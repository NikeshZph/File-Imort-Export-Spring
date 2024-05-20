package org.example.fileimport.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeDto {

    private String name;
    private Double salary;
    private String department;
    private String address;
}
