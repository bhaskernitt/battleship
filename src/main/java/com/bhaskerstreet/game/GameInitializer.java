package com.bhaskerstreet.game;

import com.bhaskerstreet.Player;

import java.util.List;

public interface GameInitializer extends PlayerActions {
    void getInputsFromUser(Player userPlayer, List<UserInput> list);

    void getInputsFromUser(Player userPlayer);

}
