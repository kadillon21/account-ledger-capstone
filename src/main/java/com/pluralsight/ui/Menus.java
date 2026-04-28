package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.util.ColorUtilities;

import java.text.NumberFormat;
import java.util.List;

public class Menus {

    public static void mainMenu() {
        System.out.println(ColorUtilities.BRIGHT_CYAN);
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     ACCOUNTING LEDGER        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║                              ║");
        System.out.println("║  D)  Add Deposit             ║");
        System.out.println("║  P)  Make Payment (Debit)    ║");
        System.out.println("║  L)  Ledger                  ║");
        System.out.println("║  X)  Exit                    ║");
        System.out.println("║                              ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println(ColorUtilities.RESET);
    }

    public static void ledgerMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║          LEDGER              ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║                              ║");
        System.out.println("║  A)  All Entries             ║");
        System.out.println("║  D)  Deposits                ║");
        System.out.println("║  P)  Payments                ║");
        System.out.println("║  R)  Reports                 ║");
        System.out.println("║  H)  Home                    ║");
        System.out.println("║                              ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public static void reportsMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║          REPORTS             ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║                              ║");
        System.out.println("║  1)  Month To Date           ║");
        System.out.println("║  2)  Previous Month          ║");
        System.out.println("║  3)  Year To Date            ║");
        System.out.println("║  4)  Previous Year           ║");
        System.out.println("║  5)  Search by Vendor        ║");
        System.out.println("║  0)  Back                    ║");
        System.out.println("║                              ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public static void displayTransactions(List<Transaction> transactions){

        NumberFormat money = NumberFormat.getCurrencyInstance();

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                       ACCOUNT LEDGER                                             ║");
        System.out.println("╠════════════╦══════════╦════════════════════════════════╦═══════════════════════════╦═════════════╣");
        System.out.println("║    DATE    ║   TIME   ║          DESCRIPTION           ║           VENDOR          ║    AMOUNT   ║");
        System.out.println("╠════════════╬══════════╬════════════════════════════════╬═══════════════════════════╬═════════════╣");


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            String color = t.isDeposit() ? ColorUtilities.GREEN : ColorUtilities.RED;

            System.out.printf("║ %-11s║ %-9s║ %-30s ║ %-25s ║%s%12s%s ║%n",
                    t.getDate(),
                    t.getTime(),
                    truncate(t.getDescription(), 29),
                    truncate(t.getVendor(), 24),
                    color,
                    money.format(t.getAmount()),
                    ColorUtilities.RESET
            );
        }

        System.out.println("╠════════════╩══════════╩════════════════════════════════╩═══════════════════════════╩═════════════╣");
        System.out.printf("║  %d transactions displayed                                                                      ║%n", transactions.size());
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════╝");

    }

    public static String truncate(String s, int maxLen) {
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 3) + "... ";
    }

}
