package org.example.fileimport.service;


import jakarta.servlet.http.HttpServletResponse;
import org.example.fileimport.dto.FormDto;
import org.example.fileimport.entity.FormEntity;

import java.io.IOException;
import java.util.List;

public interface FormService {


    FormEntity save(FormDto formDto);
    List<FormEntity> getall();

    void getExcel(HttpServletResponse httpServletResponse) throws IOException;

}
