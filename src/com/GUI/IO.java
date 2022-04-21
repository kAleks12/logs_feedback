package com.GUI;

import java.io.IOException;
import java.util.Scanner;

public class IO {

    public static String getDecisionInput() {
        Scanner scanner = new Scanner(System.in);
        promptUser("Your input: ");
        return scanner.nextLine();
    }

    public static String getDataInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void promptUser(String prompt){
        System.out.println(prompt);
    }

    public static void CLS() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}