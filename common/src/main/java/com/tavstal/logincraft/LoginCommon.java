package com.tavstal.logincraft;

import org.apache.logging.log4j.core.config.LoggerConfig;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.google.common.reflect.Reflection;
import com.tavstal.logincraft.commands.ChangePasswordCommand;
import com.tavstal.logincraft.commands.LoginCommand;
import com.tavstal.logincraft.commands.RegisterCommand;
import com.tavstal.logincraft.commands.UnRegisterCommand;
import com.tavstal.logincraft.interfaces.IDatabaseManager;
import com.tavstal.logincraft.managers.MysqlManager;
import net.minecraft.server.MinecraftServer;

public class LoginCommon {

    private static IDatabaseManager _database;
    public static IDatabaseManager Database() {
        return _database;
    }

    public static void init(MinecraftServer server) {
        // Create the Config class
        Reflection.initialize(Config.class);
        
        // Connect to the database
        _database = new MysqlManager(Config.DatabaseHost.get(), Config.DatabasePort.get(), Config.DatabaseName.get(), Config.DatabaseUser.get(), Config.DatabasePassword.get(), 120);

        // Register Commands
        var dispatcher = server.getCommands().getDispatcher();
        RegisterCommand.register(dispatcher);
        LoginCommand.register(dispatcher);
        ChangePasswordCommand.register(dispatcher);
        UnRegisterCommand.register(dispatcher);

        if (Config.EnableDebugMode.get()) {
            SetLogLevel("DEBUG");
        }
    }

    private static void SetLogLevel(String level) {
        // Set the logging level for the logger
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        LoggerConfig loggerConfig = loggerContext.getConfiguration().getLoggerConfig(Constants.LOG.getName());
        loggerConfig.setLevel(Level.valueOf(level.toUpperCase()));
        loggerContext.updateLoggers();
    }
}