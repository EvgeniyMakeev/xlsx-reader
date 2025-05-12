package dev.makeev.xlsxreader.controller;

import dev.makeev.xlsxreader.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с Excel-файлами
 */

@Tag(name = "ExcelController", description = "Контроллер для работы с Excel-файлами")
@RestController
@RequestMapping("/api/v1/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Operation(summary = "Получить минимальное число из файла Excel по пути и порядковому номеру")
    @GetMapping("/min")
    public ResponseEntity<Integer> getMinNumber(@RequestParam("filePath") String filePath, @RequestParam("n") int n) {
        return ResponseEntity.ok(excelService.getMinNumberFromFile(filePath, n));
    }
}
