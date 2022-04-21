package com.LOGIC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOp {

    public static Scanner openFileRead (String filePath) throws FileNotFoundException {
        if (Files.exists(Path.of(filePath))) {
            File file = new File(filePath);
            return new Scanner(file);
        }
        return null;
    }

    public static PrintWriter openFileWrite (String filePath) throws IOException {
        if (!Files.exists(Path.of(filePath)))
            Files.createFile(Path.of(filePath));

        File file = new File(filePath);
        return new PrintWriter(file);
    }

    public static String readLines(Scanner openedFile) {

        if(openedFile != null){
            if(openedFile.hasNextLine()){
                return openedFile.nextLine();
            }
        }
        return "!~~^^~~!";
    }

    public static String readChunk(Scanner openedFile , String endingLine) {
        StringBuilder finalString = new StringBuilder();

        if(openedFile != null) {

            String currLine;
            boolean firstLineAdded = false;

            while (openedFile.hasNextLine() ) {
                currLine = openedFile.nextLine();
                if(currLine.equals(endingLine))
                    return finalString.toString();
                else {
                    if(firstLineAdded)
                        finalString.append("\n").append(currLine);
                    else{
                        finalString.append(currLine);
                        firstLineAdded = true;
                    }
                }
            }
        }
        return "!~~^^~~!";
    }
}