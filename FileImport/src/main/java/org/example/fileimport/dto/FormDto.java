package org.example.fileimport.dto;

import lombok.Data;
import java.util.Date;
@Data
public class FormDto {

    private String lname;

    private String fname;

    private String email;

    private String address;

    private String gender;

    private String agegroup;

    private String dob;

    private String country;

    private Date submittime;

    private String  submittedby;
}
