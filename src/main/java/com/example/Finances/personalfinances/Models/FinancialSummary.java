package com.example.Finances.personalfinances.Models;

import lombok.Data;

@Data
public class FinancialSummary {
    Double totalCash;
    Double totalSalary;
    Double totalReceived;
    TotalInvestment totalInvestment;
    Double totalReturns;
    TotalExpenses totalExpenses;
}
