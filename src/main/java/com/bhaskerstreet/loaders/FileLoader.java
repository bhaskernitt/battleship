package com.bhaskerstreet.loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public  abstract class FileLoader implements Loader {

    public boolean isFileExists;




    @Override
    public String load(String filePath) {
        String result = null;
        try {
            result = new String(Files.readAllBytes(Paths.get(filePath)));
            isFileExists = true;
        } catch (IOException e) {


        }
        return result;

    }
}
