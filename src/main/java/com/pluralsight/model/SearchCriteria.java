package com.pluralsight.model;

import java.time.LocalDate;

public class SearchCriteria {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description = "";
    private String vendor = "";
    private Double minAmount;
    private Double maxAmount;

    public SearchCriteria(){

    }

    public SearchCriteria(LocalDate startDate, LocalDate endDate, String description, String vendor, Double minAmount, Double maxAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.vendor = vendor;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
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

    public boolean matches(Transaction t) {
        if (startDate != null && t.getDate().isBefore(startDate)) return false;
        if (endDate != null && t.getDate().isAfter(endDate)) return false;
        if (vendor != null && !vendor.isEmpty() &&
                !t.getVendor().equalsIgnoreCase(vendor)) return false;
        if (description != null && !description.isEmpty() &&
                !t.getDescription().toLowerCase().contains(description.toLowerCase())) return false;
        if (minAmount != null && t.getAmount() < minAmount) return false;
        if (maxAmount != null && t.getAmount() > maxAmount) return false;
        return true;
    }
}