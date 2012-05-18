package com.github.GoryMoon;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.GoryMoon.commands.BoxCommandExecutor;

public class Box extends JavaPlugin{
	
	private VirtualChestManager chestManager;
    private BoxCommandExecutor commandExecutor;
    
    /*Config Start*/
    
    public boolean debug;
    public String tablePrefix = "box_", host = "localhost";
    public String port = "3306";
    public String databaseid = "minecraft", username = "root", password = "''";
    public int maxConnections = 10;
    public PluginDescriptionFile bridgeDescription;
    public FileConfiguration config;
	private Logger log;
    
    /*Config End*/
    
    @Override
	public void onEnable(){ 
    	log = getLogger();
    	
    	// Save default config.yml
		if (!new File(getDataFolder(), "config.yml").exists()){
			saveDefaultConfig();
		}
    	
    	// Initialize
    	chestManager = new VirtualChestManager(this, new File(getDataFolder(), "chests"));
    	Config();
    	
    	//Register command
    	commandExecutor = new BoxCommandExecutor(chestManager,this);
    	getCommand("box").setExecutor(commandExecutor);
    	
    	// Schedule auto-saving
    			int autosaveInterval = getConfig().getInt("autosave") * 1200;
    			if (autosaveInterval > 0) {
    				getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
    					public void run() {
    						int savedChests = chestManager.save(false);
    						if (savedChests > 0 && !getConfig().getBoolean("silentAutosave"))
    							log.info("auto-saved " + savedChests + " chests");
    					}
    				}, autosaveInterval, autosaveInterval);
    			}
    	
    	log.info("version " + getDescription().getVersion() + " enabled");
    }
    
    public void Config() {
    	config = getConfig();
        tablePrefix = config.getString("bridge.tablePrefix");
        host = config.getString("bridge.host");
        port = config.getString("bridge.port");
        databaseid = config.getString("bridge.database");
        username = config.getString("bridge.username");
        password = config.getString("bridge.password");
        maxConnections = config.getInt("bridge.maxConnections");
        saveConfig();
    }
    
    
	public void onDisable(){
		int savedChests = chestManager.save(false);

		log.info("saved " + savedChests + " chests");
		log.info("version [" + getDescription().getVersion() + "] disabled");
	}
}
