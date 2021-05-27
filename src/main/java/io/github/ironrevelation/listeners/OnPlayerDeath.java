package io.github.ironrevelation.listeners;

import io.github.ironrevelation.lobby.Lobby;
import io.github.ironrevelation.teams.Teams;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity().getPlayer();
        assert p != null;
        if (Teams.getHunters().contains(p))
        {
            e.getDrops().remove(new ItemStack(Material.COMPASS));
        }
        else if(Teams.getRunners().contains(p))
        {
            p.setGameMode(GameMode.SPECTATOR);
            Teams.removeRunner(p);
            if(Teams.getRunners().isEmpty())
            {
                Lobby.declareWin("HUNTERS");
            }
        }
    }
}
