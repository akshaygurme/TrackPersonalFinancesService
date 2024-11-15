package com.example.Finances.personalFinances;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;


@Controller
public class personalFinanceController {
    @RequestMapping("/fetchData")
    @ResponseBody
    public String hello() {
        String filePath = "C:\\Users\\Akshay Gurme\\IdeaProjects\\personalFinances\\src\\main\\resources\\Nov2024InputCSV.csv"; // Replace with your file path
        //Read excel
        convertCSVtoPOJO(filePath);


        //Store it in POJO
        //Print data


        return "Hello World";
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
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("UNKNOWN\t");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

