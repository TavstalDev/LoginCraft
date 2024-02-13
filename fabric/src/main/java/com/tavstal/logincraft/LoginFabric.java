package com.tavstal.logincraft;

import com.tavstal.logincraft.platform.FabricPlatformHelper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class LoginFabric implements ModInitializer {
    
    private boolean _isInitialized = false;

    @Override
    public void onInitialize() {
        
        // CHECK IF THE MOD IS LOADED ON CLIENT
		var helper = new FabricPlatformHelper();
		if (helper.isClientSide()) {
			Constants.LOG.error("{} should be only loaded on the server.", Constants.MOD_NAME);
			return;
		}

        // SERVER START TICK EVENT
		ServerTickEvents.START_SERVER_TICK.register((server) -> {
			if (_isInitialized)
			{
				return;
			}

			_isInitialized = true;
			LoginCommon.init(server);
		});
    }
}
