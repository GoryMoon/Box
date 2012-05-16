package com.github.GoryMoon;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * Class for parsing managing player's {@PlayerSession}s
 * 
 */
public class SessionManager {

    private HashMap<String, PlayerSession> playerSessions = new HashMap<String, PlayerSession>();

    public SessionManager() {
        // Add console session
        playerSessions.put(Bukkit.getServer().getConsoleSender().getName(), new PlayerSession(Bukkit.getServer().getConsoleSender()));
    }

    /**
     * @param player
     * @return Whether the sender has a session
     */
    public boolean hasSession(CommandSender player) {
        return playerSessions.containsKey(player.getName());
    }

    /**
     * Get a PlayerSession from the list, create a new session if the sender has
     * no session
     * 
     * @param player
     * @return
     */
    public PlayerSession getSession(CommandSender player) {
        PlayerSession session = playerSessions.get(player.getName());
        if (session == null) {
            session = new PlayerSession(player);
            playerSessions.put(player.getName(), session);
        }
        return session;
    }
}