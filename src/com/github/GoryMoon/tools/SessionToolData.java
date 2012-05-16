package com.github.GoryMoon.tools;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class SessionToolData {

    private boolean enabled = false;
    private ToolMode mode;
    private List<String> params;

    public SessionToolData(Tool tool, Player player) {
        enabled = tool.defaultEnabled && player.hasPermission("guardian.tools." + tool.name);
        params = new ArrayList<String>(tool.params);
        mode = tool.mode;
    }

    public ToolMode getMode() {
        return mode;
    }

    public List<String> getParams() {
        return params;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setMode(ToolMode mode) {
        this.mode = mode;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}