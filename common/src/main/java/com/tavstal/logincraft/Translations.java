package com.tavstal.logincraft;

import com.supermartijn642.configlib.api.ConfigBuilders;
import com.supermartijn642.configlib.api.IConfigBuilder;
import java.util.function.Supplier;

public class Translations {

    public static final Supplier<String> RegisterRequest;
    public static final Supplier<String> RegisterUsage;
    public static final Supplier<String> RegisterMismatch;
    public static final Supplier<String> RegisterSuccess;
    public static final Supplier<String> RegisterByAdmin;
    public static final Supplier<String> RegisterAlready;

    public static final Supplier<String> LoginRequest;
    public static final Supplier<String> LoginUsage;
    public static final Supplier<String> LoginWrong;
    public static final Supplier<String> LoginKick;
    public static final Supplier<String> LoginSuccess;
    public static final Supplier<String> LoginNotRegistered;
    public static final Supplier<String> LoginAlready;

    public static final Supplier<String> UnregisterUsage;
    public static final Supplier<String> UnregisterSuccess;
    public static final Supplier<String> UnregisterMismatch;

    public static final Supplier<String> ChangePasswordUsage;
    public static final Supplier<String> ChangePasswordMismatch;
    public static final Supplier<String> ChangePasswordFail;
    public static final Supplier<String> ChangePasswordSuccess;

    public static final Supplier<String> JoinNameLength;
    public static final Supplier<String> JoinInvalidChar;
    public static final Supplier<String> JoinCountryBanned;
    public static final Supplier<String> JoinFullServer;
    public static final Supplier<String> JoinSameName;

    public static final Supplier<String> AntiBotKick;
    public static final Supplier<String> AntiBotEnable;
    public static final Supplier<String> AntiBotDisable;



    static {
        IConfigBuilder builder = ConfigBuilders.newTomlConfig(Constants.MOD_ID, "translations", false).dontSync();
        builder.push("Register");
        RegisterRequest = builder.define("register_request", "&6Please register to the server with /register <password> <password>", 0, 0);
        RegisterUsage = builder.define("register_usage", "&6Usage: /register <password> <password>", 0, 0);
        RegisterMismatch = builder.define("register_mismatch", "&cYou must provide matching passwords.", 0, 0);
        RegisterSuccess = builder.define("register_success", "&aYou have successfully registered to the server.", 0, 0);
        RegisterByAdmin = builder.define("register_admin", "&aYou have been registered by an admin. Your password is: {0}", 0, 0);
        RegisterAlready = builder.define("register_already", "&cYou have registered already.", 0, 0);
        builder.pop();
        builder.push("Login");
        LoginRequest = builder.define("login_request", "&6Please login to the server with /login <password>", 0, 0);
        LoginUsage = builder.define("login_usage", "&6Usage: /login <password>", 0, 0);
        LoginWrong = builder.define("login_wrong", "&cWrong password.", 0, 0);
        LoginKick = builder.define("login_kick", "&cToo many wrong attempts.", 0, 0);
        LoginAlready = builder.define("login_already", "&cYou have logged in already.", 0, 0);
        LoginNotRegistered = builder.define("login_not_registered", "&cYou must register before logging in.", 0, 0);
        LoginSuccess = builder.define("login_success", "&aYou have successfully logged in.", 0, 0);
        builder.pop();
        builder.push("Unregister");
        UnregisterUsage = builder.define("unregister_usage", "&6Usage: /unregister <password>", 0, 0);
        UnregisterMismatch= builder.define("unregister_mismatch", "&cYour provided password does not match with your current password.", 0, 0);
        UnregisterSuccess= builder.define("unregister_success", "&aYou have successfully unregistered.", 0, 0);
        builder.pop();
        builder.push("Change Password");
        ChangePasswordUsage = builder.define("change_password_usage", "&6Usage: /changepassword <currentPassword> <newPassword> <newPassword>", 0, 0);
        ChangePasswordMismatch = builder.define("change_password_mismatch", "&cYou must provide matching passwords.", 0, 0);
        ChangePasswordSuccess = builder.define("change_password_success", "&aYou have successfully changed your password.", 0, 0);
        ChangePasswordFail = builder.define("change_password_fail", "&cYour provided password does not match with your current password.", 0, 0);
        builder.pop();
        builder.push("Join");
        JoinNameLength = builder.define("join_name_length", "Your name must be longer than {0} char(s).", 0, 0);
        JoinInvalidChar = builder.define("join_invalid_char", "Your name contains invalid char(s), please follow the '{0}' format.", 0, 0);
        JoinCountryBanned = builder.define("join_country_banned", "Your country is banned.", 0, 0);
        JoinFullServer = builder.define("join_full_server", "The server is full.", 0, 0);
        JoinSameName = builder.define("join_same_name", "Someone with the same name is playing on the server already.", 0, 0);
        builder.pop();
        builder.push("AntiBot");
        AntiBotKick = builder.define("antibot_kick", "AntiBot protection is active. Please try again later.", 0, 0);
        AntiBotEnable = builder.define("antibot_enable", "&cAntiBot protection has been activated due the amount of connections.", 0, 0);
        AntiBotDisable = builder.define("antibot_disable", "&aAntiBot protection has been deactivated.", 0, 0);
        builder.pop();
        builder.build();
    }
}
