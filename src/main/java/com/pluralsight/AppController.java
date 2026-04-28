package com.pluralsight;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class AppController {

    private final Ledger ledger = new Ledger();
    private boolean programmRunning = true;
    public void run(){

        ledger.loadTransactions();
        handleHomeScreen();

    }

    private void handleHomeScreen() {
        boolean onHomeScreen = true;
        while (onHomeScreen) {
            Menus.mainMenu();
            switch (UserInput.promptForChar("Your choice: ")) {
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
            switch (UserInput.promptForChar("Your choice: ")) {
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
            switch (UserInput.promptForInt("Your choice: ", 0, 5)) {
                case 1:
                    Reports.monthToDateReport(ledger.getLedger());
                    break;
                case 2:
                    Reports.previousMonthReport(ledger.getLedger());
                    break;
                case 3:
                    Reports.yearToDate();
                    break;
                case 4:
                    Reports.previousYearReport();
                    break;
                case 5:
                    Reports.searchByVendor();
                    break;
                case 0:
                    onReportScreen = false;
            }
        }
    }

    private void addDeposit(Ledger ledger){

        System.out.println("── Add Deposit ──");
        String description = UserInput.promptForString("Description: ");
        String vendor = UserInput.promptForString("Vendor: ");
        double amount = UserInput.promptForDouble("Amount: ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println("Confirm:");
        System.out.println("  Date:        " + date);
        System.out.println("  Time:        " + time);
        System.out.println("  Description: " + description);
        System.out.println("  Vendor:      " + vendor);
        System.out.println("  Amount:      " + ColorUtilities.BRIGHT_RED + money.format(amount) + ColorUtilities.RESET);

        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transactions? [Y/n]")) {
                case 'Y':
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    switchRunning = false;
                    break;
                case 'N':
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;
                default:
                    System.out.println(ColorUtilities.ERROR + "You did not enter a valid menu option try again");
                    break;

            }
        }
    }

    private void makePayment(){

        System.out.println("── Make Payment ──");
        String description = UserInput.promptForString("Description: ");
        String vendor = UserInput.promptForString("Vendor: ");
        double amount = -UserInput.promptForDouble("Amount: ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println("Confirm:");
        System.out.println("  Date:        " + date);
        System.out.println("  Time:        " + time);
        System.out.println("  Description: " + description);
        System.out.println("  Vendor:      " + vendor);
        System.out.println("  Amount:      " + ColorUtilities.GREEN + money.format(amount) + ColorUtilities.RESET);

        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transactions? [Y/n]")) {
                case 'Y':
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    switchRunning = false;
                    break;
                case 'N':
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;
                default:
                    System.out.println(ColorUtilities.ERROR + "You did not enter a valid menu option try again");
                    break;

            }
        }

    }

    public static void showAccountOverview(){

    }

}
