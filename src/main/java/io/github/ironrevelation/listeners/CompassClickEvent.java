package io.github.ironrevelation.listeners;

import io.github.ironrevelation.teams.Teams;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.CompassMeta;

public class CompassClickEvent implements Listener {
    @EventHandler
    public void onCompassClick(PlayerInteractEvent event)
    {
        if (event.getHand() == EquipmentSlot.HAND) {
            Player player = event.getPlayer();
            if((Teams.getHunters().contains(player))&&(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.COMPASS)
            {
                Player target = getClosestRunner(player);
                if(target != null)
                {
                    if(player.getWorld().getEnvironment().equals(World.Environment.NETHER))
                    {
                        CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
                        assert meta != null;
                        meta.setLodestone(target.getLocation());
                        meta.setLodestoneTracked(false);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                    }
                    else
                    {
                        CompassMeta meta = (CompassMeta) player.getInventory().getItemInMainHand().getItemMeta();
                        assert meta != null;
                        meta.setLodestone(null);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        player.setCompassTarget(target.getLocation());
                    }
                    player.sendMessage(ChatColor.GREEN + "Pointing to: " + target.getName());
                }
                else
                {
                    player.sendMessage(ChatColor.GREEN + "No runner found");
                }
            }
        }

    }
    public Player getClosestRunner(Player p)
    {
        double distance = Double.POSITIVE_INFINITY;
        Player target = null;
        for(Player t : Teams.getRunners())
        {
            if (p.getWorld()==t.getWorld()) {
                double dist = p.getLocation().distanceSquared(t.getLocation());
                if (dist < distance) {
                    distance = dist;
                    target = t;
                }
            }
        }
        return target;
    }
}
