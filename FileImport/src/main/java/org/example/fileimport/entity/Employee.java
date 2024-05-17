package org.example.fileimport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "DepartMent cannot be blank")
    private String department;
    @NotNull(message = "Address cannot be blank")
    private String address;

    @NumberFormat
    private Double salary;

}
