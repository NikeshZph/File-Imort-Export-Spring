package org.example.fileimport.service.impl;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.fileimport.dto.FormDto;
import org.example.fileimport.entity.FormEntity;
import org.example.fileimport.repo.FormRepo;
import org.example.fileimport.service.FormService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepo formRepo;

    @Override
    public FormEntity save(FormDto formDto) {
        FormEntity formEntity = new FormEntity();
        formEntity.setFname(formDto.getFname());
        formEntity.setGender(formDto.getGender());
        formEntity.setEmail(formDto.getEmail());
        formEntity.setLname(formDto.getLname());
        formEntity.setAddress(formDto.getAddress());
        formEntity.setCountry(formDto.getCountry());
        formEntity.setAgegroup(formDto.getAgegroup());
        formEntity.setDob(formDto.getDob());
        formEntity.setSubmittime(formDto.getSubmittime());
        formEntity.setSubmittedby(formDto.getSubmittedby());
        return formRepo.save(formEntity);
    }

    @Override
    public List<FormEntity> getall() {
        List<FormEntity> formEntities = new ArrayList<>();
        formRepo.findAll()
                .forEach(formEntities :: add);
        return formEntities;
    }

    @Override
    public void getExcel(HttpServletResponse httpServletResponse) throws IOException {

        List<FormEntity> formEntities = formRepo.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Form Details");
        Row row= sheet.createRow(0);

        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("address");
        row.createCell(2).setCellValue("agegroup");
        row.createCell(3).setCellValue("country");
        row.createCell(4).setCellValue("dob");
        row.createCell(5).setCellValue("email");
        row.createCell(6).setCellValue("fname");
        row.createCell(7).setCellValue("gender");
        row.createCell(8).setCellValue("lname");
        row.createCell(9).setCellValue("submittedby");
        row.createCell(10).setCellValue("submittedtime");

        int dataRowIndex= 1;
        for(FormEntity formEntity:formEntities)
        {
            Row datarow = sheet.createRow(dataRowIndex);
            datarow.createCell(0).setCellValue(formEntity.getId());
            datarow.createCell(1).setCellValue(formEntity.getAddress());
            datarow.createCell(2).setCellValue(formEntity.getAgegroup());
            datarow.createCell(3).setCellValue(formEntity.getCountry());
            datarow.createCell(4).setCellValue(formEntity.getDob());
            datarow.createCell(5).setCellValue(formEntity.getEmail());
            datarow.createCell(6).setCellValue(formEntity.getFname());
            datarow.createCell(7).setCellValue(formEntity.getGender());
            datarow.createCell(8).setCellValue(formEntity.getLname());
            datarow.createCell(9).setCellValue(formEntity.getSubmittedby());

            datarow.createCell(10).setCellValue(formEntity.getSubmittime());
            dataRowIndex++;
        }
        ServletOutputStream ops= httpServletResponse.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
