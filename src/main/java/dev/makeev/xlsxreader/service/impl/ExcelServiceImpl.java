package dev.makeev.xlsxreader.service.impl;

import dev.makeev.xlsxreader.service.ExcelService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static dev.makeev.xlsxreader.utils.ExcelReader.readNumbersFromExcel;

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
