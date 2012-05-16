package com.github.GoryMoon.commands;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.github.GoryMoon.Box;
import com.github.GoryMoon.entries.Entry;
import com.github.GoryMoon.params.QueryParams;
import com.github.GoryMoon.util.BukkitUtils;

public class BoxCommand extends BaseCommand{

	public BoxCommand() {
        name = "search";
        usage = "<params> <- search the database";
        minArgs = 1;
    }

    @Override
    public boolean execute() {
        return true;
    }

    public void moreHelp() {
        // TODO display help
    }
    
    public static Runnable asTask(final CommandSender sender, final QueryParams params, final Box plugin) {
        return new Runnable() {

            @Override
            public void run() {
                try {
                    if (!params.silent) {
                        BukkitUtils.sendMessage(sender, ChatColor.GREEN + "Searching for entries");
                    }
                    List<Entry> results = null;
                    if (results.size() > 0) {
                        plugin.getSessionManager().getSession(sender).setEntryCache(results);
                        PageCommand.showPage(sender, 1);
                    } else {
                        sender.sendMessage(ChatColor.DARK_AQUA + "No results found.");
                        plugin.getSessionManager().getSession(sender).setEntryCache(null);
                    }
                } catch (final Exception ex) {
                    sender.sendMessage(ChatColor.RED + "Exception, check error log");
                }
            }
            
        };
    }

    @Override
    public boolean permission(CommandSender csender) {
        return csender.hasPermission("guardian.lookup");
    }

    @Override
    public BaseCommand newInstance() {
        // TODO Auto-generated method stub
        return new BoxCommand();
    }
}
