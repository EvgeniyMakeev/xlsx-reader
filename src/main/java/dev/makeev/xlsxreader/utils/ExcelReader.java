package dev.makeev.xlsxreader.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<Integer> readNumbersFromExcel(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell == null) continue;

                String number = cell.toString().trim();
                if (!number.isEmpty()) {
                    numbers.add((int) Double.parseDouble(number));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return numbers;
    }
}
