package com.github.employees.entities;

public enum EntityStatus {
    off, on;

    public static EntityStatus choose(boolean isLocked) {
        return isLocked ? off : on;
    }

}
