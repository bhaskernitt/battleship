package com.bhaskerstreet;

import com.bhaskerstreet.helpers.GameHelper;

public class Grid {
    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    private Location[][] grid;
    private int points;

    public Grid() {
        if (NUM_ROWS > 26) {
            throw new IllegalArgumentException("ERROR! NUM_ROWS CANNOT BE > 26");
        }

        grid = new Location[NUM_ROWS][NUM_COLS];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Location tempLoc = new Location();
                grid[row][col] = tempLoc;
            }
        }
    }

    // Mark a hit in this location by calling the markHit method
    // on the Location object.  
    public void markHit(int row, int col) {
        grid[row][col].markHit();
        points++;
    }

    // Mark a miss on this location.    
    public void markMiss(int row, int col) {
        grid[row][col].markMiss();
    }

    // Set the status of this location object.
    public void setStatus(int row, int col, int status) {
        grid[row][col].setStatus(status);
    }

    // Get the status of this location in the grid  
    public int getStatus(int row, int col) {
        return grid[row][col].getStatus();
    }

    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col) {
        return !grid[row][col].isUnguessed();
    }

    // Set whether or not there is a ship at this location to the val   
    public void setShip(int row, int col, boolean val) {
        grid[row][col].setShip(val);
    }

    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col) {
        return grid[row][col].hasShip();
    }


    public void printStatus() {
        GameHelper.printOutput(0, grid);
    }

    public void printShips() {
        GameHelper.printOutput(1, grid);
    }


    public void printCombined() {
        GameHelper.printOutput(2, grid);
    }

    public boolean hasLost() {
        if (points >= 17)
            return true;
        else
            return false;
    }

    public void addShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int length = s.getLength();
        int dir = s.getDirection();

        if (!(s.isDirectionSet()) || !(s.isLocationSet()))
            throw new IllegalArgumentException("ERROR! Direction or Location is unset/default");

        // 0 - hor; 1 - ver
        if (dir == 0) // Hortizontal
        {
            for (int i = col; i < col + length; i++) {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                updateShipParams(i, col, length, dir);
            }
        } else if (dir == 1) // Vertical
        {
            for (int i = row; i < row + length; i++) {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                updateShipParams(i, col, length, dir);
            }
        }
    }

    private void updateShipParams(int i, int col, int length, int dir) {
        grid[i][col].setShip(true);
        grid[i][col].setLengthOfShip(length);
        grid[i][col].setDirectionOfShip(dir);
    }


}