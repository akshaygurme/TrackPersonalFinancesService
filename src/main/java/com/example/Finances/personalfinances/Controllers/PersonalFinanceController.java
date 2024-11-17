package com.example.Finances.personalfinances.Controllers;

import com.example.Finances.personalfinances.Models.*;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;


@Controller
@Log4j2
public class PersonalFinanceController {
    @RequestMapping("/fetchData")
    @ResponseBody
    public String hello() {
        String filePath = "src\\main\\resources\\Nov2024InputCSV.csv"; // Replace with your file path
        //Read excel
        //Store it in POJO
        var allTransactions = convertCSVtoPOJO(filePath);
        log.info("----------------All Transactions {}",allTransactions);
        //Process this data into different objects so easy to handle
        var hygieneTransactions = hygieneAllTransactions(allTransactions);
        log.info("----------------All Hygiened Transactions {}",hygieneTransactions);
        //Print data


        return "Hello World";
    }

    private List<HygieneTransaction> hygieneAllTransactions(List<AllTransactionEntries> allTransactions) {

        List<HygieneTransaction> hygieneTransactions = new ArrayList<>();
        for(var transaction : allTransactions)
        {
            BankBalance bankBalance = new BankBalance();
            Investment investment = new Investment();
            Expense expense = new Expense();

            bankBalance.setAxisBalance(Double.parseDouble(transaction.getAxisBalance()));
            bankBalance.setSbiBalance(Double.parseDouble(transaction.getSbiBalance()));
            bankBalance.setHdfcBalance(Double.parseDouble(transaction.getHdfcBalance()));
            bankBalance.setTotalCash(Double.parseDouble(transaction.getTotalCash()));


            investment.setEmergencyFund(Double.parseDouble(transaction.getEmergencyFund()));
            investment.setInvoiceDiscounting(Double.parseDouble(transaction.getInvoiceDiscounting()));
            investment.setUsStocks(Double.parseDouble(transaction.getUsStocks()));
            investment.setCorporateBonds(Double.parseDouble(transaction.getCorporateBonds()));
            investment.setCorporateBankFD(Double.parseDouble(transaction.getCorporateBankFD()));
            investment.setNps(Double.parseDouble(transaction.getNps()));
            investment.setElss(Double.parseDouble(transaction.getElss()));
            investment.setStocks(Double.parseDouble(transaction.getStocks()));
            investment.setMutualFunds(Double.parseDouble(transaction.getMutualFunds()));
            investment.setGoldMutualFund(Double.parseDouble(transaction.getGoldMutualFund()));
            investment.setPhysicalGold(Double.parseDouble(transaction.getPhysicalGold()));
            investment.setSgb(Double.parseDouble(transaction.getSgb()));
            investment.setCrypto(Double.parseDouble(transaction.getCrypto()));
            investment.setRealEstate(Double.parseDouble(transaction.getRealEstate()));


            expense.setStudy(Double.parseDouble(transaction.getStudy()));
            expense.setDailyNeeds(Double.parseDouble(transaction.getDailyNeeds()));
            expense.setInsurance(Double.parseDouble(transaction.getInsurance()));
            expense.setWants(Double.parseDouble(transaction.getWants()));
            expense.setLend(Double.parseDouble(transaction.getLend()));
            expense.setConstruction(Double.parseDouble(transaction.getConstruction()));
            expense.setDonation(Double.parseDouble(transaction.getDonation()));
            expense.setOther(Double.parseDouble(transaction.getOther()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");

            HygieneTransaction hygieneTransaction = new HygieneTransaction();
            //TODO: make recordNumber auto increment
            hygieneTransaction.setTransactionNumber(1);
            hygieneTransaction.setDate(LocalDate.parse(transaction.getDate(),formatter));
            hygieneTransaction.setBankName(transaction.getBankName());
            hygieneTransaction.setTransactionTitle(transaction.getTransactionTitle());
            hygieneTransaction.setBankBalance(bankBalance);
            hygieneTransaction.setSalary(Double.parseDouble(transaction.getSalary()));
            hygieneTransaction.setReceivedAmount(Double.parseDouble(transaction.getReceivedAmount()));
            hygieneTransaction.setInvestments(investment);
            hygieneTransaction.setReturns(Double.parseDouble(transaction.getReturns()));
            hygieneTransaction.setExpenses(expense);

            hygieneTransactions.add(hygieneTransaction);
        }
        return hygieneTransactions;
    }

    public List<AllTransactionEntries> convertCSVtoPOJO(String filePath){
        List<AllTransactionEntries> allTransactionEntries = null;

        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<AllTransactionEntries> csvToBean = new CsvToBeanBuilder<AllTransactionEntries>(reader)
                    .withType(AllTransactionEntries.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            allTransactionEntries = csvToBean.parse();

            allTransactionEntries.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTransactionEntries;

    }
    public void readExcel(String filePath){
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            log.debug(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                log.debug(cell.getDateCellValue() + "\t");
                            } else {
                                log.debug(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            log.debug(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            log.debug("UNKNOWN\t");
                    }
                }
                log.debug("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

