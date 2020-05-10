package com.bhaskerstreet.loaders;

import java.util.Scanner;

public class InputConsoleReader {

    //create an object of SingleObject
    public static Scanner reader = new Scanner(System.in);

    //make the constructor private so that this class cannot be
    //instantiated
    private InputConsoleReader() {
    }

    //Get the only object available
    public static Scanner getInstance() {
        return reader;
    }
}
