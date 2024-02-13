package com.tavstal.logincraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {
    public static String GetName(Entity entity) {
        return entity.getName().getString();
    } 

    public static boolean IsPlayer(Entity entity)
    {
        return entity instanceof Player; 
    }

    public static boolean IsLiving(Entity entity)
    {
        return entity instanceof LivingEntity; 
    }

    public static Level GetLevel(Entity entity) {
        return entity.level();
    }

    public static ServerLevel GetServerLevel(Entity entity) {
        var server = entity.getServer();
        if (server == null)
            return null;

        return server.getLevel(GetLevel(entity).dimension());
    }

    public static Level GetLevel(ServerPlayer entity) {
        return entity.level();
    }

    public static ServerLevel GetServerLevel(ServerPlayer entity) {
        var server = entity.getServer();
        if (server == null)
        return null;
        return entity.serverLevel();
    }

    public static Vec3 GetPosition(Entity entity) {
        return entity.position();
    }

    public static BlockPos GetBlockPosition(Entity entity) {
        return entity.blockPosition();
    }

    public static double DistanceToSqr(Entity entity, Vec3 vec3) {
        double d = entity.getX() - vec3.x;
        double e = entity.getY() - vec3.y;
        return d * d + e * e;
    }
}
