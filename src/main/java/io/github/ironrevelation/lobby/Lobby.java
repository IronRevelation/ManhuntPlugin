package io.github.ironrevelation.lobby;
import io.github.ironrevelation.manhunt.ManHunt;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lobby {
    public final static int height = 170;
    final static int size = 8;
    final static int cage_height = 8;
    final static Material material = Material.WHITE_STAINED_GLASS;
    public static void createLobby()
    {

        World world = Bukkit.getWorld("world");
        for (int i=-size; i<size; i++)
        {
            for(int j=-size; j<size; j++)
            {
                Location loc = new Location(world, i, height, j);
                loc.getBlock().setType(material);
                loc = new Location(world, i, height+cage_height, j);
                loc.getBlock().setType(material);
            }
        }
        for (int i=-(size-1); i<size-1; i++)
        {
            for(int j=-(size-1); j<size-1; j++)
            {
                for (int h = height+1; h<cage_height+height; h++ )
                {
                    Location loc = new Location(world, i, h, j);
                    loc.getBlock().setType(Material.AIR);
                }
            }
        }
        for(int i = -size; i<size; i++)
        {
            for(int h = height; h<height+cage_height+1; h++)
            {
                Location loc = new Location(world, i, h, -size);
                loc.getBlock().setType(material);
                loc = new Location(world, i, h, size-1);
                loc.getBlock().setType(material);
                loc = new Location(world, -size, h, i);
                loc.getBlock().setType(material);
                loc = new Location(world, size-1, h, i);
                loc.getBlock().setType(material);
            }
        }
    }

    public static void deleteLobby()
    {
        World world = Bukkit.getWorld("world");
        for (int i=-size; i<size; i++)
        {
            for(int j=-size; j<size; j++)
            {
                for (int h = height; h<cage_height+height+1; h++ )
                {
                    Location loc = new Location(world, i, h, j);
                    loc.getBlock().setType(Material.AIR);
                }
            }
        }
    }
    public static void declareWin(String team)
    {
        createLobby();
        Objects.requireNonNull(Bukkit.getWorld("world")).setPVP(false);
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.teleport(new Location(Bukkit.getWorld("world"), 0, Lobby.height + 1, 0));
            p.setGameMode(GameMode.ADVENTURE);
            p.sendTitle(ChatColor.GREEN + team + " WIN!!", "", 5, 100, 5);
        }
        ManHunt.getInstance().getConfig().set("started", false);
        ManHunt.getInstance().getConfig().set("runners", new ArrayList<String>());
        ManHunt.getInstance().getConfig().set("hunters", new ArrayList<String>());
        ManHunt.getInstance().saveConfig();
    }
}
