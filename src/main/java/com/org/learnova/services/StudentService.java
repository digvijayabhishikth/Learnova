package com.org.learnova.services;

import com.org.learnova.dto.StudentCourseListDTO;
import com.org.learnova.dto.StudentRegisterDTO;
import com.org.learnova.dto.StudentRequestDTO;
import com.org.learnova.dto.StudentResponseDTO;
import com.org.learnova.entities.Student;
import com.org.learnova.exception.AlreadyExistsException;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.mappers.StudentMapper;
import com.org.learnova.repositories.EnrollmentRepository;
import com.org.learnova.repositories.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRespository studentRespository;
    private final StudentMapper studentMapper;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public StudentService(StudentRespository studentRespository, StudentMapper studentMapper,EnrollmentRepository enrollmentRepository){
        this.studentRespository = studentRespository;
        this.studentMapper = studentMapper;
        this.enrollmentRepository = enrollmentRepository;
    }

    //adding the student
    public Student registerStudent(StudentRegisterDTO studentRegisterDTO){
        Student student = this.studentMapper.toStudent(studentRegisterDTO);
        if (this.studentRespository.existsByEmail(studentRegisterDTO.getEmail())) {
            throw new AlreadyExistsException("Student with email : "+ studentRegisterDTO.getEmail()+" already exists");
        }
        return this.studentRespository.save(student);
    }

    //get student by their id
    public StudentResponseDTO getStudentById(Long stdId){

        Student student = this.studentRespository.findById(stdId).orElseThrow( () ->  new NotFoundException("Student Not found with Id : "+ stdId));

        return this.studentMapper.toResponseDTO(student);
    }

    //getStudent by their Email
    public StudentResponseDTO getStudentByEmail(String email){
        Student  student = this.studentRespository.findByEmail(email).orElseThrow(() ->  new NotFoundException("No Student found with given email "));
        return this.studentMapper.toResponseDTO(student);
    }

    //update Student Profile
    public StudentResponseDTO updateStudentProfile(StudentRequestDTO studentRequestDTO){

        Student student = this.studentMapper.toReqStudent(studentRequestDTO);

        Student updated = this.studentRespository.save(student);
        return this.studentMapper.toResponseDTO(updated);
    }

    //delete student/ student account
    public void deleteStudent(Long stdId){
        if (!this.studentRespository.existsById(stdId)) {
            throw new NotFoundException("Student with ID " + stdId + " not found.");
        }
        this.studentRespository.deleteById(stdId);
    }

    //list all courses enrolled by student
    public List<StudentCourseListDTO> allCoursesByStudent(Long stdId){

        List<StudentCourseListDTO> list = this.enrollmentRepository.findCourseDetailsByStudentId(stdId);
        if(list.isEmpty()){
            throw new NotFoundException("Student has not enrolled in any courses");
        }
        return list;
    }

}
