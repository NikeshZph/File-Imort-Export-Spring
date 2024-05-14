package org.example.fileimport.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.fileimport.dto.EmployeeDto;
import org.example.fileimport.entity.Employee;
import org.example.fileimport.file.ExcelHelper;
import org.example.fileimport.repo.EmpRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final EmpRepo empRepo;

    public void save(MultipartFile file) {
        try {
            List<Employee> employees = ExcelHelper.excelPractice(file.getInputStream());
            empRepo.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("fail to save the excel data" + e.getMessage());
        }
    }

    public List<Employee> getAllEmployee() {
        return empRepo.findAll();
    }


    public ByteArrayInputStream load() {
        List<Employee> employees = empRepo.findAll();

        ByteArrayInputStream in = ExcelHelper.toExcel(employees);
        return in;
    }

    public Employee add(EmployeeDto employeeDto)
    {

        Employee employee = new Employee();
        employee.setDepartment(employeeDto.getDepartment());
        employee.setAddress(employeeDto.getAddress());
        employee.setName(employeeDto.getName());
         return  empRepo.save(employee);
    }

    public List<Employee> findbysearch(String searchtext)
    {
       return empRepo.findEmployeeBySearch(searchtext);
    }


}

