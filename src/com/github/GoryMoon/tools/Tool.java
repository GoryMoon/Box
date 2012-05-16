package com.github.GoryMoon.tools;

import java.util.List;

public class Tool {

    public final String name;
    public final List<String> aliases;
    public final ToolBehavior leftClickBehavior, rightClickBehavior;
    public final boolean defaultEnabled;
    public final int item;
    public final List<String> params;
    public final ToolMode mode;
    public final boolean giveTool;

    public Tool(String name, List<String> aliases, ToolBehavior leftClickBehavior, ToolBehavior rightClickBehavior, boolean defaultEnabled, int item, List<String> params, ToolMode mode, Boolean giveTool) {
        this.name = name;
        this.aliases = aliases;
        this.leftClickBehavior = leftClickBehavior;
        this.rightClickBehavior = rightClickBehavior;
        this.defaultEnabled = defaultEnabled;
        this.item = item;
        this.params = params;
        this.mode = mode;
        this.giveTool = giveTool;
    }
}