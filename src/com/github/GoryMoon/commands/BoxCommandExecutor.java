package com.github.GoryMoon.commands;

import java.util.ArrayList;

import java.util.List;

import net.minecraft.server.EntityPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;

import com.github.GoryMoon.Box;
import com.github.GoryMoon.util.Teller;
import com.github.GoryMoon.util.Teller.Type;
import com.github.GoryMoon.VirtualChestManager;
import com.github.GoryMoon.VirtualChest;

public class BoxCommandExecutor implements CommandExecutor{
	
	private final VirtualChestManager chestManager;
	public Box plugin;
	CommandSender sender;
	Command command;
	String Label;
	String[] args;
	private ArrayList<Boolean> commands = new ArrayList<Boolean>();

    public BoxCommandExecutor(VirtualChestManager chestManager, Box plugin) {
    	this.chestManager = chestManager;
    	this.plugin = plugin;
    }

    /**
     * Command manager for box
     *
     * @param sender - {@link CommandSender}
     * @param command - {@link Command}
     * @param label command name
     * @param args arguments
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	final String name = command.getName();
    	if (args.length != 0 && args[0].equalsIgnoreCase("help"))
			return executeHelp(sender);
		else if (name.equals("box"))
			return executeBox(sender, args);
		else
			return false;
    }
    
    public boolean executeHelp(CommandSender sender) {
    	if (sender instanceof CraftPlayer) {
	    	if (sender.hasPermission("box.user")) {
	            sender.sendMessage(cleanTitle("Box v" + plugin.getDescription().getVersion(), "="));
	           	sender.sendMessage("/box "+ ChatColor.GOLD +"<- Opens your multiserver inventory");
	            if(sender.hasPermission("box.admin")){
	            	sender.sendMessage("/box <player>"+ ChatColor.GOLD +" <- Opens the multiserver inventory of <player>");
		        }
	        }else{
	        	Teller.tell(sender, Type.Error, "You're not allowed to use this command.");
	        }
	    	return true;
			// General help
	        
    	}
        Teller.tell(sender, Type.Error, "Only players are able to open chests.");
		return true;
    }
    
    public boolean executeBox(CommandSender sender, String[] args) {
    	if (sender instanceof CraftPlayer) {
			if (args.length == 0) {
				if (sender.hasPermission("box.user")) {
					VirtualChest chest = chestManager.getChest(sender.getName());
					EntityPlayer player = ((CraftPlayer) sender).getHandle();
					player.openContainer(chest);
				} else {
					Teller.tell(sender, Type.Error, "You're not allowed to use this command.");
				}
				return true;
			} else if (args.length == 1) {
				if (sender.hasPermission("box.admin")) {
					VirtualChest chest = chestManager.getChest(args[0]);
					EntityPlayer player = ((CraftPlayer) sender).getHandle();
					player.openContainer(chest);
				} else {
					Teller.tell(sender, Type.Error, "You're not allowed to open other user's chests.");
				}
				return true;

			} else {
				return false;
			}
		} else {
			Teller.tell(sender, Type.Error, "Only players are able to open chests.");
			return true;
		}
    }
    
    public String cleanTitle(String title, String fill) { // Formats a string with a provided title and padding and centers title
        int chatWidthMax = 53; // Vanilla client line character max
        int titleWidth = title.length() + 2; // Title's character width with 2 spaces padding
        int fillWidth = (int) ((chatWidthMax - titleWidth) / 2D); // Fill string calculation for padding either side
        String cleanTitle = "";
        
        for(int i = 0; i < fillWidth; i++)
            cleanTitle += fill;
        cleanTitle += " " + title + " ";
        for(int i = 0; i < fillWidth; i++)
            cleanTitle += fill;
        
        return cleanTitle;
    }
    
    /**
     * @return the commands
     */
    public List<Boolean> getCommands() {
        return commands;
    }

}
