package org.example.fileimport.controller;


import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.FileNameUtils;
import org.example.fileimport.dto.EmployeeDto;
import org.example.fileimport.entity.Employee;
import org.example.fileimport.exception.ExcelValidationException;
import org.example.fileimport.file.ExcelHelper;
import org.example.fileimport.response.GenericResponse;
import org.example.fileimport.response.ResponseMessage;
import org.example.fileimport.service.impl.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/excel")
@RequiredArgsConstructor

public class UploadController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (ExcelValidationException e) {
                message = "Excel validation error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } else {
            message = "Please upload an Excel file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/employee")
        public ResponseEntity<List<Employee>> getAllEmployee()
        {
            return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllEmployee());
        }
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "employee.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


    @GetMapping("/sample")
    public ResponseEntity<Resource> getSample()
    {
        String filename= "Sample.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.sample());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);    }

    @PostMapping("/add")
    public void addEmployees(@RequestBody EmployeeDto employeeDto){
        fileService.add(employeeDto);
    }


    @GetMapping(value = "/search")
    public List<Employee> getSearch(@RequestParam String searchtext)
    {
        return fileService.searchEmployees(searchtext);
    }


    @GetMapping(value = "/pagination/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Employee>> getPagination(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field)
    {

        Page<Employee> employees= fileService.findEmployeeByPagination(offset,pageSize,field);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}


