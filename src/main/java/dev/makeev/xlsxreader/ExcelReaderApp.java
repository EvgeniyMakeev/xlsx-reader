package dev.makeev.xlsxreader;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Excel Reader API",
                version = "1.0",
                description = "API для получения данных из Excel-файлов по их пути и поиска N-го минимального числа"
        )
)
@SpringBootApplication
public class ExcelReaderApp {
    public static void main(String[] args) {
        SpringApplication.run(ExcelReaderApp.class, args);
    }
}
