package org.example.fileimport.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z])$")
    private String lname;

    @Pattern(regexp = "^([a-zA-Z])$")
    @NotEmpty
    private String fname;

    @Email(message = "Please enter the valid email")
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String agegroup;

    private String dob;
    private String country;
    private Date   submittime;
    private String submittedby;
}
