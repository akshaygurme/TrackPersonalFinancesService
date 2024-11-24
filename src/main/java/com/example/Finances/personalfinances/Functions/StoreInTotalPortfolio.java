package com.example.Finances.personalfinances.Functions;

import com.example.Finances.personalfinances.Models.*;

import java.util.List;

public class StoreInTotalPortfolio {

    public static FinancialSummary storeTransactionInPortfolio(List<HygieneTransaction> hygieneTransactions){
//        TODO : fetch financialSummary data from database
        FinancialSummary financialSummary = defaultFinancialSummary();

//        TODO : write logic to fetch data from hygieneTransactions and calculate final financial Summary
        for(HygieneTransaction transaction : hygieneTransactions){
            financialSummary.setTotalCash       (transaction.getBankBalance().getTotalCash());
            financialSummary.setTotalSalary     (transaction.getSalary()            + financialSummary.getTotalSalary());
            financialSummary.setTotalReceived   (transaction.getReceivedAmount()    + financialSummary.getTotalReceived());
            financialSummary.setTotalReturns    (transaction.getReturns()           + financialSummary.getTotalReturns());
            financialSummary.setTotalExpenses   (getTotalExpense(transaction, financialSummary));
            financialSummary.setTotalInvestment (getTotalInvestments(transaction, financialSummary));
            financialSummary.setTotalReceived   (transaction.getReceivedAmount());
        }
//        TODO : Store financial Summary in database

        return financialSummary;
    }

    private static TotalInvestment getTotalInvestments(HygieneTransaction transaction, FinancialSummary financialSummary) {

        TotalInvestment totalInvestment = new TotalInvestment();
        totalInvestment.setBankFds              (transaction.getInvestments().getBankFds()              + financialSummary.getTotalInvestment().getBankFds());
        totalInvestment.setInvoiceDiscounting   (transaction.getInvestments().getInvoiceDiscounting()   + financialSummary.getTotalInvestment().getInvoiceDiscounting());
        totalInvestment.setUSStocks             (transaction.getInvestments().getUsStocks()             + financialSummary.getTotalInvestment().getUSStocks());
        totalInvestment.setCorporateBonds       (transaction.getInvestments().getCorporateBonds()       + financialSummary.getTotalInvestment().getCorporateBonds());
        totalInvestment.setCorporateBankFD      (transaction.getInvestments().getCorporateBankFD()      + financialSummary.getTotalInvestment().getCorporateBankFD());
        totalInvestment.setDebtMF               (transaction.getInvestments().getDebtMF()               + financialSummary.getTotalInvestment().getDebtMF());
        totalInvestment.setNps                  (transaction.getInvestments().getNps()                  + financialSummary.getTotalInvestment().getNps());
        totalInvestment.setElss                 (transaction.getInvestments().getElss()                 + financialSummary.getTotalInvestment().getElss());
        totalInvestment.setStocks               (transaction.getInvestments().getStocks()               + financialSummary.getTotalInvestment().getStocks());
        totalInvestment.setMutualFunds          (transaction.getInvestments().getMutualFunds()          + financialSummary.getTotalInvestment().getMutualFunds());
        totalInvestment.setGoldMutualFund       (transaction.getInvestments().getGoldMutualFund()       + financialSummary.getTotalInvestment().getGoldMutualFund());
        totalInvestment.setPhysicalGold         (transaction.getInvestments().getPhysicalGold()         + financialSummary.getTotalInvestment().getPhysicalGold());
        totalInvestment.setSgb                  (transaction.getInvestments().getSgb()                  + financialSummary.getTotalInvestment().getSgb());
        totalInvestment.setCrypto               (transaction.getInvestments().getCrypto()               + financialSummary.getTotalInvestment().getCrypto());
        totalInvestment.setRealEstate           (transaction.getInvestments().getRealEstate()           + financialSummary.getTotalInvestment().getRealEstate());

        return totalInvestment;

    }

    private static TotalExpenses getTotalExpense(HygieneTransaction transaction, FinancialSummary financialSummary) {

        TotalExpenses totalExpenses = new TotalExpenses();
        
        totalExpenses.setStudy      (transaction.getExpenses().getStudy()           + financialSummary.getTotalExpenses().getStudy());
        totalExpenses.setDailyNeeds (transaction.getExpenses().getDailyNeeds()      + financialSummary.getTotalExpenses().getDailyNeeds());
        totalExpenses.setInsurance  (transaction.getExpenses().getInsurance()       + financialSummary.getTotalExpenses().getInsurance());
        totalExpenses.setWants      (transaction.getExpenses().getWants()           + financialSummary.getTotalExpenses().getWants());
        totalExpenses.setLend       (transaction.getExpenses().getLend()            + financialSummary.getTotalExpenses().getLend());
        totalExpenses.setConstruction(transaction.getExpenses().getConstruction()   + financialSummary.getTotalExpenses().getConstruction());
        totalExpenses.setDonation   (transaction.getExpenses().getDonation()        + financialSummary.getTotalExpenses().getDonation());
        totalExpenses.setOther      (transaction.getExpenses().getOther()           + financialSummary.getTotalExpenses().getOther());

        return totalExpenses;
    }

    private static FinancialSummary defaultFinancialSummary() {
        FinancialSummary financialSummary = new FinancialSummary();
//      TODO : remove once data fetched from database.
        TotalExpenses expense = new TotalExpenses();
        expense.setConstruction(10000.0);
        expense.setLend(10000.0);
        expense.setOther(10000.0);
        expense.setDonation(10000.0);
        expense.setInsurance(10000.0);
        expense.setStudy(10000.0);
        expense.setDailyNeeds(10000.0);
        expense.setWants(10000.0);

        TotalInvestment investment = new TotalInvestment();
        investment.setBankFds(10000.0);
        investment.setInvoiceDiscounting(10000.0);
        investment.setUSStocks(10000.0);
        investment.setCorporateBonds(10000.0);
        investment.setCorporateBankFD(10000.0);
        investment.setDebtMF(1000.0);
        investment.setNps(10000.0);
        investment.setElss(10000.0);
        investment.setStocks(10000.0);
        investment.setMutualFunds(10000.0);
        investment.setGoldMutualFund(10000.0);
        investment.setPhysicalGold(10000.0);
        investment.setSgb(10000.0);
        investment.setCrypto(10000.0);
        investment.setRealEstate(10000.0);


        financialSummary.setTotalCash(100000.0);
        financialSummary.setTotalReceived(10000.0);
        financialSummary.setTotalReturns(1000.0);
        financialSummary.setTotalSalary(50000.0);
        financialSummary.setTotalInvestment(investment);
        financialSummary.setTotalExpenses(expense);

        return financialSummary;
    }
}
