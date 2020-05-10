package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.exceptions.InputFileNotExist;
import com.bhaskerstreet.helpers.GameHelper;

public class GameImpl extends GameAbstract {

    private Player userPlayer;
    private Player computer;
    private String filePath;
    private boolean loadFromFile;


    public GameImpl(Player userPlayer, Player computer, String filePath, boolean loadFromFile) {
        this.userPlayer = userPlayer;
        this.computer = computer;
        this.filePath = filePath;
        this.loadFromFile = loadFromFile;
    }


    @Override
    public void init() {

        if (loadFromFile) {
            String setupFromFile = GameStarterOption.getInstance().load(filePath);

            if (!GameStarterOption.getInstance().isFileExists) {
                throw new InputFileNotExist("filePath [" + filePath + " ] doesnt exist");
            }
            getInputsFromUser(userPlayer, GameHelper.parseString(setupFromFile));
        } else {
            getInputsFromUser(userPlayer);

        }
        System.out.println("\nPlayer SETUP:");


    }

    @Override
    public void play() {
        while (true) {
            System.out.println("\nUSER MAKE GUESS:");
            askForGuess(userPlayer, computer);
            if (userPlayer.playerGrid.hasLost()) {
                System.out.println("HIT!...WINNER = COMPUTER, LOSER=USER ");
                System.out.println("Thanks for playing..BhaskerStreets...battleship..");
                break;
            } else if (computer.playerGrid.hasLost()) {
                System.out.println("HIT!...WINNER = USER, LOSER= COMPUTER ");
                System.out.println("Thanks for playing..BhaskerStreets...battleship..");
                break;
            }
            System.out.println("\nCOMPUTER IS MAKING GUESS...");
            compMakeGuess(computer, userPlayer);
        }
    }
}
