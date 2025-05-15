package dev.makeev.xlsxreader.service.impl;

import dev.makeev.xlsxreader.service.ExcelService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

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
        return findNthSmallestQuickSelect(numbers, n);
    }

    private int findNthSmallestMaxHeap(List<Integer> numbers, int n) {
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

    private int findNthSmallestQuickSelect(List<Integer> nums, int n) {
        return quickSelectHelper(nums, 0, nums.size() - 1, n - 1);
    }

    private int quickSelectHelper(List<Integer> nums, int left, int right, int k) {
        if (left == right) return nums.get(left);

        int pivotIndex = left + new Random().nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (k == pivotIndex) {
            return nums.get(k);
        } else if (k < pivotIndex) {
            return quickSelectHelper(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelectHelper(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<Integer> nums, int left, int right, int pivotIndex) {
        int pivotValue = nums.get(pivotIndex);
        Collections.swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums.get(i) < pivotValue) {
                Collections.swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        Collections.swap(nums, storeIndex, right);
        return storeIndex;
    }
}
