package io.github.ironrevelation.listeners;

import io.github.ironrevelation.lobby.Lobby;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        Entity e = event.getEntity();
        if(e.getType() == EntityType.ENDER_DRAGON)
        {
            Lobby.declareWin("RUNNERS");
        }

    }
}
