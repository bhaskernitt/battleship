package com.bhaskerstreet.validator;

import com.bhaskerstreet.Player;

public interface Validator {

    boolean isInvalidPosition(int row, int col, int dir, Player p, int count);

}
