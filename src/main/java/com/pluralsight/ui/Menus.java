package com.pluralsight.ui;

import com.pluralsight.model.Transaction;
import com.pluralsight.util.ColorUtilities;

import java.text.NumberFormat;
import java.util.List;

public class Menus {

    public static void mainMenu() {
        String B  = ColorUtilities.BORDER;
        String A  = ColorUtilities.ACCENT;
        String Bo = ColorUtilities.BOLD;
        String U  = ColorUtilities.UNDERLINE;
        String R  = ColorUtilities.RESET;

        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║  " + Bo + U + R + "    ACCOUNTING LEDGER       " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "D" + R + ")  Add Deposit             " + B + "║");
        System.out.println(B + "║  " + A + "P" + R + ")  Make Payment (Debit)    " + B + "║");
        System.out.println(B + "║  " + A + "L" + R + ")  Ledger                  " + B + "║");
        System.out.println(B + "║  " + A + "X" + R + ")  Exit                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    public static void ledgerMenu() {
        String B  = ColorUtilities.BORDER;
        String A  = ColorUtilities.ACCENT;
        String Bo = ColorUtilities.BOLD;
        String U  = ColorUtilities.UNDERLINE;
        String R  = ColorUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "       LEDGER            " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "A" + R + ")  All Entries             " + B + "║");
        System.out.println(B + "║  " + A + "D" + R + ")  Deposits                " + B + "║");
        System.out.println(B + "║  " + A + "P" + R + ")  Payments                " + B + "║");
        System.out.println(B + "║  " + A + "R" + R + ")  Reports                 " + B + "║");
        System.out.println(B + "║  " + A + "H" + R + ")  Home                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    public static void reportsMenu() {
        String B  = ColorUtilities.BORDER;
        String A  = ColorUtilities.ACCENT;
        String Bo = ColorUtilities.BOLD;
        String U  = ColorUtilities.UNDERLINE;
        String R  = ColorUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "      REPORTS            " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "1" + R + ")  Month To Date           " + B + "║");
        System.out.println(B + "║  " + A + "2" + R + ")  Previous Month          " + B + "║");
        System.out.println(B + "║  " + A + "3" + R + ")  Year To Date            " + B + "║");
        System.out.println(B + "║  " + A + "4" + R + ")  Previous Year           " + B + "║");
        System.out.println(B + "║  " + A + "5" + R + ")  Search by Vendor        " + B + "║");
        System.out.println(B + "║  " + A + "0" + R + ")  Back                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    public static void displayTransactions(List<Transaction> transactions) {
        String B = ColorUtilities.BORDER;
        String A = ColorUtilities.ACCENT;
        String M = ColorUtilities.MUTED;
        String R = ColorUtilities.RESET;
        NumberFormat money = NumberFormat.getCurrencyInstance();

        System.out.println(B + "\n╔══════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(B + "║                                       ACCOUNT LEDGER                                             ║");
        System.out.println(B + "╠════════════╦══════════╦════════════════════════════════╦═══════════════════════════╦═════════════╣");
        System.out.println(B + "║ " + A + "   DATE    " + B + "║ " + A + "  TIME   " + B + "║ " + A + "         DESCRIPTION           " + B + "║ " + A + "          VENDOR          " + B + "║ " + A + "   AMOUNT   " + B + "║");
        System.out.println(B + "╠════════════╬══════════╬════════════════════════════════╬═══════════════════════════╬═════════════╣");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            String amtColor = t.isDeposit() ? ColorUtilities.SUCCESS : ColorUtilities.DANGER;

            System.out.printf(B + "║" + R + " %-11s" + B + "║" + M + " %-9s" + B + "║" + R + " %-30s " + B + "║" + R + " %-25s " + B + "║" + amtColor + "%12s" + B + " ║%n",
                    t.getDate(),
                    t.getTime(),
                    truncate(t.getDescription(), 29),
                    truncate(t.getVendor(), 24),
                    money.format(t.getAmount())
            );
        }


        int count = transactions.size(); 
        int digits = String.valueOf(count).length();
        String spacer = switch (digits) {
            case 1 -> "%72s║%n";
            case 2 -> "%71s║%n";
            case 3 -> "%70s║%n";
            default -> "%69s║%n";
        };

        System.out.println(B + "╠════════════╩══════════╩════════════════════════════════╩═══════════════════════════╩═════════════╣");
        System.out.printf( B + "║  " + A + "%d transactions displayed" + B + spacer, transactions.size(), "");
        System.out.println(B + "╚══════════════════════════════════════════════════════════════════════════════════════════════════╝" + R);
    }

    public static String truncate(String s, int maxLen) {
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 3) + "... ";
    }

}
