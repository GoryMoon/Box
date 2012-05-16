package com.github.GoryMoon.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.GoryMoon.util.BukkitUtils;
import com.github.GoryMoon.util.Utils;

public class PageCommand extends BaseCommand {

    public PageCommand() {
        name = "page";
        usage = "<page> <- view a page from your search";
        allowConsole = false;
        minArgs = 1;
        maxArgs = 1;
    }

    @Override
    public boolean execute() {
        if (Utils.isInt(args.get(0))) {
            showPage(sender, Integer.valueOf(args.get(0)));
        } else {
            sender.sendMessage(ChatColor.RED + "You have to specify a page");
        }
        return true;
    }

    @Override
    public void moreHelp() {
        BukkitUtils.sendMessage(sender, "&cShows the specified page of results from your latest search");
    }

    @Override
    public boolean permission(CommandSender csender) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public BaseCommand newInstance() {
        return new PageCommand();
    }
    
    public static void showPage(CommandSender sender, int page) {
        
    }
}