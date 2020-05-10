package com.bhaskerstreet.constants;

public enum Direction {
    UNSET(-1),
    HORIZONTAL(0),
    VERTICAL(1);

    private final int dir;

    Direction(int dir) {
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }
}
