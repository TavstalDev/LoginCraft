package com.tavstal.logincraft.managers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import com.ibm.icu.text.MessageFormat;

public class DatabaseManager {
    private String _host;
    private Integer _port;
    private String _database;
    private String _user;
    private String _password;
    private Integer _timeout;

    public DatabaseManager(String host, Integer port, String database, String user, String password, Integer timeout) {
        _host = host;
        _port = port;
        _database = database;
        _user = user;
        _password = password;
        _timeout = timeout;
    }

    public DatabaseManager(String host, String database, String user, String password, Integer timeout) {
        _host = host;
        _port = 3306;
        _database = database;
        _user = user;
        _password = password;
        _timeout = timeout;
    }

    private String GetJdbcUrl() {
        return MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", _host, _port, _database);
    }

    public Connection CreateConnection() {
        String connectionUrl =
                MessageFormat.format("{6};"
                        + "database={2};"
                        + "user={3}@{0}:{1};"
                        + "password={4};"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout={5};",
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

    public void AddAccount(UUID uuid, String password) {
        try (Connection connection = CreateConnection()) {

        }
        catch (Exception ex) {
        
            
        }
    }
}
