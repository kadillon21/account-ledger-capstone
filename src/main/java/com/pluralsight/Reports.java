package com.pluralsight;


import java.time.LocalDate;
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
        LocalDate previousYearStart = now.minusYears(1).minusMonths(1).withDayOfMonth(1);
        LocalDate previousYearEnd = previousYearStart.withMonth(12).withDayOfMonth(31);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousYearStart)
                        && !transaction.getDate().isAfter(previousYearEnd)).toList();
        Menus.displayTransactions(filtered);
    }

    public static void searchByVendor(List<Transaction> transactions) {
        String comparator = UserInput.promptForString("Vendor: ");
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> transaction.getVendor().equalsIgnoreCase(comparator)).toList();
        Menus.displayTransactions(filtered);
    }
}
