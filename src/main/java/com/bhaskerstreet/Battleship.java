package com.bhaskerstreet;

import com.bhaskerstreet.builders.ComputerPlayer;
import com.bhaskerstreet.builders.GameBuilder;

public class Battleship {

    public static void main(String[] args) {


        new GameBuilder()
                .init(new Player())
                .setComputer(new ComputerPlayer.ComputerPlayerBuilder().build())
                .start();
    }

}