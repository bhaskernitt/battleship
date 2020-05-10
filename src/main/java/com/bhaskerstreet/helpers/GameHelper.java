package com.bhaskerstreet.helpers;

import com.bhaskerstreet.Location;
import com.bhaskerstreet.convertor.Converter;
import com.bhaskerstreet.game.UserInput;
import com.bhaskerstreet.loaders.InputConsoleReader;

import java.util.ArrayList;
import java.util.List;

public final class GameHelper {
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    private GameHelper() {
    }

    public static List<UserInput> parseString(String setUpFromFile) {
        String[] setupArray = setUpFromFile.split(System.lineSeparator());
        List<UserInput> generatedListFromFile = new ArrayList<>();
        for (String sr1 : setupArray) {

            if (!sr1.startsWith("#")) {
                String[] inner = sr1.split(",");


                String row = inner[0].substring(inner[0].indexOf("=") + 1);
                int col = Integer.parseInt(inner[1].substring(inner[1].indexOf("=") + 1));
                int len = Integer.parseInt(inner[2].substring(inner[2].indexOf("=") + 1));
                int dir = Integer.parseInt(inner[3].substring(inner[3].indexOf("=") + 1));

                UserInput userInput = new UserInput(row, col, dir, len);
                generatedListFromFile.add(userInput);

            }
        }
        return generatedListFromFile;
    }

    public static UserInput askUserForGuess() {
        System.out.print("Type in row (A-J): ");
        String userInputRow = InputConsoleReader.getInstance().next();

        System.out.print("Type in column (1-10): ");
        int userInputColumn = InputConsoleReader.getInstance().nextInt();

        return new UserInput(userInputRow, userInputColumn, -1, -1);
    }

    public static UserInput askUserInputs() {
        System.out.print("Type in row (A-J): ");
        String userInputRow = InputConsoleReader.getInstance().next();

        System.out.print("Type in column (1-10): ");
        int userInputColumn = InputConsoleReader.getInstance().nextInt();

        System.out.print("Type in direction (0-H, 1-V): ");
        int userInputDir = InputConsoleReader.getInstance().nextInt();

        return new UserInput(userInputRow, userInputColumn, userInputDir, -1);
    }

    // Type: 0 for status, 1 for ships, 2 for combined
    public static void printOutput(int type, Location[][] grid) {
        System.out.println();
        // Print columns (HEADER)
        System.out.print("  ");
        for (int i = 1; i <= NUM_COLS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Print rows
        int endLetterForLoop = (NUM_ROWS - 1) + 65;
        for (int i = 65; i <= endLetterForLoop; i++) {
            char theChar = (char) i;
            System.out.print(theChar + " ");

            for (int j = 0; j < NUM_COLS; j++) {
                if (type == 0) // type == 0; status
                {
                    if (grid[Converter.switchCounterToIntegerForArray(i)][j].isUnguessed())
                        System.out.print("- ");
                    else if (grid[Converter.switchCounterToIntegerForArray(i)][j].checkMiss())
                        System.out.print("O ");
                    else if (grid[Converter.switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print("X ");
                } else if (type == 1) // type == 1; ships
                {
                    if (grid[Converter.switchCounterToIntegerForArray(i)][j].hasShip()) {
                        // System.out.print("X ");
                        if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2) {
                            System.out.print("D ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3) {
                            System.out.print("C ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4) {
                            System.out.print("B ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5) {
                            System.out.print("A ");
                        }
                    } else if (!(grid[Converter.switchCounterToIntegerForArray(i)][j].hasShip())) {
                        System.out.print("- ");
                    }

                } else // type == 2; combined
                {
                    if (grid[Converter.switchCounterToIntegerForArray(i)][j].checkHit())
                        System.out.print("X ");
                    else if (grid[Converter.switchCounterToIntegerForArray(i)][j].hasShip()) {
                        // System.out.print("X ");
                        if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 2) {
                            System.out.print("D ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 3) {
                            System.out.print("C ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 4) {
                            System.out.print("B ");
                        } else if (grid[Converter.switchCounterToIntegerForArray(i)][j].getLengthOfShip() == 5) {
                            System.out.print("A ");
                        }
                    } else if (!(grid[Converter.switchCounterToIntegerForArray(i)][j].hasShip())) {
                        System.out.print("- ");
                    }
                }
            }
            System.out.println();
        }
    }
}
