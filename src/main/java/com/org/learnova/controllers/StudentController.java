package com.org.learnova.controllers;

import com.org.learnova.dto.StudentRegisterDTO;
import com.org.learnova.dto.StudentRequestDTO;
import com.org.learnova.dto.StudentResponseDTO;
import com.org.learnova.entities.Student;
import com.org.learnova.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody @Valid StudentRegisterDTO dto){
        return new ResponseEntity<>(this.studentService.registerStudent(dto), HttpStatus.CREATED);
    }

    @GetMapping("/id/{sId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable(name = "sId") Long sId){
        return ResponseEntity.ok(this.studentService.getStudentById(sId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDTO> getStudentByEmail(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(this.studentService.getStudentByEmail(email));
    }

    @PatchMapping("/update")
    public ResponseEntity<StudentResponseDTO> updateStudent(@RequestBody @Valid StudentRequestDTO dto){
        return ResponseEntity.ok(this.studentService.updateStudentProfile(dto));
    }

    @DeleteMapping("/removeStudent/{sId}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "sId") Long sId){
        this.studentService.deleteStudent(sId);
        return ResponseEntity.ok("Student deleted successfully.");
    }
}
