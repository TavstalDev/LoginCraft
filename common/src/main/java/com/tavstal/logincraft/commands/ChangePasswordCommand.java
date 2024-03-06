package com.tavstal.logincraft.commands;

import java.text.MessageFormat;

import com.mojang.brigadier.*;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import net.minecraft.commands.*;

import com.mojang.brigadier.context.*;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.tavstal.logincraft.Translations;
import com.tavstal.logincraft.utils.ModUtils;

public class ChangePasswordCommand {

    public static final String Name = "changepassword";
    public static final String Syntax = Translations.RegisterUsage.get();
    public static final String[] Aliases = new String[] { "chngpassword", "chngpass", "cpassword", "changep" };
    public static final Integer PermissionLevel = 0;

    // Put here because of the main stuff will be done here
    private static int execute(CommandContext<CommandSourceStack> command){
        //StringArgumentType.getString(command, "password")

        return Command.SINGLE_SUCCESS;
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) 
    {
        // Create Arguments
        ArgumentType<String> argType = StringArgumentType.word();

        RequiredArgumentBuilder<CommandSourceStack, String> arg = 
            Commands.argument("password", argType) // Create Password Argument
            .then(Commands.argument("newPassword", argType)
                .then(Commands.argument("newPasswordAgain", argType)
                    .executes((context) -> { return execute(context); })
                )
                .executes((context) -> { return executeSyntax(context); })
            ) // Then create passwordAgain argument, then execute the command body
            .executes((context) -> { return executeSyntax(context); }); // if passwordAgain was not provided then it's a syntax error

        // Create the command itself
        LiteralCommandNode<CommandSourceStack> commandNode = dispatcher.register(
            Commands.literal(Name) // Create the command
            .requires(source -> source.hasPermission(PermissionLevel)) // Check permission
            .then(arg.executes((context) -> executeSyntax(context))) // Implement the arguments
            .executes((context) -> { return executeSyntax(context); })); // If no argument was given then it's a syntax error

        // Generate Aliases
        for (String alias : Aliases) {
            dispatcher.register(Commands.literal(alias).redirect(commandNode));
        }
    }

    private static int executeSyntax(CommandContext<CommandSourceStack> command){
        ModUtils.SendMessage(command.getSource().getEntity(), Syntax);
        return Command.SINGLE_SUCCESS;
    }
}
