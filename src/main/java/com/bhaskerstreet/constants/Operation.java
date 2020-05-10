package com.bhaskerstreet.constants;

public enum Operation {
    UNGUESSED(0),
    HIT(1),
    MISSED(2);

    private int operation;

    Operation(int operation) {
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }
}
