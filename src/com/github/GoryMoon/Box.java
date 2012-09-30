package com.github.GoryMoon;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import lib.PatPeter.SQLibrary.*;

import com.github.GoryMoon.commands.BoxCommandExecutor;

public class Box extends JavaPlugin{
	
	private VirtualChestManager chestManager;
    private BoxCommandExecutor commandExecutor;
    
    /*Config Start*/
    
    public boolean debug;
    public String tablePrefix = "box_", host = "localhost";
    public String port = "3306";
    public String databaseid = "minecraft", username = "root", password = "";
    public int maxConnections = 10;
    public PluginDescriptionFile bridgeDescription;
    public FileConfiguration config;
	Logger log;
	MySQL mysql;
    
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
    	
    	// Mysql init	
        String query1 ="CREATE TABLE `"+ tablePrefix +"inv` ( \t`player` VARCHAR(50) NOT NULL,";
        for(int i=0;i<=54;i++){
        	query1 += (" \t`slot"+i+"` INT(10) NULL DEFAULT NULL,");
        }
        for(int i=0;i<=54;i++){
        	query1 += (" \t`data"+i+"` INT(10) NULL DEFAULT '0',");
        }
        query1 += " \tUNIQUE INDEX `player` (`player`) ) COLLATE='latin1_swedish_ci' ENGINE=MyISAM ROW_FORMAT=DEFAULT ";
        
        mysql = new MySQL(log,
                "",
                host,
                port,
                databaseid,
                username,
                password);
		try {
		  mysql.open();
		  if(!mysql.checkTable(tablePrefix +"inv")){
			  mysql.createTable(query1);
		  }
		} catch (Exception e) {
		  log.info(e.getMessage());
		}
		
    	
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
        host = config.getString("bridge.host");
        port = config.getString("bridge.port");
        //databaseid = config.getString("bridge.databaseid");
        username = config.getString("bridge.username");
        password = config.getString("bridge.password");
        maxConnections = config.getInt("bridge.maxConnections");
        saveConfig();
    }
    
    
	public void onDisable(){
		int savedChests = chestManager.save(false);

		log.info("saved " + savedChests + " chests");
		log.info("version [" + getDescription().getVersion() + "] disabled");
		mysql.close();
	}
}
