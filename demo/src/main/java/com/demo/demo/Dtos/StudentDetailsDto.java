package com.demo.demo.Dtos;

import lombok.Data;

@Data
public class StudentDetailsDto {
    private String idDocument;
    private String typeDocument;
    private String codeStudent;
    private String firstName;
    private String middleName;
    private String lastName;
    private String SecondLastName;
    private String degree;
    private String levelProgramming;
    private String email;
}
