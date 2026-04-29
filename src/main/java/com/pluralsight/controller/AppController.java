package com.pluralsight.controller;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.Ledger;
import com.pluralsight.service.Reports;
import com.pluralsight.ui.Menus;
import com.pluralsight.util.ConsoleUtilities;
import com.pluralsight.util.UserInput;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class AppController {

    private final Ledger ledger = new Ledger();
    public void run(){

        ledger.loadTransactions();
        ConsoleUtilities.showProgressBar("Loading Account Ledger...", 3000);
        Menus.displayDashboard(ledger);
        handleHomeScreen();

    }

    private void handleHomeScreen() {
        boolean onHomeScreen = true;
        while (onHomeScreen) {
            Menus.mainMenu();
            switch (UserInput.promptForChar("Your choice ", "DPLX")) {
                case 'D':
                    addDeposit(ledger);
                    break;
                case 'P':
                    makePayment();
                    break;
                case 'L':
                    handleLedgerScreen();
                    break;
                case 'X':
                    onHomeScreen = false;
                    break;
            }
        }
    }
    private void handleLedgerScreen(){
        boolean onLedgerScreen = true;
        while (onLedgerScreen) {
            Menus.ledgerMenu();
            switch (UserInput.promptForChar("Your choice ", "ADPRH")) {
                case 'A':
                    Menus.displayTransactions(ledger.getLedger());
                    break;
                case 'D':
                    Menus.displayTransactions(ledger.getDeposits());
                    break;
                case 'P':
                    Menus.displayTransactions(ledger.getPayments());
                    break;
                case 'R':
                    handleReportsScreen();
                    break;
                case 'H':
                    onLedgerScreen = false;
            }
        }

    }

    private void handleReportsScreen(){
        boolean onReportScreen = true;
        while (onReportScreen) {
            Menus.reportsMenu();
            switch (UserInput.promptForInt("Your choice ", 0, 6)) {
                case 1:
                    Reports.monthToDateReport(ledger.getLedger());
                    break;
                case 2:
                    Reports.previousMonthReport(ledger.getLedger());
                    break;
                case 3:
                    Reports.yearToDate(ledger.getLedger());
                    break;
                case 4:
                    Reports.previousYearReport(ledger.getLedger());
                    break;
                case 5:
                    Reports.searchByVendor(ledger.getLedger());
                    break;
                case 6:
                    Reports.customSearch(ledger.getLedger());
                    break;
                case 0:
                    onReportScreen = false;
            }
        }
    }

    private void addDeposit(Ledger ledger){

        System.out.println("\n── Add Deposit ──");
        String description = UserInput.promptForString("Description ");
        String vendor = UserInput.promptForString("Vendor ");
        double amount = UserInput.promptForDouble("Amount ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println(ConsoleUtilities.BOLD + "\nConfirm:");
        System.out.println(ConsoleUtilities.ACCENT + "  Date        " + ConsoleUtilities.RESET + date);
        System.out.println(ConsoleUtilities.ACCENT + "  Time        " + ConsoleUtilities.RESET + time);
        System.out.println(ConsoleUtilities.ACCENT + "  Description " + ConsoleUtilities.RESET + description);
        System.out.println(ConsoleUtilities.ACCENT + "  Vendor      " + ConsoleUtilities.RESET + vendor);
        System.out.println(ConsoleUtilities.ACCENT + "  Amount      " + ConsoleUtilities.BRIGHT_GREEN + money.format(amount) + ConsoleUtilities.RESET);

        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transactions? [Y/n] ", "YN")) {
                case 'Y':
                    ConsoleUtilities.spin("Adding deposit...", 3000);
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    switchRunning = false;
                    break;
                case 'N':
                    ConsoleUtilities.spin("Canceling..." , 3000);
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;

            }
        }
    }

    private void makePayment(){

        System.out.println("\n── Make Payment ──");
        String description = UserInput.promptForString("Description: ");
        String vendor = UserInput.promptForString("Vendor: ");
        double amount = -UserInput.promptForDouble("Amount: ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println("Confirm:");
        System.out.println(ConsoleUtilities.ACCENT + "  Date:        " + ConsoleUtilities.RESET + date);
        System.out.println(ConsoleUtilities.ACCENT + "  Time:        " + ConsoleUtilities.RESET + time);
        System.out.println(ConsoleUtilities.ACCENT + "  Description: " + ConsoleUtilities.RESET + description);
        System.out.println(ConsoleUtilities.ACCENT + "  Vendor:      " + ConsoleUtilities.RESET + vendor);
        System.out.println(ConsoleUtilities.ACCENT + "  Amount:      " + ConsoleUtilities.BRIGHT_RED + money.format(amount) + ConsoleUtilities.RESET);

        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transactions? [Y/n] ", "YN")) {
                case 'Y':
                    ConsoleUtilities.spin("Adding Payment...", 3000);
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    switchRunning = false;
                    break;
                case 'N':
                    ConsoleUtilities.spin("Canceling...", 3000);
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;
            }
        }

    }

}
