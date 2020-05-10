package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.Ship;
import com.bhaskerstreet.convertor.Converter;
import com.bhaskerstreet.helpers.GameHelper;

import java.util.List;

public abstract class GameInitializerAbstract implements GameInitializer {


    @Override
    public void getInputsFromUser(Player userPlayer) {
//userPlayer.playerGrid.printShips();

        System.out.println();
        int counter = 1;
        int normCounter = 0;
        while (userPlayer.numOfShipsLeft() > 0) {
            for (Ship s : userPlayer.ships) {
                System.out.println("\nShip #" + counter + ": Length-" + s.getLength());
                int row = -1;
                int col = -1;
                int dir = -1;
                while (true) {

                    UserInput userInput = GameHelper.askUserInputs();

                    row = Converter.convertLetterToInt(userInput.getRow().toUpperCase());
                    col = Converter.convertUserColToProCol(userInput.getCol());
                    dir = userInput.getDir();

                    if (mapToUser(userPlayer, normCounter, row, col, dir)) // Check if errors will produce (out of bounds)
                    {
                        break;
                    }
                    System.out.println("Invalid location!");
                }

                System.out.println();
                System.out.println("You have " + userPlayer.numOfShipsLeft() + " remaining ships to place.");

                normCounter++;
                counter++;
            }
        }
    }

    @Override
    public void getInputsFromUser(Player userPlayer, List<UserInput> list) {
        System.out.println();
        int counter = 1;
        int normCounter = 0;
        int row = -1;
        int col = -1;
        int dir = -1;
        while (userPlayer.numOfShipsLeft() > 0) {
            for (Ship s : userPlayer.ships) {
                System.out.println("\nShip #" + counter + ": Length-" + s.getLength());
                for (UserInput userInput : list) {

                    if (!userInput.isInserted() && s.getLength() == userInput.getLen()) {
                        row = Converter.convertLetterToInt(userInput.getRow());
                        col = Converter.convertUserColToProCol(userInput.getCol());
                        dir = userInput.getDir();
                        userInput.setInserted(true);
                        break;
                    }
                }
                if (!mapToUser(userPlayer, normCounter, row, col, dir)) // Check if errors will produce (out of bounds)
                {
                    System.out.println("Invalid location!");
                }

                System.out.println();
                System.out.println("You have " + userPlayer.numOfShipsLeft() + " remaining ships to place.");

                normCounter++;
                counter++;
            }
        }
        userPlayer.playerGrid.printShips();
    }

    private boolean mapToUser(Player userPlayer, int counter, int row, int col, int dir) {

        if (!GameStarterOption.getInstance().isInvalidPosition(row, col, dir, userPlayer, counter)) // Check if errors will produce (out of bounds)
        {
            userPlayer.ships[counter].setLocation(row, col);
            userPlayer.ships[counter].setDirection(dir);
            userPlayer.playerGrid.addShip(userPlayer.ships[counter]);
            userPlayer.playerGrid.printShips();
            return true;
        }
        return false;
    }
}
