package io.github.ironrevelation.teams;

import io.github.ironrevelation.manhunt.ManHunt;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Teams {
    private static List<Player> hunters = new ArrayList<>();
    private static List<Player> runners = new ArrayList<>();
    private static final ChatColor runnerColor = ChatColor.AQUA;
    private static final ChatColor hunterColor = ChatColor.RED;

    public static List<Player> getHunters()
    {
        return hunters;
    }
    public static List<Player> getRunners()
    {
        return runners;
    }



    public static void addRunner(Player player)
    {
        runners.add(player);

        List<String> players = ManHunt.getInstance().getConfig().getStringList("runners");
        if(!players.contains(player.getName()))
        {
            players.add(player.getName());
            ManHunt.getInstance().getConfig().set("runners", players);
            ManHunt.getInstance().saveConfig();
        }

        setPlayerColor(player, runnerColor);
    }

    public static void addHunter(Player player)
    {
        hunters.add(player);
        List<String> players = ManHunt.getInstance().getConfig().getStringList("hunters");
        if(!players.contains(player.getName())) {
            players.add(player.getName());
            ManHunt.getInstance().getConfig().set("hunters", players);
            ManHunt.getInstance().saveConfig();
        }
        setPlayerColor(player, hunterColor);
        player.getInventory().remove(new ItemStack(Material.COMPASS));
        if(player.getWorld().getEnvironment()!=World.Environment.NETHER)
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }

    public static void removeRunner(Player player)
    {
        runners.remove(player);
        List<String> players = ManHunt.getInstance().getConfig().getStringList("runners");
        players.remove(player.getName());
        ManHunt.getInstance().getConfig().set("runners", players);
        ManHunt.getInstance().saveConfig();
        setPlayerColor(player, ChatColor.RESET);
    }

    public static void removeHunter(Player player)
    {
        hunters.remove(player);
        List<String> players = ManHunt.getInstance().getConfig().getStringList("hunters");
        players.remove(player.getName());
        ManHunt.getInstance().getConfig().set("hunters", players);
        ManHunt.getInstance().saveConfig();
        setPlayerColor(player, ChatColor.RESET);
        player.getInventory().remove(new ItemStack(Material.COMPASS));
    }

    private static void setPlayerColor(Player p, ChatColor color)
    {
        p.setDisplayName(color + p.getName());
        p.setPlayerListName(color + p.getName());
    }
}
