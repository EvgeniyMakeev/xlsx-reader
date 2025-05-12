package dev.makeev.xlsxreader.service.impl;

import dev.makeev.xlsxreader.service.ExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public int getMinNumberFromFile(String filePath, int n) {
        List<Integer> numbers = readNumbersFromExcel(filePath);
        if (n <= 0 || n > numbers.size()) {
            throw new IllegalArgumentException("N не может быть меньше 1 или больше количества чисел в файле");
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Файл не содержит чисел");
        }
        return findNthSmallest(numbers, n);
    }

    private List<Integer> readNumbersFromExcel(String filePath) {
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
        System.out.println(numbers.size());
        return numbers;
    }

    private int findNthSmallest(List<Integer> numbers, int n) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : numbers) {
            if (maxHeap.size() < n) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        return maxHeap.peek();
    }
}
