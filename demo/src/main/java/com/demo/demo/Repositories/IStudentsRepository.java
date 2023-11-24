package com.demo.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.demo.Models.Student;

@Repository
public interface IStudentsRepository extends JpaRepository<Student, Long>{
    
}
