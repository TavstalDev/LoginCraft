package com.tavstal.logincraft.commands;

import com.mojang.brigadier.*;
import net.minecraft.commands.*;
import com.mojang.brigadier.context.*;
import com.tavstal.logincraft.LoginCommon;

import net.minecraft.world.entity.player.Player;

public class RegisterCommand {
 
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("register").executes((command) -> {
            return execute(command);
        }));
    }

    private static int executeSyntax(CommandContext<CommandSourceStack> command) {

        
        return Command.SINGLE_SUCCESS;
    }

    private static int execute(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();

            if (player == null)
                return 0;

            
        }
        return Command.SINGLE_SUCCESS;
    }
}
