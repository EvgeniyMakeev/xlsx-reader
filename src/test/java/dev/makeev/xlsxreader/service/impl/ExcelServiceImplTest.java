package dev.makeev.xlsxreader.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тест ExcelServiceImpl")
class ExcelServiceImplTest {

    ExcelServiceImpl excelService = new ExcelServiceImpl();

    @Test
    @DisplayName("Должен возвращать минимальное число из файла Excel по порядковому номеру")
    void getMinNumberFromFile() {
        assertThat(excelService.getMinNumberFromFile("src/test/resources/test.xlsx", 3)).isEqualTo(4);
    }

    @Test
    @DisplayName("Должен выбрасывать IllegalArgumentException при некорректном N")
    void getMinNumberFromFile_InvalidN() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                excelService.getMinNumberFromFile("src/test/resources/test.xlsx", 100)
        );
        assertThat(exception.getMessage()).isEqualTo("N не может быть меньше 1 или больше количества чисел в файле");
    }

    @Test
    @DisplayName("Должен выбрасывать RuntimeException при ошибке чтения файла")
    void getMinNumberFromFile_FileNotFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                excelService.getMinNumberFromFile("src/test/resources/nonexistent.xlsx", 1)
        );
        assertThat(exception.getMessage()).contains("nonexistent.xlsx");
    }
}