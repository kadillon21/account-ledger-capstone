package com.pluralsight;

public class AppController {


    public void run(){
        boolean appRunning = true;

        handleHomeScreen();




    }

    private void handleHomeScreen() {
        boolean onHomeScreen = true;
        while (onHomeScreen) {
            Menus.mainMenu();
            switch (UserInput.promptForChar("Your choice: ")) {
                case 'D':
                    addDeposit();
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
        Menus.ledgerMenu();
        switch (UserInput.promptForChar("Your choice: ")) {
            case 'A':

                break;
            case 'D':
                addDeposit();
                break;
            case 'P':
                makePayment();
                break;
            case 'R':
                handleReportsScreen();
                break;
            case 'H':

        }
    }

    private void handleReportsScreen(){
        boolean onReportScreen = true;
        while (onReportScreen) {
            Menus.reportsMenu();
            switch (UserInput.promptForInt("Your choice: ", 0, 5)) {
                case 1:
                    Reports.monthToDateReport();
                    break;
                case 2:
                    Reports.previousMonthReport();
                    break;
                case 3:
                    Reports.yearToDate();
                    break;
                case 4:
                    Reports.previousYearReport();
                    break;
                case 5:
                    Reports.searchByVendor();
                case 0:
                    onReportScreen = false;
            }
        }
    }

    private void addDeposit(){

    }

    private void makePayment(){

    }

    public static void showAccountOverview(){

    }
}
