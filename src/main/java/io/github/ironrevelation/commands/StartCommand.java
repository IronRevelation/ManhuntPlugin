package io.github.ironrevelation.commands;

import io.github.ironrevelation.runnables.ReleaseHuntersRunnable;
import org.bukkit.GameMode;
import org.bukkit.command.*;
import io.github.ironrevelation.manhunt.*;
import org.bukkit.entity.Player;
import io.github.ironrevelation.teams.*;
import org.bukkit.*;

public class StartCommand implements CommandExecutor{
    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args)
    {
        if(!ManHunt.isStarted())
        {
            if (sender instanceof Player)
            {
                ((Player) sender).getWorld().setTime(0);
                ((Player) sender).getWorld().setStorm(false);
            }
            Bukkit.setDefaultGameMode(GameMode.SURVIVAL);

            for(Player p:Teams.getRunners())
            {
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(new Location(p.getWorld(), 20, p.getWorld().getHighestBlockYAt(20,20)+1, 20));
            }
            new ReleaseHuntersRunnable().runTaskLater(ManHunt.getInstance(), (long) (ManHunt.getInstance().getConfig().getInt("grace-period")* 20L));
            ManHunt.start();
            ManHunt.getInstance().getConfig().set("started", true);
            ManHunt.getInstance().saveConfig();
        }
        return true;
    }
}

