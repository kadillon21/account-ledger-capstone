package com.pluralsight;

import java.awt.*;

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

}
