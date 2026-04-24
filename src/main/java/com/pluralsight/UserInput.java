package com.pluralsight;

import java.awt.*;
import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);

    public UserInput(){

    }

    public static int promptForInt(String prompt, int min, int max) {
        int input = 0;
        boolean inputValid = false;
        System.out.print(prompt);

        while (!inputValid) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    inputValid = true;
                } else if (input <= min) {
                    System.out.print(ColorUtilities.ERROR);
                    System.out.println(" The valid you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ColorUtilities.ERROR);
                    System.out.println(" The valid you entered was too high...");
                    System.out.print(prompt);
                }
            } else {
                System.out.print(ColorUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.println(prompt);
            }
        }
        return input;
    }

    public static double promptForDouble(String prompt, double min, double max) {
        double input = 0;
        boolean inputValid = false;
        System.out.print(prompt);

        while (!inputValid) {
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input >= min && input <= max) {
                    inputValid = true;
                } else if (input <= min) {
                    System.out.print(ColorUtilities.ERROR);
                    System.out.println(" The valid you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ColorUtilities.ERROR);
                    System.out.println(" The valid you entered was too high...");
                    System.out.print(prompt);
                }
            } else {
                System.out.print(ColorUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.println(prompt);
            }
        }
        return input;
    }


}
