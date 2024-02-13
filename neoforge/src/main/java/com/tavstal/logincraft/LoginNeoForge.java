package com.tavstal.logincraft;

import com.tavstal.logincraft.platform.NeoForgePlatformHelper;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class LoginNeoForge {

    public LoginNeoForge(IEventBus eventBus) {

        var helper = new NeoForgePlatformHelper();
        if (helper.isClientSide()) {
			Constants.LOG.error("{} should be only loaded on the server.", Constants.MOD_NAME);
			return;
		}
        // Use Forge to bootstrap the Common mod.
        eventBus.register(new EventListener());
    }
}