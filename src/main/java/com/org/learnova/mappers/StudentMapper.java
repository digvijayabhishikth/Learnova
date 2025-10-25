package com.org.learnova.mappers;

import com.org.learnova.dto.StudentRegisterDTO;
import com.org.learnova.dto.StudentRequestDTO;
import com.org.learnova.dto.StudentResponseDTO;
import com.org.learnova.entities.Student;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.repositories.StudentRespository;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final StudentRespository studentRespository;


    public StudentMapper(StudentRespository studentRespository){
        this.studentRespository = studentRespository;
    }

    public Student toStudent(StudentRegisterDTO studentRegisterDTO){
        Student student = new Student();

        student.setFirstName((studentRegisterDTO.getFirstName()));
        student.setLastName(studentRegisterDTO.getLastName());
        student.setEmail(studentRegisterDTO.getEmail());
        student.setPassword((studentRegisterDTO.getPassword()));
        student.setOrganization(studentRegisterDTO.getOrganization());
        student.setExperience(studentRegisterDTO.getExperience());

        return student;
    }

    public Student toReqStudent(StudentRequestDTO studentRequestDTO){
        Student student = this.studentRespository.findById(studentRequestDTO.getId()).orElseThrow(() -> new NotFoundException("No Student Found with given details"));

        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setExperience(studentRequestDTO.getExperience());
        student.setOrganization(studentRequestDTO.getOrganization());

        return student;
    }

    public  StudentResponseDTO toResponseDTO(Student student){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setFirstName(student.getFirstName());
        studentResponseDTO.setLastName(student.getLastName());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setOrganization(student.getOrganization());
        studentResponseDTO.setExperience(student.getExperience());

        return studentResponseDTO;
    }
}
