package dev.makeev.xlsxreader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Интеграционные тесты")
class ExcelIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Должен возвращать минимальное число из файла Excel по порядковому номеру")
    void integrationTest() throws Exception {
        mockMvc.perform(get("/api/v1/excel/min")
                        .param("filePath", "src/test/resources/test.xlsx")
                        .param("n", "3"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$").value(4)
                );
    }
}