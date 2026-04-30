package com.pluralsight.util;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInput {
    static Scanner scanner = new Scanner(System.in);
    public static final String arrow = ConsoleUtilities.ACCENT + "❯ " + ConsoleUtilities.RESET;

    public UserInput(){

    }

    public static int promptForInt(String prompt, int min, int max) {
        String input;
        int value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine();
            try{
                value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    inputValid = true;
                } else if (value <= min) {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too high...");
                    System.out.print(prompt);
                }
            } catch(Exception e) {
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    public static double promptForDouble(String prompt, double min, double max) {
        String input;
        double value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    inputValid = true;
                } else if (value <= min) {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too high...");
                    System.out.print(prompt);
                }
            } catch (Exception e){
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    public static double promptForDouble(String prompt, double min) {
        String input;
        double value = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);


        while (!inputValid) {
            input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value >= min) {
                    inputValid = true;
                } else {
                    System.out.print(ConsoleUtilities.ERROR);
                    System.out.println(" The value you entered was too little...");
                    System.out.print(prompt);
                }
            } catch (NumberFormatException e) {
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println("You did not provide a valid input try again...");
                System.out.print(prompt);
            }
        }
        return value;
    }

    public static String promptForString(String prompt, String comparative) {
        String input = "";
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase(comparative)) {
                inputValid = true;
            } else {
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println(" You did not enter a valid menu option");
                System.out.print(prompt);
            }

        }
        return input;
    }

    public static String promptForString(String prompt) {
        prompt += arrow;
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static char promptForChar(String prompt){
        char returnValue = 0;
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);

        while (!inputValid) {
            String input = scanner.nextLine().toUpperCase();
            if (input.length() == 1) {
                returnValue = input.charAt(0);
                inputValid = true;
            } else {
                System.out.println(ConsoleUtilities.ERROR + "You did not enter a valid menu option");
                System.out.println(prompt);
            }
        }
        return returnValue;
    }

    public static char promptForChar(String prompt, String validChars) {
        String valid = validChars.toUpperCase();
        while (true) {
            char input = promptForChar(prompt);
            if (valid.indexOf(input) >= 0) {
                return input;
            }
            System.out.println(ConsoleUtilities.ERROR + " Invalid choice... Try again");
        }
    }



    public static LocalDate promptForDate(String prompt){
        boolean inputValid = false;
        prompt += arrow;
        System.out.print(prompt);
        LocalDate date = LocalDate.now();

        while (!inputValid){
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input);
                inputValid = true;
                return date;

            }catch (Exception e){
                System.out.print(ConsoleUtilities.ERROR);
                System.out.println(" You did not enter a valid date try again...");
                System.out.println(prompt);
            }
        }

        return date;
    }

}
