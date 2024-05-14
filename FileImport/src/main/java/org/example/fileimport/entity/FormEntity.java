package org.example.fileimport.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Getter
@Setter
public class FormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NotNull(message = "Name cannot be blank")
    private String lname;
    @NotNull(message = "Name cannot be blank")
    private String fname;

    @Email(message = "Please enter the valid email")
    private String email;
    private String address;
    private String gender;
    private String agegroup;
    private String dob;
    private String country;
    private Date   submittime;
    private String submittedby;
}
