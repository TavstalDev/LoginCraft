package com.tavstal.logincraft.managers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import com.ibm.icu.text.MessageFormat;
import com.tavstal.logincraft.models.Account;
import com.tavstal.logincraft.utils.EntityUtils;
import com.tavstal.logincraft.utils.PlayerUtils;
import com.tavstal.logincraft.utils.WorldUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class DatabaseManager {
    private String _host;
    private Integer _port;
    private String _database;
    private String _user;
    private String _password;
    private Integer _timeout;
    private final String _accountTable = "lc_accounts";

    public DatabaseManager(String host, Integer port, String database, String user, String password, Integer timeout) {
        _host = host;
        _port = port;
        _database = database;
        _user = user;
        _password = password;
        _timeout = timeout;
        CheckSchema();
    }

    public DatabaseManager(String host, String database, String user, String password, Integer timeout) {
        _host = host;
        _port = 3306;
        _database = database;
        _user = user;
        _password = password;
        _timeout = timeout;
        CheckSchema();
    }

    private String GetJdbcUrl() {
        return MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", _host, _port, _database);
    }

    public Connection CreateConnection() {
        String connectionUrl =
                MessageFormat.format("{6};database={2};user={3}@{0}:{1};password={4};encrypt=true;trustServerCertificate=false;loginTimeout={5};",
                _host, _port, _database, _user, _password, _timeout, GetJdbcUrl());

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            return connection;
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void CheckSchema() {
        // Create Account Table
        String createSql = MessageFormat.format("CREATE TABLE IF NOT EXISTS {0} (" + 
            "Id INT(11) PRIMARY KEY AUTO_INCREMENT, Username VARCHAR(255) UNIQUE NOT NULL," +
            "Password VARCHAR(255) NOT NULL, RegisterIP VARCHAR(40), IP VARCHAR(40), RegisterDate TIMESTAMP NOT NULL," +
            "LastLogin TIMESTAMP NOT NULL, X INT(255) NOT NULL, Y INT(255) NOT NULL, Z INT(255) NOT NULL," +
            "World VARCHAR(255) NOT NULL, Yaw FLOAT NOT NULL, Pitch FLOAT NOT NULL, IsLogged BOOLEAN NOT NULL"
            + ");", 
            _accountTable);
        try (Connection connection = CreateConnection();
            PreparedStatement prepStatement= connection.prepareStatement(createSql, Statement.SUCCESS_NO_INFO);) {
            prepStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddAccount(ServerPlayer player, String password) {
        BlockPos blockPos = EntityUtils.GetBlockPosition(player);
        String worldName = WorldUtils.GetName(EntityUtils.GetLevel(player));
        String insertSql = MessageFormat.format("INSERT INTO {0} (Username, Password, RegisterIP, IP, RegisterDate, LastLogin, X, Y, Z, World, Yaw, Pitch, IsLogged) VALUES ('{1}', '{2}', '{3}', '{4}', '{5}', '{6}', '{7}', '{8}', '{9}', '{10}', '{11}', '{12}', '{13}');", 
        _accountTable, EntityUtils.GetName(player), password, player.getIpAddress(), player.getIpAddress(), LocalDateTime.now(), LocalDateTime.now(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), worldName, player.getYRot(), player.getXRot(), true);

        try (Connection connection = CreateConnection();
            PreparedStatement prepsInsert= connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsert.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account GetAccount(String username) {
        String getSql = MessageFormat.format("SELECT * FROM {0} WHERE Username='{1}';", _accountTable, username);
        try (Connection connection = CreateConnection();
            PreparedStatement prepStatement= connection.prepareStatement(getSql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                return new Account(result.getInt("Id"), result.getString("Username"), result.getString("Password"), result.getString("RegisterIP"), 
                result.getString("IP"), result.getDate("RegisterDate"), result.getDate("LastLogin"), result.getInt("X"), result.getInt("Y"), result.getInt("Z"), 
                result.getString("World"), result.getFloat("Pitch"), result.getFloat("Yaw"), result.getBoolean("IsLogged"));
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashSet<Account> GetAccounts(String ip) {
        HashSet<Account> accounts = new HashSet<>();
        String getSql = MessageFormat.format("SELECT * FROM {0} WHERE IP='{1}';", _accountTable, ip);
        try (Connection connection = CreateConnection();
            PreparedStatement prepStatement= connection.prepareStatement(getSql, Statement.RETURN_GENERATED_KEYS);) {

            ResultSet result = prepStatement.executeQuery();
            while (result.next()) {
                accounts.add( new Account(result.getInt("Id"), result.getString("Username"), result.getString("Password"), result.getString("RegisterIP"), 
                result.getString("IP"), result.getDate("RegisterDate"), result.getDate("LastLogin"), result.getInt("X"), result.getInt("Y"), result.getInt("Z"), 
                result.getString("World"), result.getFloat("Pitch"), result.getFloat("Yaw"), result.getBoolean("IsLogged")));
            }
            return accounts;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
