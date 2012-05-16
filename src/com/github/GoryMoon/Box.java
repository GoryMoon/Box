package com.github.GoryMoon;

import java.io.File;

import lib.PatPeter.SQLibrary.Database;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.GoryMoon.commands.BoxCommandExecutor;
import com.github.GoryMoon.config.Config;
import com.github.GoryMoon.util.BukkitUtils;;

public class Box extends JavaPlugin{
	
	// Plugins
	private static Box box;
	// Config and commands
	private Config conf;
    private BoxCommandExecutor commandExecutor;
    //Database, consummer ...
    private SessionManager sessionMan;
    private final int pluginId = 1;
    private Database database;
    
    
    @Override
	public void onEnable(){ 
		//Load configuration
    	conf = new Config();
    	// Initialize commands
        // Testing Rebase
    	commandExecutor = new BoxCommandExecutor();
   //     getCommand("box").setExecutor(commandExecutor);
        // Lets get some bridge action going
   //     final File file = new File(getDataFolder() + File.separator + getConf().bridgeName);
        
        
        //Activate Listener
   //     if (conf.superWorldConfig.isLogging(ActionType.INVENTORY_CLICK)) {
   //         new InventoryClick();
        }
        
		 
   // }
	 
	public void onDisable(){ 
	 
	}
	
	public Config getConf() {
        return conf;
    }
	
	public BoxCommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
	
	public int getPluginId() {
        return pluginId;
    }


	public static Box getInstance() {
		return box;
	}


	public SessionManager getSessionManager() {
		return sessionMan;
	}
	
	public Database getDatabaseBridge() {
        return database;
    }
	
}
