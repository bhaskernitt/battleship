package com.bhaskerstreet.builders;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.Randomizer;
import com.bhaskerstreet.Ship;
import com.bhaskerstreet.loaders.InputConsoleReader;

public class ComputerPlayer {

    public static class ComputerPlayerBuilder {
        private Player player;

        private static boolean hasErrorsComp(int row, int col, int dir, Player p, int count) {
            //System.out.println("DEBUG: count arg is " + count);

            int length = p.ships[count].getLength();

            // Check if off grid - Horizontal
            if (dir == 0) {
                int checker = length + col;
                //System.out.println("DEBUG: checker is " + checker);
                if (checker > 10) {
                    return true;
                }
            }

            // Check if off grid - Vertical
            if (dir == 1) // VERTICAL
            {
                int checker = length + row;
                //System.out.println("DEBUG: checker is " + checker);
                if (checker > 10) {
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
                        return true;
                    }
                }
            } else if (dir == 1) // Vertical
            {
                // For each location a ship occupies, check if ship is already there
                for (int i = row; i < row + length; i++) {
                    //System.out.println("DEBUG: row = " + row + "; col = " + i);
                    if (p.playerGrid.hasShip(i, col)) {
                        return true;
                    }
                }
            }

            return false;
        }

        public Player build() {
            System.out.println();
            int normCounter = 0;
            player = new Player();

            Randomizer rand = new Randomizer();

            while (player.numOfShipsLeft() > 0) {
                for (Ship s : player.ships) {
                    int row = rand.nextInt(0, 9);
                    int col = rand.nextInt(0, 9);
                    int dir = rand.nextInt(0, 1);

                    //System.out.println("DEBUG: row-" + row + "; col-" + col + "; dir-" + dir);

                    while (hasErrorsComp(row, col, dir, player, normCounter)) // while the random nums make error, start again
                    {
                        row = rand.nextInt(0, 9);
                        col = rand.nextInt(0, 9);
                        dir = rand.nextInt(0, 1);
                        //System.out.println("AGAIN-DEBUG: row-" + row + "; col-" + col + "; dir-" + dir);
                    }

                    //System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
                    player.ships[normCounter].setLocation(row, col);
                    player.ships[normCounter].setDirection(dir);
                    player.playerGrid.addShip(player.ships[normCounter]);

                    normCounter++;
                }
            }
            System.out.println("Computer SETUP...DONE...PRESS ENTER TO CONTINUE...");
            InputConsoleReader.getInstance().nextLine();
            System.out.println("\nCOMPUTER GRID (FOR DEBUG)...");
            player.playerGrid.printShips();
            return player;
        }

    }
}
