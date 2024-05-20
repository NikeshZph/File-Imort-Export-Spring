package org.example.fileimport.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fileimport.dto.FormDto;
import org.example.fileimport.entity.FormEntity;
import org.example.fileimport.response.GenericResponse;
import org.example.fileimport.service.impl.FormServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/form")
public class FormController {

    private final FormServiceImpl formService;

    @PostMapping(value = "/save")
    public GenericResponse<FormEntity> save(@Valid @RequestBody FormDto formDto, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("userEmail");
        formDto.setSubmittedby(user);
        return GenericResponse.<FormEntity>builder()
                .data(formService.save(formDto))
                .status(true)
                .message("Form submitted successfully")
                .build();
    }
    @GetMapping(value = "/getall")
    public List<FormEntity> findall()
    {
      return   formService.getall();
    }

    @GetMapping(value = "/excel")
    public void generateExcelReport(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=form_details.xls");;
        formService.getExcel(httpServletResponse);
    }

 }
