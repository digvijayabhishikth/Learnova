package com.org.learnova.controllers;

import com.org.learnova.dto.EnrollmentRegisterDTO;
import com.org.learnova.entities.Enrollment;
import com.org.learnova.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/registerenrollment")
    public ResponseEntity<Enrollment> registerEnrollment(@RequestBody @Valid EnrollmentRegisterDTO dto){
        return new ResponseEntity<>(this.enrollmentService.addEnrollment(dto), HttpStatus.CREATED);
    }

}
