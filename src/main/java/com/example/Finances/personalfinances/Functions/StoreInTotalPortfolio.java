package com.example.Finances.personalfinances.Functions;

import com.example.Finances.personalfinances.Models.*;

import java.util.List;

public class StoreInTotalPortfolio {

    public static FinancialSummary storeTransactionInPortfolio(List<HygieneTransaction> hygieneTransactions){
//        TODO : fetch financialSummary data from database
        FinancialSummary financialSummary = defaultFinancialSummary();

//        TODO : write logic to fetch data from hygieneTransactions and calculate final financial Summary
        for(HygieneTransaction transaction : hygieneTransactions){
            var total = transaction.getBankBalance().getTotalCash();
//            total = total + financialSummary.getTotalCash();
            financialSummary.setTotalCash(total);

            total = transaction.getSalary();
            total = total + financialSummary.getTotalSalary();
            financialSummary.setTotalSalary(total);

            total = transaction.getReceivedAmount();
            total = total + financialSummary.getTotalReceived();
            financialSummary.setTotalReceived(total);

            total = transaction.getReturns();
            total = total + financialSummary.getTotalReturns();
            financialSummary.setTotalReturns(total);

            TotalExpenses expenses = getTotalExpense(transaction, financialSummary);
            financialSummary.setTotalExpenses(expenses);

            TotalInvestment investment = getTotalInvestments(transaction, financialSummary);
            financialSummary.setTotalInvestment(investment);


            financialSummary.setTotalReceived(transaction.getReceivedAmount());
        }
        
        
//        TODO : Store financial Summary in database

        return financialSummary;
    }

    private static TotalInvestment getTotalInvestments(HygieneTransaction transaction, FinancialSummary financialSummary) {

        TotalInvestment totalInvestment = new TotalInvestment();

        var total = transaction.getInvestments().getBankFds();
        total = total + financialSummary.getTotalInvestment().getBankFds();
        totalInvestment.setBankFds(total);

        total = transaction.getInvestments().getInvoiceDiscounting();
        total = total + financialSummary.getTotalInvestment().getInvoiceDiscounting();
        totalInvestment.setInvoiceDiscounting(total);

        total = transaction.getInvestments().getUsStocks();
        total = total + financialSummary.getTotalInvestment().getUSStocks();
        totalInvestment.setUSStocks(total);

        total = transaction.getInvestments().getCorporateBonds();
        total = total + financialSummary.getTotalInvestment().getCorporateBonds();
        totalInvestment.setCorporateBonds(total);

        total = transaction.getInvestments().getCorporateBankFD();
        total = total + financialSummary.getTotalInvestment().getCorporateBankFD();
        totalInvestment.setCorporateBankFD(total);

        total = transaction.getInvestments().getDebtMF();
        total = total + financialSummary.getTotalInvestment().getDebtMF();
        totalInvestment.setDebtMF(total);

        total = transaction.getInvestments().getNps();
        total = total + financialSummary.getTotalInvestment().getNps();
        totalInvestment.setNps(total);

        total = transaction.getInvestments().getElss();
        total = total + financialSummary.getTotalInvestment().getElss();
        totalInvestment.setElss(total);

        total = transaction.getInvestments().getStocks();
        total = total + financialSummary.getTotalInvestment().getStocks();
        totalInvestment.setStocks(total);

        total = transaction.getInvestments().getMutualFunds();
        total = total + financialSummary.getTotalInvestment().getMutualFunds();
        totalInvestment.setMutualFunds(total);

        total = transaction.getInvestments().getGoldMutualFund();
        total = total + financialSummary.getTotalInvestment().getGoldMutualFund();
        totalInvestment.setGoldMutualFund(total);

        total = transaction.getInvestments().getPhysicalGold();
        total = total + financialSummary.getTotalInvestment().getPhysicalGold();
        totalInvestment.setPhysicalGold(total);

        total = transaction.getInvestments().getSgb();
        total = total + financialSummary.getTotalInvestment().getSgb();
        totalInvestment.setSgb(total);

        total = transaction.getInvestments().getCrypto();
        total = total + financialSummary.getTotalInvestment().getCrypto();
        totalInvestment.setCrypto(total);

        total = transaction.getInvestments().getRealEstate();
        total = total + financialSummary.getTotalInvestment().getRealEstate();
        totalInvestment.setRealEstate(total);


        return totalInvestment;

    }

    private static TotalExpenses getTotalExpense(HygieneTransaction transaction, FinancialSummary financialSummary) {

        TotalExpenses totalExpenses = new TotalExpenses();

        var total = transaction.getExpenses().getStudy();
        total = total + financialSummary.getTotalExpenses().getStudy();
        totalExpenses.setStudy(total);

        total = transaction.getExpenses().getDailyNeeds();
        total = total + financialSummary.getTotalExpenses().getDailyNeeds();
        totalExpenses.setDailyNeeds(total);

        total = transaction.getExpenses().getInsurance();
        total = total + financialSummary.getTotalExpenses().getInsurance();
        totalExpenses.setInsurance(total);

        total = transaction.getExpenses().getWants();
        total = total + financialSummary.getTotalExpenses().getWants();
        totalExpenses.setWants(total);

        total = transaction.getExpenses().getLend();
        total = total + financialSummary.getTotalExpenses().getLend();
        totalExpenses.setLend(total);

        total = transaction.getExpenses().getConstruction();
        total = total + financialSummary.getTotalExpenses().getConstruction();
        totalExpenses.setConstruction(total);

        total = transaction.getExpenses().getDonation();
        total = total + financialSummary.getTotalExpenses().getDonation();
        totalExpenses.setDonation(total);

        total = transaction.getExpenses().getOther();
        total = total + financialSummary.getTotalExpenses().getOther();
        totalExpenses.setOther(total);

        return totalExpenses;
    }

    private static FinancialSummary defaultFinancialSummary() {
        FinancialSummary financialSummary = new FinancialSummary();
        //        for demo creating financialSummary.
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
