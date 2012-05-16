package com.github.GoryMoon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.GoryMoon.entries.Entry;
import com.github.GoryMoon.params.QueryParams;
import com.github.GoryMoon.tools.SessionToolData;
import com.github.GoryMoon.tools.Tool;

public class PlayerSession {

    private CommandSender sender;
    private Map<Tool, SessionToolData> toolDatas;
    private List<Entry> entryCache;
    private QueryParams lastQuery;

    public PlayerSession(CommandSender sender) {
        this.sender = sender;
        toolDatas = new HashMap<Tool, SessionToolData>();
        if (sender instanceof Player) {
            for (final Tool tool : Box.getInstance().getConf().tools) {
                toolDatas.put(tool, new SessionToolData(tool, (Player) sender));
            }
        }
    }

    public CommandSender getSender() {
        return sender;
    }

    public Map<Tool, SessionToolData> getToolDatas() {
        return toolDatas;
    }

    public List<Entry> getEntryCache() {
        return entryCache;
    }

    public void setEntryCache(List<Entry> entryCache) {
        this.entryCache = entryCache;
    }

    public QueryParams getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(QueryParams lastQuery) {
        this.lastQuery = lastQuery;
    }
}
