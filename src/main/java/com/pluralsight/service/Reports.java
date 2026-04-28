package com.pluralsight.service;


import com.pluralsight.model.SearchCriteria;
import com.pluralsight.model.Transaction;
import com.pluralsight.util.UserInput;
import com.pluralsight.ui.Menus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



public class Reports {

    static SearchCriteria searchCriteria = new SearchCriteria();

    public static void monthToDateReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(startOfMonth)
                        && !transaction.getDate().isAfter(now)).toList();
        Menus.displayTransactions(filtered);
    }

    public static void previousMonthReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousMonthStart = now.minusMonths(1).withDayOfMonth(1);
        LocalDate previousMonthEnd = previousMonthStart.withDayOfMonth(previousMonthStart.lengthOfMonth());
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousMonthStart)
                        && !transaction.getDate().isAfter(previousMonthEnd)).toList();
        Menus.displayTransactions(filtered);
    }

    public static void yearToDate(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousYearStart = now.withMonth(1).withDayOfMonth(1);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousYearStart)
                        && !transaction.getDate().isAfter(now)).toList();
        Menus.displayTransactions(filtered);
    }

    public static void previousYearReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousYearStart = now.minusYears(1).withMonth(1).withDayOfMonth(1);
        LocalDate previousYearEnd = previousYearStart.withMonth(12).withDayOfMonth(31);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousYearStart)
                        && !transaction.getDate().isAfter(previousYearEnd)).toList();
        Menus.displayTransactions(filtered);
    }

    public static void searchByVendor(List<Transaction> transactions) {
        String comparator = UserInput.promptForString("Vendor ");
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> transaction.getVendor().equalsIgnoreCase(comparator)).toList();

        if(filtered.isEmpty()){
            System.out.println("\nNo files to display");
        } else {
        Menus.displayTransactions(filtered);
        }
    }

    public static void customSearch(List<Transaction> transactions){

        Menus.customSearchMenu();

        switch (UserInput.promptForChar("Your choice ")){
            case 'A':
                Menus.customSearchOptions(searchCriteria.getStartDate(), searchCriteria.getEndDate(), searchCriteria.getDescription(), searchCriteria.getVendor());
                customSearchMenuOptions();
                break;
            case 'D':
                Menus.customSearchOptions(searchCriteria.getStartDate(), searchCriteria.getEndDate(), searchCriteria.getDescription(), searchCriteria.getVendor());
                break;
            case 'P':
                Menus.customSearchOptions(searchCriteria.getStartDate(), searchCriteria.getEndDate(), searchCriteria.getDescription(), searchCriteria.getVendor(), searchCriteria.getMinAmount(), searchCriteria.getMaxAmount());
                break;

        }

    }

    public static void allSearch(List<Transaction> transactions){


    }

    public static void customSearchMenuOptions(){
       switch (UserInput.promptForChar("Your choice ")){
           case 'E':
               Menus.editValuesMenu();
               switch (UserInput.promptForInt("What would you like to change? ", 1,6)){
                   case 1:
                       searchCriteria.setStartDate(UserInput.promptForDate("Start date [YYYY-MM-DD] "));
                       break;
                   case 2:
                       searchCriteria.setEndDate(UserInput.promptForDate("End date [YYYY-MM-DD] "));
                       break;
                   case 3:
                       searchCriteria.setDescription(UserInput.promptForString("Description "));
                       break;
                   case 4:
                       searchCriteria.setDescription(UserInput.promptForString("Vendor name "));
                       break;
                   case 5:
                       searchCriteria.setMinAmount(UserInput.promptForDouble("Min Value ", 1));
                       break;
                   case 6:
                       searchCriteria.setMaxAmount(UserInput.promptForDouble("Max Value", 1));
                       break;
               }
               break;
           case 'S':
               Menus.mainMenu();
               break;
           case 'X':
               break;
       }
    }
}
