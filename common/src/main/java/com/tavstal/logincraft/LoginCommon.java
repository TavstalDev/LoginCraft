package com.tavstal.logincraft;

import org.apache.logging.log4j.core.config.LoggerConfig;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.google.common.reflect.Reflection;
import com.tavstal.logincraft.commands.RegisterCommand;
import com.tavstal.logincraft.interfaces.IDatabaseManager;
import com.tavstal.logincraft.managers.MysqlManager;
import com.tavstal.logincraft.platform.Services;
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

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
        if (Services.PLATFORM.isModLoaded("examplemod")) {

            Constants.LOG.info("Hello to examplemod");
        }

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