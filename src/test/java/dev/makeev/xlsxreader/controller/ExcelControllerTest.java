package dev.makeev.xlsxreader.controller;

import dev.makeev.xlsxreader.service.ExcelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExcelController.class)
@DisplayName("Тест контроллера ExcelController")
class ExcelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExcelService excelService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ExcelService excelService() {
            return Mockito.mock(ExcelService.class);
        }
    }

    @Test
    @DisplayName("Should return min number (success case)")
    void getMinNumber_Success() throws Exception {
        when(excelService.getMinNumberFromFile("test.xlsx", 1)).thenReturn(5);

        mockMvc.perform(get("/api/v1/excel/min")
                        .param("filePath", "test.xlsx")
                        .param("n", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(excelService).getMinNumberFromFile("test.xlsx", 1);
    }

    @Test
    @DisplayName("Should return 400 Bad Request if N is invalid")
    void getMinNumber_InvalidN() throws Exception {
        doThrow(new IllegalArgumentException("N не может быть меньше 1 или больше количества чисел в файле"))
                .when(excelService).getMinNumberFromFile("test.xlsx", 100);

        mockMvc.perform(get("/api/v1/excel/min")
                        .param("filePath", "test.xlsx")
                        .param("n", "100"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("N не может быть меньше 1 или больше количества чисел в файле"));
    }

    @Test
    @DisplayName("Should return 500 Internal Server Error on file error")
    void getMinNumber_FileError() throws Exception {
        doThrow(new RuntimeException("Файл не найден"))
                .when(excelService).getMinNumberFromFile("nonexistent.xlsx", 1);

        mockMvc.perform(get("/api/v1/excel/min")
                        .param("filePath", "nonexistent.xlsx")
                        .param("n", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Ошибка обработки файла: Файл не найден"));
    }
}