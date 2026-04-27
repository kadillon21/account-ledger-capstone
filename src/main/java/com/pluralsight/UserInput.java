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

    public static double promptForDouble(String prompt, double min) {
        double input = 0;
        boolean inputValid = false;
        System.out.print(prompt);

        while (!inputValid) {
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input >= min) {
                    inputValid = true;
                } else {
                    System.out.print(ColorUtilities.ERROR);
                    System.out.println(" The valid you entered was too little...");
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

    public static String promptForString(String prompt, String comparative) {
        String input = "";
        boolean inputValid = false;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(comparative)) {
                inputValid = true;
            } else {
                System.out.print(ColorUtilities.ERROR);
                System.out.println(" You did not enter a valid menu option");
                System.out.print(prompt);
            }

        }
        return input;
    }

    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static char promptForChar(String prompt){
        char returnValue = 0;
        boolean inputValid = false;
        System.out.print(prompt);

        while (!inputValid) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                returnValue = input.charAt(0);
                inputValid = true;
            } else {
                System.out.print(ColorUtilities.ERROR);
                System.out.println(" You did not enter a valid menu option");
                System.out.println(prompt);
            }
        }
        return returnValue;
    }



/*
    public static char promptForChar(String prompt, String[] alternatives){
        char returnValue = 0;
        boolean inputValid = false;
        System.out.print(prompt);
        String input = scanner.nextLine();

        while (!inputValid) {
            if (input.length() == 1) {
                returnValue = input.charAt(0);
                inputValid = true;
            }
            else {
                for(String alt : alternatives){

                }
                System.out.print(ColorUtilities.ERROR);
                System.out.println(" You did not enter a valid menu option");
                System.out.println(prompt);
            }
        }
        return returnValue;
    }
*/


}
