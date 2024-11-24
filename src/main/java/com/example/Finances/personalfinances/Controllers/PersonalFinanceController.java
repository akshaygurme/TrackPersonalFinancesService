package com.example.Finances.personalfinances.Controllers;

import com.example.Finances.personalfinances.Functions.ConvertCSV;
import com.example.Finances.personalfinances.Functions.HygieneTransactions;
import com.example.Finances.personalfinances.Models.AllTransactionEntries;
import com.example.Finances.personalfinances.Models.FinancialSummary;
import com.example.Finances.personalfinances.Models.HygieneTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.Finances.personalfinances.Functions.StoreInTotalPortfolio.storeTransactionInPortfolio;


@Controller
@Log4j2
public class PersonalFinanceController {

    @GetMapping("/fetchData")
    public String fetchData(Model model, HttpSession session) throws JsonProcessingException {
        String filePath = "src\\main\\resources\\Nov2024InputCSV.csv"; // Replace with your file path
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());

//        Read excel
//        Store it in POJO
        List<AllTransactionEntries> allTransactions = ConvertCSV.convertCSVtoPOJO(filePath);
        log.info("----------------All Transactions {}",allTransactions);

//        Process this data into different objects so easy to handle
        List<HygieneTransaction> hygieneTransactions = HygieneTransactions.hygieneAllTransactions(allTransactions);

//        Print data
//        String json = mapper.writeValueAsString(hygieneTransactions);
//        log.info("----------------All Hygiened Transactions {}",json);

        model.addAttribute("transaction",hygieneTransactions);
        session.setAttribute("transaction",hygieneTransactions);
        return "hygieneTransactions";
    }

    @PostMapping("/process")
    public String process(HttpSession session, Model model){
//        TODO : process the hygiene transactions to be stored in total portfolio
        List<HygieneTransaction> data = (List<HygieneTransaction>) session.getAttribute("transaction");
        FinancialSummary financialSummary = storeTransactionInPortfolio(data);

        model.addAttribute("transactionData", data);
        model.addAttribute("financialSummary", financialSummary);
        return "hellojsp";
    }


    @PostMapping("/emergencyFund")
    public String emergencyFund(HttpSession session, Model model) {
        List<HygieneTransaction> data = (List<HygieneTransaction>) session.getAttribute("transaction");
        model.addAttribute("transactionData", data);

        //Display pie-chart of values of Emergency fund (BankFds, corporateBankFD, DebtFunds, invoiceDiscounting, corporateBonds )
//        var bankFds =
        return "hellojsp";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hellojsp";
    }

}

