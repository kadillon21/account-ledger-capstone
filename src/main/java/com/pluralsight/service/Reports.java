package com.pluralsight.service;


import com.pluralsight.model.SearchCriteria;
import com.pluralsight.model.Transaction;
import com.pluralsight.util.UserInput;
import com.pluralsight.ui.Menus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



public class Reports {


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

        if (filtered.isEmpty()) {
            System.out.println("\nNo files to display");
        } else {
            Menus.displayTransactions(filtered);
        }
    }

    public static void customSearch(List<Transaction> transactions) {

        SearchCriteria criteria = new SearchCriteria();
        boolean editing = true;

        while (editing) {
            Menus.customSearchOptions(criteria);
            switch (UserInput.promptForChar("Your choice ")) {
                case 'E':
                    editCriteria(criteria);
                    break;
                case 'S':
                    editing = false;
                    break;
                case 'X':
                    return;
            }
        }

        List<Transaction> filtered = transactions.stream().filter(criteria::matches).toList();
        Menus.displayTransactions(filtered);


    }


    public static void editCriteria(SearchCriteria criteria) {
        Menus.editValuesMenu();
        switch (UserInput.promptForInt("What would you like to change? ", 1, 7)) {
            case 1:
                criteria.setStartDate(UserInput.promptForDate("Start date [YYYY-MM-DD] "));
                break;
            case 2:
                criteria.setEndDate(UserInput.promptForDate("End date [YYYY-MM-DD] "));
                break;
            case 3:
                criteria.setDescription(UserInput.promptForString("Description "));
                break;
            case 4:
                criteria.setDescription(UserInput.promptForString("Vendor name "));
                break;
            case 5:
                criteria.setMinAmount(UserInput.promptForDouble("Min Value ", 1));
                break;
            case 6:
                criteria.setMaxAmount(UserInput.promptForDouble("Max Value", 1));
                break;
            case 7:
                Menus.transTypeMenu();
                switch (UserInput.promptForChar("Your choice ")){
                    case 'A':
                        criteria.setTransType("(any)");
                    case 'D':
                        criteria.setTransType("Deposits");
                    case 'P':
                        criteria.setTransType("Payments");
                }
                break;

        }
    }
}
