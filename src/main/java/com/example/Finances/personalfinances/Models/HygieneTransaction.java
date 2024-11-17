package com.example.Finances.personalfinances.Models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HygieneTransaction {
    private double transactionNumber;
    private LocalDate date;
    private String bankName;
    private String transactionTitle;
    private BankBalance bankBalance;
    private double salary;
    private double receivedAmount;
    private Investment investments;
    private double returns;
    private Expense expenses;
}
