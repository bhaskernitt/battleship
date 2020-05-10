package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.loaders.FileLoader;
import com.bhaskerstreet.validator.Validator;

public class GameStarterOption extends FileLoader implements Validator {

    private static final GameStarterOption GAME_STARTER_OPTION = new GameStarterOption();

    public static GameStarterOption getInstance() {
        return GAME_STARTER_OPTION;
    }


    @Override
    public boolean isInvalidPosition(int row, int col, int dir, Player p, int count) {
        //System.out.println("DEBUG: count arg is " + count);

        if (!(col >= 0 && col <= 9 && row != -1 && dir != -1)) // Check valid input
        {
            return false;
        }

        int length = p.ships[count].getLength();

        // Check if off grid - Horizontal
        if (dir == 0) {
            int checker = length + col;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 10) {
                System.out.println("SHIP DOES NOT FIT");
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir == 1) // VERTICAL
        {
            int checker = length + row;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 10) {
                System.out.println("SHIP DOES NOT FIT");

                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col + length; i++) {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if (p.playerGrid.hasShip(row, i)) {
                    System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");


                    return true;
                }
            }
        } else if (dir == 1) // Vertical
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = row; i < row + length; i++) {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if (p.playerGrid.hasShip(i, col)) {

                    System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");

                    return true;
                }
            }
        }

        return false;
    }
}
