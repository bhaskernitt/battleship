package com.bhaskerstreet.builders;

import com.bhaskerstreet.Player;
import com.bhaskerstreet.game.Game;
import com.bhaskerstreet.game.GameImpl;
import com.bhaskerstreet.loaders.InputConsoleReader;

import java.util.Scanner;

public class GameBuilder {
    private Player user;
    private Player computer;
    private String filePath;
    private boolean isLoadFromFile;
    private Game game;


    public GameBuilder setComputer(Player player) {
        computer = player;
        return this;
    }


    public GameBuilder init(Player player) {
        user = player;
        initMessage();
        Scanner sc = InputConsoleReader.getInstance();
        int option = sc.nextInt();
        switch (option) {
            case 1: {
                isLoadFromFile = true;
                System.out.println("copy file path..");
                sc.nextLine();
                filePath = sc.nextLine();
                System.out.println();
                break;
            }
            case 2: {
                break;
            }
            default: {
                System.out.println("choose valid option...");
            }
        }
        game = new GameImpl(user, computer, filePath, isLoadFromFile);
        game.init();

        return this;
    }

    public void start() {
        game.play();
    }

    private void initMessage() {
        System.out.println("\n\n-------------------------------------------");
        System.out.println("## Choose options to start the game....");
        System.out.println("## 1:press 1 to load inputs from file");
        System.out.println("## 2:press 2 to start reading inputs from console");
        System.out.println("-------------------------------------------");
    }

}
