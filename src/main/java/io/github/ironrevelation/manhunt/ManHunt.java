package io.github.ironrevelation.manhunt;

import io.github.ironrevelation.listeners.*;

import org.bukkit.*;

import org.bukkit.plugin.java.*;
import io.github.ironrevelation.lobby.*;
import io.github.ironrevelation.commands.*;

public class ManHunt extends JavaPlugin {

    public static ManHunt plugin;
    public static boolean started;
    @Override
    public void onEnable() {
        ManHunt.plugin = this;

        this.getCommand("start").setExecutor(new StartCommand());
        this.getCommand("runner").setExecutor(new RunnerCommand());
        this.getServer().getPluginManager().registerEvents(new OnJoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnLeaveEvent(), this);
        this.getServer().getPluginManager().registerEvents(new CompassClickEvent(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerRespawn(), this);
        this.getServer().getPluginManager().registerEvents(new OnEntityDeath(), this);

        this.saveDefaultConfig();
        ManHunt.started = getConfig().getBoolean("started");
        World world = Bukkit.getWorld("world");
        assert world != null;

        if(!started)
        {
            Lobby.createLobby();
            Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
            world.setPVP(false);
        }
        world.setDifficulty(Difficulty.EASY);
    }

    public static boolean isStarted()
    {
        return started;
    }
    public static void start()
    {
        started=true;
    }
    public static ManHunt getInstance() {
        return ManHunt.plugin;
    }
}