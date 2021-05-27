package io.github.ironrevelation.listeners;

import io.github.ironrevelation.lobby.Lobby;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import io.github.ironrevelation.teams.*;
import io.github.ironrevelation.manhunt.*;
import org.bukkit.*;

import java.util.List;

public class OnJoinEvent implements Listener{
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e)
    {
        final Player p = e.getPlayer();
        if(!ManHunt.isStarted()) {
            p.teleport(new Location(p.getWorld(), 0, Lobby.height + 1, 0));
        }
        if(!p.hasPlayedBefore())
        {
            Teams.addHunter(p);
        }
        else
        {
            List<String> runners = ManHunt.getInstance().getConfig().getStringList("runners");
            List<String> hunters = ManHunt.getInstance().getConfig().getStringList("hunters");

            if(runners.contains(p.getName()))
                Teams.addRunner(p);
            else if (hunters.contains(p.getName()))
                Teams.addHunter(p);
            else
                ManHunt.getInstance().getLogger().info("Something went wrong, old player joined but was not in a team. You may have tampered with the config");
        }
    }
}
