package com.zidio.connect.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @PostMapping("/analyze")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> analyze(@RequestParam("file") MultipartFile file) throws Exception {
        try (InputStream in = file.getInputStream(); Workbook wb = file.getOriginalFilename().endsWith(".xlsx") ? new XSSFWorkbook(in) : WorkbookFactory.create(in)) {
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            if (!rowIterator.hasNext()) return ResponseEntity.badRequest().body(Map.of("error", "Empty sheet"));
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            int rowCount = 0;
            while (rowIterator.hasNext()) { rowIterator.next(); rowCount++; }
            return ResponseEntity.ok(Map.of(
                    "headers", headers,
                    "rows", rowCount
            ));
        }
    }
}

