package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.Randomizer;
import com.bhaskerstreet.convertor.Converter;
import com.bhaskerstreet.helpers.GameHelper;
import com.bhaskerstreet.loaders.InputConsoleReader;

public abstract class GameAbstract extends Game {



    @Override
    public String askForGuess(Player p, Player opp) {
        System.out.println("Viewing My Guesses:");
        p.oppGrid.printStatus();

        int row = -1;
        int col = -1;

        String oldRow = "Z";
        int oldCol = -1;

        while (true) {
            String userInputRow = "Z";
            UserInput userInput= GameHelper.askUserForGuess();

            userInputRow = userInput.getRow().toUpperCase();
            oldRow = userInputRow;
            row = Converter.convertLetterToInt(userInputRow);
            col = Converter.convertUserColToProCol(userInput.getCol());

            //System.out.println("DEBUG: " + row + col);

            if (col >= 0 && col <= 9 && row != -1)
                break;

            System.out.println("Invalid location!");
        }

        if (opp.playerGrid.hasShip(row, col)) {
            p.oppGrid.markHit(row, col);
            opp.playerGrid.markHit(row, col);
            return "** USER HIT AT " + oldRow + oldCol + " **";
        } else {
            p.oppGrid.markMiss(row, col);
            opp.playerGrid.markMiss(row, col);
            return "** USER MISS AT " + oldRow + oldCol + " **";
        }
    }

    @Override
    public void compMakeGuess(Player comp, Player user) {
        Randomizer rand = new Randomizer();
        int row = rand.nextInt(0, 9);
        int col = rand.nextInt(0, 9);

        // While computer already guessed this posiiton, make a new random guess
        while (comp.oppGrid.alreadyGuessed(row, col)) {
            row = rand.nextInt(0, 9);
            col = rand.nextInt(0, 9);
        }

        if (user.playerGrid.hasShip(row, col)) {
            comp.oppGrid.markHit(row, col);
            user.playerGrid.markHit(row, col);
            System.out.println("COMP HIT AT " + Converter.convertIntToLetter(row) + Converter.convertCompColToRegular(col));
        } else {
            comp.oppGrid.markMiss(row, col);
            user.playerGrid.markMiss(row, col);
            System.out.println("COMP MISS AT " + Converter.convertIntToLetter(row) + Converter.convertCompColToRegular(col));
        }


        System.out.println("\nYOUR BOARD...PRESS ENTER TO CONTINUE...");
        InputConsoleReader.getInstance().nextLine();
        user.playerGrid.printCombined();
        System.out.println("PRESS ENTER TO CONTINUE...");
        InputConsoleReader.getInstance().nextLine();
    }
}
