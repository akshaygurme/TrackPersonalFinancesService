package com.example.Finances.personalfinances.Controllers;

import com.example.Finances.personalfinances.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
@Log4j2
public class PersonalFinanceController {

    @GetMapping("/fetchData")
    public String fetchData(Model model) throws JsonProcessingException {
        String filePath = "src\\main\\resources\\Nov2024InputCSV.csv"; // Replace with your file path
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        //Read excel
        //Store it in POJO
        var allTransactions = convertCSVtoPOJO(filePath);
        log.info("----------------All Transactions {}",allTransactions);

        //Process this data into different objects so easy to handle
        var hygieneTransactions = hygieneAllTransactions(allTransactions);

        //Print data
//        String json = mapper.writeValueAsString(hygieneTransactions);
//        log.info("----------------All Hygiened Transactions {}",json);

        model.addAttribute("transaction",hygieneTransactions);
        return "hygieneTransactions";
    }

    @GetMapping("/hello")
    public String showChart() {
        return "hellojsp"; // Corresponds to chartView.jsp
    }

    //TODO move hygiene function to Functions package
    private List<HygieneTransaction> hygieneAllTransactions(List<AllTransactionEntries> allTransactions) {

        List<HygieneTransaction> hygieneTransactions = new ArrayList<>();
        double index = 0.0;
        for(var transaction : allTransactions)
        {
            index++;
            BankBalance bankBalance;
            Investment investment;
            Expense expense;

            bankBalance = buildBankBalance(transaction);
            investment = buildInvestment(transaction);
            expense = buildExpense(transaction);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");

            HygieneTransaction hygieneTransaction = new HygieneTransaction();
            //TODO: make recordNumber auto increment

            hygieneTransaction.setTransactionNumber(index);
            hygieneTransaction.setDate(LocalDate.parse(transaction.getDate(),formatter));
            hygieneTransaction.setBankName(transaction.getBankName());
            hygieneTransaction.setTransactionTitle(transaction.getTransactionTitle());
            hygieneTransaction.setBankBalance(bankBalance);
            hygieneTransaction.setSalary(Double.parseDouble(transaction.getSalary().isEmpty() ? "0" : transaction.getSalary()));
            hygieneTransaction.setReceivedAmount(Double.parseDouble(transaction.getReceivedAmount().isEmpty() ? "0" : transaction.getReceivedAmount()));
            hygieneTransaction.setInvestments(investment);
            hygieneTransaction.setReturns(Double.parseDouble(transaction.getReturns().isEmpty() ? "0" : transaction.getReturns()));
            hygieneTransaction.setExpenses(expense);

            hygieneTransactions.add(hygieneTransaction);
        }
        return hygieneTransactions;
    }

    private BankBalance buildBankBalance(AllTransactionEntries transaction) {
        BankBalance bankBalance = new BankBalance();
        bankBalance.setAxisBalance(Double.parseDouble(transaction.getAxisBalance().isEmpty() ? "0" : transaction.getAxisBalance()));
        bankBalance.setSbiBalance(Double.parseDouble(transaction.getSbiBalance().isEmpty() ? "0" : transaction.getSbiBalance()));
        bankBalance.setHdfcBalance(Double.parseDouble(transaction.getHdfcBalance().isEmpty() ? "0" : transaction.getHdfcBalance()));
        bankBalance.setTotalCash(Double.parseDouble(transaction.getTotalCash().isEmpty() ? "0" : transaction.getTotalCash()));
        return bankBalance;
    }

    private Expense buildExpense(AllTransactionEntries transaction) {
        Expense expense = new Expense();
        expense.setStudy(Double.parseDouble(transaction.getStudy().isEmpty() ? "0" : transaction.getStudy()));
        expense.setDailyNeeds(Double.parseDouble(transaction.getDailyNeeds().isEmpty() ? "0" : transaction.getDailyNeeds()));
        expense.setInsurance(Double.parseDouble(transaction.getInsurance().isEmpty() ? "0" : transaction.getInsurance()));
        expense.setWants(Double.parseDouble(transaction.getWants().isEmpty() ? "0" : transaction.getWants()));
        expense.setLend(Double.parseDouble(transaction.getLend().isEmpty() ? "0" : transaction.getLend()));
        expense.setConstruction(Double.parseDouble(transaction.getConstruction().isEmpty() ? "0" : transaction.getConstruction()));
        expense.setDonation(Double.parseDouble(transaction.getDonation().isEmpty() ? "0" : transaction.getDonation()));
        expense.setOther(Double.parseDouble(transaction.getOther().isEmpty() ? "0" : transaction.getOther()));
        return expense;
    }

    private Investment buildInvestment(AllTransactionEntries transaction) {

        Investment investment = new Investment();
        investment.setBankFds(Double.parseDouble(transaction.getBankFds().isEmpty() ? "0" : transaction.getBankFds()));
        investment.setInvoiceDiscounting(Double.parseDouble(transaction.getInvoiceDiscounting().isEmpty() ? "0" : transaction.getInvoiceDiscounting()));
        investment.setUsStocks(Double.parseDouble(transaction.getUsStocks().isEmpty() ? "0" : transaction.getUsStocks()));
        investment.setCorporateBonds(Double.parseDouble(transaction.getCorporateBonds().isEmpty() ? "0" : transaction.getCorporateBonds()));
        investment.setCorporateBankFD(Double.parseDouble(transaction.getCorporateBankFD().isEmpty() ? "0" : transaction.getCorporateBankFD()));
        investment.setNps(Double.parseDouble(transaction.getNps().isEmpty() ? "0" : transaction.getNps()));
        investment.setElss(Double.parseDouble(transaction.getElss().isEmpty() ? "0" : transaction.getElss()));
        investment.setStocks(Double.parseDouble(transaction.getStocks().isEmpty() ? "0" : transaction.getStocks()));
        investment.setMutualFunds(Double.parseDouble(transaction.getMutualFunds().isEmpty() ? "0" : transaction.getMutualFunds()));
        investment.setGoldMutualFund(Double.parseDouble(transaction.getGoldMutualFund().isEmpty() ? "0" : transaction.getGoldMutualFund()));
        investment.setPhysicalGold(Double.parseDouble(transaction.getPhysicalGold().isEmpty() ? "0" : transaction.getPhysicalGold()));
        investment.setSgb(Double.parseDouble(transaction.getSgb().isEmpty() ? "0" : transaction.getSgb()));
        investment.setCrypto(Double.parseDouble(transaction.getCrypto().isEmpty() ? "0" : transaction.getCrypto()));
        investment.setRealEstate(Double.parseDouble(transaction.getRealEstate().isEmpty() ? "0" : transaction.getRealEstate()));

        return investment;
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
            log.error("Error occured {}",e);
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
            log.error("Error occured {}",e);
        }
    }
}

