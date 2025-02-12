package org.example.fileimport.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class GenericResponse<T>{


    private String message;
    private boolean status;
    private T data;
}
