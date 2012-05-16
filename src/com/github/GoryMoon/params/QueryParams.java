package com.github.GoryMoon.params;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import com.github.GoryMoon.ActionType;

public class QueryParams implements Cloneable {

    public List<ActionType> actions = new ArrayList<ActionType>();
    public long before, since;
    public List<Integer> blocks = new ArrayList<Integer>();
    public boolean excludePlayersMode = false;
    public int limit = -1;
    public Location loc = null;
    public List<String> players = new ArrayList<String>();
    public int radius = 0;
    public boolean silent = false;
    public String textMatch;
    public List<World> worlds = new ArrayList<World>();
    public boolean needId = false, needDate = false, needPlayer = false, needCoords = false, needSubTableText = false,
            needSubTableItem = false, needSubTableBlock = false, needSubTableDeath = false;

    /**
     * Constructs a new QueryParams with default values
     */
    public QueryParams() {
        // TODO Set default values
    }

    @Override
    public QueryParams clone() {
        return this;
    }
}