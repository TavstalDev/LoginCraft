package com.tavstal.logincraft.models;

import java.sql.Date;

public class Account {
    public final Integer Id;
    public final String Username;
    public final String Password;
    public final String RegisterIP;
    public final String IP;
    public final Date RegisterDate;
    public final Date LastLogin;
    public final Integer X;
    public final Integer Y;
    public final Integer Z;
    public final String World;
    public final float Pitch;
    public final float Yaw;
    public final Boolean IsLogged;

    public Account(Integer id, String username, String password, String registerIP, String ip, Date registerDate, Date lastLogin, Integer x, Integer y, Integer z, String world, float pitch, float yaw, Boolean isLogged) {
        Id = id;
        Username = username;
        Password = password;
        RegisterIP = registerIP;
        IP = ip;
        RegisterDate = registerDate;
        LastLogin = lastLogin;
        X = x;
        Y = y;
        Z = z;
        Yaw = yaw;
        Pitch = pitch;
        World = world;
        IsLogged = isLogged;
    }
}
