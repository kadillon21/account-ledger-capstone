package com.pluralsight.model;

import java.time.LocalDate;

public class SearchCriteria {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String vendor;
    private Double minAmount = 10000.00;
    private Double maxAmount = 100000.00;
    private String transType;

    public SearchCriteria(){

    }

    public SearchCriteria(LocalDate startDate, LocalDate endDate, String description, String vendor, Double minAmount, Double maxAmount, String transType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.vendor = vendor;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.transType = transType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public boolean matches(Transaction transaction) {
        if (startDate != null && transaction.getDate().isBefore(startDate)) return false;
        if (endDate != null && transaction.getDate().isAfter(endDate)) return false;
        if (vendor != null && !vendor.isEmpty() &&
                !transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())) return false;
        if (description != null && !description.isEmpty() &&
                !transaction.getDescription().toLowerCase().contains(description.toLowerCase())) return false;

        if (minAmount != null && Math.abs(transaction.getAmount()) < minAmount) return false;
        if (maxAmount != null && Math.abs(transaction.getAmount()) > maxAmount) return false;
        if ("PAYMENTS".equalsIgnoreCase(transType) && transaction.getAmount() >= 0) return false;
        if ("DEPOSITS".equalsIgnoreCase(transType) && transaction.getAmount() <= 0) return false;
        return true;
    }
}