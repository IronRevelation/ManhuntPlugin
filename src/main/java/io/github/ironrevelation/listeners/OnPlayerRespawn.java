package io.github.ironrevelation.listeners;

import io.github.ironrevelation.teams.Teams;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerRespawn implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();
        if(Teams.getHunters().contains(p))
        {
            p.getInventory().addItem(new ItemStack(Material.COMPASS));
        }
    }
}
