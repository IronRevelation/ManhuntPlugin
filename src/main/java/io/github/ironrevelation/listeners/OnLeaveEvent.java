package io.github.ironrevelation.listeners;

import io.github.ironrevelation.teams.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerQuitEvent;


public class OnLeaveEvent implements Listener {
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e)
    {
        System.out.println("player quit");
        Player p = e.getPlayer();
        if(Teams.getRunners().contains(p))
            Teams.getRunners().remove(p);
        else
            Teams.getHunters().remove(p);
    }
}
