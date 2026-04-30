package com.pluralsight.service;

import com.pluralsight.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ledger {

    // Stores all transactions loaded from the CSV file
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    // Initializes the ledger and loads transactions from the CSV file.
    public Ledger() {

    }

    // Reads transactions.csv and adds them to the transactions Array list
    public void loadTransactions() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;
            // while loops through every line in transactions.csv and splits it into parts to assign to a transaction
            while ((input = bufferedReader.readLine()) != null) {
                String[] parts = input.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount)); // creates a new transaction
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Saves transaction to transactions.csv and adds it to the list
    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Creates new line, writes to new line in formate date|time|description|vendor|amount
            bufferedWriter.newLine();
            bufferedWriter.write(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns a list of deposits
    public List<Transaction> getDeposits() {

        List<Transaction> deposits = new ArrayList<>();

        // loops through transactions and checks if it is a deposit and if it is them adds it to the new list
        for (Transaction transaction : transactions) {
            if (transaction.isDeposit()) {
                deposits.add(transaction);
            }
        }
        return deposits; // returns deposits

    }

    // Returns a list of payments (same logic as above but swapped for payments)
    public List<Transaction> getPayments() {

        List<Transaction> payments = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                payments.add(transaction);
            }
        }
        return payments;

    }

    // adds all amounts and returns a sum (used for quick stats)
    public double getBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    // Gets income from the current month (used for quick stats)
    public double getMonthToDateIncome() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth))
                .filter(Transaction::isDeposit)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Gets expenses for the current month (used for quick stats)
    public double getMonthToDateExpenses() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        return Math.abs(transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth))
                .filter(Transaction::isPayment)
                .mapToDouble(Transaction::getAmount)
                .sum());
    }

    // Gets total amount of transactions (used for quick stats)
    public int getTransactionCount() {
        return transactions.size();
    }

    // Returns a list of transactions
    public ArrayList<Transaction> getLedger() {
        return transactions;
    }


}
