package com.tavstal.logincraft;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

public class EventListener {
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        LoginCommon.init(event.getServer());
    }
}
