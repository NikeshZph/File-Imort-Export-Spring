package org.example.fileimport.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String lname;
    private String fname;
    private String email;
    private String address;
    private String gender;
    private String agegroup;
    private String dob;
    private String country;
    private Date   submittime;
    private String submittedby;
}
