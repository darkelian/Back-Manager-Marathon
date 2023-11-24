package com.demo.demo.Dtos;

import lombok.Data;

@Data
public class RegisterStundentDto {
    private String password;
    private StudentDetailsDto details;
}
