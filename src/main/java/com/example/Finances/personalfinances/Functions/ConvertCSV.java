package com.example.Finances.personalfinances.Functions;

import com.example.Finances.personalfinances.Models.AllTransactionEntries;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;

@Log4j2
public class ConvertCSV {

    public static List<AllTransactionEntries> convertCSVtoPOJO(String filePath){
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
