package com.bhaskerstreet.game;

public class UserInput {

    private String row;
    private int col;
    private int dir;
    private int len;
    private boolean isInserted;

    public UserInput(String row, int col, int dir, int len) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.len = len;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public boolean isInserted() {
        return isInserted;
    }

    public void setInserted(boolean inserted) {
        isInserted = inserted;
    }
}
