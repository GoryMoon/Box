package com.github.GoryMoon.tools;

public enum ToolMode {

    CLEARLOG("box.clearlog"), LOOKUP("box.lookup"), REDO("box.redo"), ROLLBACK("box.rollback"), UNDO("box.undo"), WRITELOGFILE("box.writelog");
    private final String permission;

    private ToolMode(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
