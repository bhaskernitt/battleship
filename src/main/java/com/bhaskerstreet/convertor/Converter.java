package com.bhaskerstreet.convertor;

public final class Converter {
    private Converter() {
    }


    public static String convertIntToLetter(int val) {
        if (val > 75 || val < 65) {
            return "Z";
        }
        return String.valueOf((char) val + 65);

    }

    public static int convertCompColToRegular(int val) {
        if (val >= 0 && val <= 9) {
            return val + 1;
        }
        return -1;

    }

    public static int convertUserColToProCol(int val) {

        if (val >= 1 && val <= 10) {
            return val - 1;
        }
        return -1;
    }


    public static int convertLetterToInt(String val) {
        if (val.compareTo("A") >= 0 || val.compareTo("Z") <= 0) {
            return val.charAt(0) - 65;
        }
        return -1;

    }

    public static int switchCounterToIntegerForArray(int val) {

        if (val >= 65 && val <= 90) {
            return val - 65;
        }
        throw new IllegalArgumentException("ERROR OCCURED IN SWITCH");
    }

}
