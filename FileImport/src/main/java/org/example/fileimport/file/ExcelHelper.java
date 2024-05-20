package org.example.fileimport.file;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.fileimport.entity.Employee;
import org.example.fileimport.exception.ExcelValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADERS = {"id", "name", "department", "address", "salary"};
    static String SHEET = "employees";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Employee> excelPractice(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            List<Employee> employees = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = currentRow.iterator();
                Employee employee = new Employee();
                int cellId = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellId) {
                        case 0:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                employee.setId((int) cell.getNumericCellValue());
                            } else {
                                throw new ExcelValidationException("Value doesn't exist for ID at row " + (rowNumber + 1));
                            }
                            break;
                        case 1:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setName(cell.getStringCellValue());
                            } else {
                                throw new ExcelValidationException("Value doesn't exist for Name at row " + (rowNumber + 1));
                            }
                            break;
                        case 2:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setDepartment(cell.getStringCellValue());
                            } else {
                                throw new ExcelValidationException("Value doesn't exist for Department at row " + (rowNumber + 1));
                            }
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.STRING) {
                                employee.setAddress(cell.getStringCellValue());
                            } else {
                                throw new ExcelValidationException("Value doesn't exist for Address at row " + (rowNumber + 1));
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                employee.setSalary(cell.getNumericCellValue());
                            } else {
                                throw new ExcelValidationException("Value doesn't exist for Salary at row " + (rowNumber + 1));
                            }
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                employees.add(employee);
                rowNumber++;
            }
            workbook.close();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }


    public static ByteArrayInputStream toExcel(List<Employee> employees) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }
            int rowId = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowId++);
                row.createCell(0).setCellValue(employee.getId());
                row.createCell(1).setCellValue(employee.getName());
                row.createCell(2).setCellValue(employee.getDepartment());
                row.createCell(3).setCellValue(employee.getAddress());
                row.createCell(4).setCellValue(employee.getSalary());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream createExcelTemplate() {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            Sheet sheet = workbook.createSheet("Sample");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Name", "Department", "Address", "Salary"};
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
