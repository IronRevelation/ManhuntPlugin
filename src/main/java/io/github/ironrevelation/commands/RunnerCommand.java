package io.github.ironrevelation.commands;

import io.github.ironrevelation.manhunt.ManHunt;
import io.github.ironrevelation.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RunnerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {
        if(args.length == 0)
        {
            if(sender instanceof Player)
                sender.sendMessage(ChatColor.RED + "Arguments are needed");
            else
                ManHunt.getInstance().getLogger().info("Arguments are needed");
        }
        else if(args.length == 1)
        {
            switch(args[0]){
                case "list" ->{
                    for(Player player : Teams.getRunners())
                    {
                        if(sender instanceof Player)
                            sender.sendMessage(ChatColor.GREEN + player.getName());
                    }
                }
                default -> {
                    if(sender instanceof Player)
                        sender.sendMessage(ChatColor.RED + "Invalid argument");
                    else
                        ManHunt.getInstance().getLogger().info("Invalid argument");
                }
            }

        }
        else if(args.length == 2)
        {
            switch (args[0]) {
                case "add" -> {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        Teams.removeHunter(p);
                        Teams.addRunner(p);
                    } else {
                        if (sender instanceof Player)
                            sender.sendMessage(ChatColor.RED + args[1] + " is not online");
                        else
                            ManHunt.getInstance().getLogger().info(args[1] + " is not online");
                    }
                }
                case "remove" -> {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p != null) {
                        Teams.removeRunner(p);
                        Teams.addHunter(p);
                    } else {
                        if (sender instanceof Player)
                            sender.sendMessage(ChatColor.RED + args[0] + "is not online");
                        else
                            ManHunt.getInstance().getLogger().info(args[0] + "is not online");
                    }
                }
                default -> {
                    if (sender instanceof Player)
                        sender.sendMessage(ChatColor.RED + "Wrong argument");
                    else
                        ManHunt.getInstance().getLogger().info("Wrong argument");
                }
            }
        }
        else
        {
            if(sender instanceof Player)
                sender.sendMessage(ChatColor.RED + "Too many arguments");
            else
                ManHunt.getInstance().getLogger().info("Too many arguments");
        }

        return true;
    }
}
