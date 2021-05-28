package io.github.ironrevelation.runnables;
import io.github.ironrevelation.manhunt.ManHunt;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.*;
import io.github.ironrevelation.teams.*;
import io.github.ironrevelation.lobby.*;

import java.util.Objects;

public class ReleaseHuntersRunnable extends BukkitRunnable{
    public void run()
    {
        for(Player p : Teams.getHunters())
        {
            p.setGameMode(GameMode.SURVIVAL);
            p.teleport(new Location(p.getWorld(), 20, p.getWorld().getHighestBlockYAt(20,20)+1, 20));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 10, 10));
        }
        Lobby.deleteLobby();
        Objects.requireNonNull(Bukkit.getWorld("world")).setPVP(true);
    }
}
