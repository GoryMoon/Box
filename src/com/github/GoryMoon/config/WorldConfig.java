package com.github.GoryMoon.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import com.github.GoryMoon.ActionType;

public class WorldConfig {

    private final boolean[] actions = new boolean[ActionType.length];
    private final boolean ignored;
    private final Set<String> ignoredPlayers;

    public WorldConfig(Collection<WorldConfig> worlds) {
        for (final ActionType action : ActionType.values()) {
            actions[action.ordinal()] = false;
            for (final WorldConfig wcfg : worlds) {
                if (wcfg.isLogging(action)) {
                    actions[action.ordinal()] = true;
                }
            }
        }
        ignored = false;
        ignoredPlayers = new HashSet<String>();
    }

    public WorldConfig(final ConfigurationSection cfg) {
        for (final ActionType action : ActionType.values()) {
            actions[action.ordinal()] = cfg.getBoolean(action.getConfigPath());
        }
        ignored = cfg.getBoolean("ignored");
        ignoredPlayers = new HashSet<String>();

        // Cicadia:
        //ignoredPlayers = new HashSet<String>(cfg.getStringList("ignoredPlayers"));
        
        // Issue #74 [patch] (ignoredPlayers doesn't have to be typed in lowercase)
        
        // Load the ignoredPlayers into HashSet and lowercase all player names.
        for (String player : cfg.getStringList("ignoredPlayers")) {
            ignoredPlayers.add(player.toLowerCase());
        }
    }

    public boolean isIgnored() {
        return ignored;
    }

    public boolean isIgnored(String playerName) {
        return ignoredPlayers.contains(playerName.toLowerCase());
    }

    public boolean isLogging(ActionType action) {
        return actions[action.ordinal()];
    }
}