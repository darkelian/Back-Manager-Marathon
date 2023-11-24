package com.demo.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stundent")
    private Long idStudent;
    @Column(name = "id_document")
    private String idDocument;
    @Column(name = "type_document")
    private String typeDocument;
    @Column(name = "code_student")
    private String codeStudent;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_last_name")
    private String SecondLastName;
    @Column(name = "degree")
    private String degree;
    @Column(name = "level_programming")
    private String levelProgramming;
    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private Users user;
}
