package com.demo.demo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDetailsDto {
    @NotBlank(message = "debe registrar un ID de documento")
    private String idDocument;
    private String typeDocument;
    @NotBlank(message = "debe registrar un código de estudiante")
    private String codeStudent;
    @NotBlank(message =  "el nombre no puede estar vacío")
    private String firstName;
    private String middleName;
    @NotBlank(message =  "el apellido no puede estar vacío")
    private String lastName;
    private String SecondLastName;
    private String degree;
    private String levelProgramming;
    @NotBlank(message = "Debe registrar un correo")
    @Email(message = "Formato de correo electrónico inválido")
    private String email;
}
