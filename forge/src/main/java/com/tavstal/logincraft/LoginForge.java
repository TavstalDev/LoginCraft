package com.tavstal.logincraft;

import com.tavstal.logincraft.platform.ForgePlatformHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class LoginForge {
    
    public LoginForge() {
    
        var helper = new ForgePlatformHelper();
        if (helper.isClientSide()) {
			Constants.LOG.error("{} should be only loaded on the server.", Constants.MOD_NAME);
			return;
		}
        // Use Forge to bootstrap the Common mod.
        MinecraftForge.EVENT_BUS.register(new EventListener());
    }
}