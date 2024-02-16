package com.tavstal.logincraft;

import com.supermartijn642.configlib.api.ConfigBuilders;
import com.supermartijn642.configlib.api.IConfigBuilder;
import com.tavstal.logincraft.models.enums.DatabaseTypes;

import java.util.function.Supplier;

public class Config {

    public static final Supplier<DatabaseTypes> DatabaseType;
    public static final Supplier<String> DatabaseName;
    public static final Supplier<String> DatabaseHost;
    public static final Supplier<Integer> DatabasePort;
    public static final Supplier<String> DatabaseUser;
    public static final Supplier<String> DatabasePassword;

    public static final Supplier<Boolean> EnableDebugMode;
    public static final Supplier<Boolean> AllowChat;
    public static final Supplier<Boolean> HideChat;
    //public static final Supplier<String[]> AllowedCommands;
    public static final Supplier<Integer> MinNicknameLength;
    public static final Supplier<Boolean> ForceSpawnLoc;
    //public static final Supplier<String[]> WorldsToForce;
    public static final Supplier<Boolean> KickOnWrongPassword;
    public static final Supplier<Boolean> AllowMovement;
    public static final Supplier<Integer> Timeout;
    public static final Supplier<String> AllowedNicknameCharacters;
    public static final Supplier<Boolean> DenyTabCompleteBeforeLogin;
    public static final Supplier<String> AllowedPasswordCharacters;

    public static final Supplier<Integer> MinPasswordLength;
    public static final Supplier<Integer> MaxPasswordLength;
    //public static final Supplier<String[]> UnsafePasswords;

    public static final Supplier<Integer> MessageInterval;
    public static final Supplier<Boolean> ApplyBlindEffect;
    public static final Supplier<Boolean> PreventOtherCase;

    public static final Supplier<Boolean> EnableAntiBot;
    public static final Supplier<Integer> AntiBotInterval;
    public static final Supplier<Integer> AntiBotSensivity;
    public static final Supplier<Integer> AntiBotDuration;


    static {
        IConfigBuilder builder = ConfigBuilders.newTomlConfig(Constants.MOD_ID, "config", true).dontSync();
        EnableDebugMode = builder.define("enable_debug_mode", false);
        builder.push("Database");
        DatabaseType = builder.define("db_type", DatabaseTypes.MYSQL);
        DatabaseName = builder.define("db_name", "minecraft", 1, 255);
        DatabaseHost = builder.define("db_host", "127.0.0.1", 1, 255);
        DatabasePort = builder.define("db_port", 3306, 1, 65535);
        DatabaseUser = builder.define("db_user", "root", 1, 255);
        DatabasePassword = builder.define("db_password", "ascent", 1, 255);
        builder.pop();
        builder.push("Settings");
        AllowChat = builder.define("allow_chat", true);
        HideChat = builder.define("hide_chat", false);
        //AllowedCommands = builder.define("allowed_commands", );
        MinNicknameLength = builder.define("min_nickname_length", 3, 0, 255);
        ForceSpawnLoc = builder.define("force_spawn_loc", false);
        KickOnWrongPassword= builder.define("kick_on_wrong_password", true);
        AllowMovement = builder.define("allow_movement", false);
        Timeout = builder.define("timeout", 30, 0, 300);
        AllowedNicknameCharacters = builder.define("allowed_nickname_characters", "", 0, 100);
        DenyTabCompleteBeforeLogin = builder.define("deny_tab_complete_before_login", true);
        AllowedPasswordCharacters = builder.define("allowed_password_characters", "", 0, 100);

        MinPasswordLength = builder.define("min_password_length", 4, 0, 30);
        MaxPasswordLength = builder.define("max_password_length", 30, 30, 60);

        MessageInterval = builder.define("message_interval", 5, 0, 30);
        ApplyBlindEffect = builder.define("apply_blind_effect", true);
        PreventOtherCase = builder.define("prevent_other_case", true);
        
        EnableAntiBot = builder.define("enable_antibot", true);
        AntiBotInterval = builder.define("antibot_interval", 5, 1, 60);
        AntiBotSensivity = builder.define("antibot_sensivity", 10, 1, Integer.MAX_VALUE);
        AntiBotDuration = builder.define("antibot_duration", 10, 10, Integer.MAX_VALUE);
        builder.pop();
        builder.build();
    }
}
