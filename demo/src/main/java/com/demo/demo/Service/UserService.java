package com.demo.demo.Service;

import com.demo.demo.Dtos.PasswordUpdateDto;
import com.demo.demo.Dtos.RegisterStundentDto;
import com.demo.demo.Dtos.StudentDetailsDto;
import com.demo.demo.Models.Roles;
import com.demo.demo.Models.Student;
import com.demo.demo.Models.Users;
import com.demo.demo.Repositories.IRolesRepository;
import com.demo.demo.Repositories.IStudentsRepository;
import com.demo.demo.Repositories.IUsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IStudentsRepository studentsRepository;

    @Autowired
    private IRolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Student registerStudent(RegisterStundentDto registerDto,String rol) {
        // Crear y configurar la entidad Students a partir de los detalles
        Student student = new Student();
        StudentDetailsDto studentDetails = registerDto.getDetails();
        student.setIdDocument(studentDetails.getIdDocument());
        student.setTypeDocument(studentDetails.getTypeDocument());
        student.setCodeStudent(studentDetails.getCodeStudent());
        student.setFirstName(studentDetails.getFirstName());
        student.setMiddleName(studentDetails.getMiddleName());
        student.setLastName(studentDetails.getLastName());
        student.setSecondLastName(studentDetails.getSecondLastName());
        student.setDegree(studentDetails.getDegree());
        student.setLevelProgramming(studentDetails.getLevelProgramming());
        student.setEmail(studentDetails.getEmail());
        // Crear y configurar la entidad Users
        Users user = new Users();
        user.setUsername(student.getCodeStudent()); // Usar codeStudent como username
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        
        // Asignar rol por defecto (ajustar según tu lógica de negocio)
        Roles userRole = rolesRepository.findByName(rol)
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setName(rol);
                    return rolesRepository.save(newRole);
                });
        user.setRoles(Collections.singletonList(userRole));

        // Guardar el usuario
        usersRepository.save(user);

        // Vincular usuario con estudiante y guardar estudiante
        student.setUser(user);
        //user.setUserStudent(student);
        return studentsRepository.save(student);
    }

    ///Crear user
    public Users createUser(String username, String password, String role) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Roles userRole = rolesRepository.findByName(role)
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setName(role);
                    return rolesRepository.save(newRole);
                });

        user.setRoles(Collections.singletonList(userRole));
        return usersRepository.save(user);
    }

    public boolean updatePassword(String username, PasswordUpdateDto passwordUpdateDto) {
        Optional<Users> userOptional = usersRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
            usersRepository.save(user);
            return true;
        }
        return false;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        try {
            if (usersRepository.existsById(userId)) {
                usersRepository.deleteById(userId);
                return true;
            }
        } catch (DataIntegrityViolationException e) {
            
        } catch (Exception e) {
            
        }
        return false;
    }
}
