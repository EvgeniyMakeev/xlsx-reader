package dev.makeev.xlsxreader.service;

/**
 * Интерфейс сервиса для работы с Excel-файлами
 */
public interface ExcelService {

    /**
     *
     * @param filePath путь к файлу
     * @param n порядковый номер минимального числа
     * @return минимальное число из файла Excel по порядковому номеру
     */
    int getMinNumberFromFile(String filePath, int n);
}
