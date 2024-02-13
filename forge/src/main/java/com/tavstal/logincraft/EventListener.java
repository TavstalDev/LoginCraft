package com.tavstal.logincraft;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventListener {
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        LoginCommon.init(event.getServer());
    }
}
