package com.pluralsight.service;

import com.pluralsight.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ledger {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Ledger(){

    }

    public void loadTransactions(){
        try{
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufferedReader.readLine()) != null){
                String[] parts = input.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTransaction(Transaction t){
        transactions.add(t);

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.newLine();
            bufferedWriter.write(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getDeposits(){

        List<Transaction> result = new ArrayList<>();

        for(Transaction t : transactions){
            if(t.isDeposit()){
                result.add(t);
            }
        }
        return result;

    }

    public List<Transaction> getPayments(){

        List<Transaction> result = new ArrayList<>();

        for(Transaction t : transactions){
            if(t.isPayment()){
                result.add(t);
            }
        }
        return result;

    }

    public double getBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getMonthToDateIncome() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        return transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth))
                .filter(Transaction::isDeposit)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getMonthToDateExpenses() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        return Math.abs(transactions.stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth))
                .filter(Transaction::isPayment)
                .mapToDouble(Transaction::getAmount)
                .sum());
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public ArrayList<Transaction> getLedger(){
        return transactions;
    }




}
