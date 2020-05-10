package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;

public interface PlayerActions {

    String askForGuess(Player p, Player opp);

    void compMakeGuess(Player comp, Player user);
}
