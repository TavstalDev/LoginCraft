package com.tavstal.logincraft.interfaces;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;

import com.tavstal.logincraft.models.Account;

import net.minecraft.server.level.ServerPlayer;

public interface IDatabaseManager {
    String GetJdbcUrl();
    Connection CreateConnection();
    void CheckSchema();
    void AddAccount(ServerPlayer player, String password);
    void DeleteAccount(Integer id);
    void UpdateAccount(Integer id, ServerPlayer player, String password);
    void UpdateAccount(Integer id, ServerPlayer player, Date lastLogin, Boolean isLogged);
    void UpdateAccount(Integer id, ServerPlayer player);
    Account GetAccount(String username);
    HashSet<Account> GetAccounts(String ip);
}
