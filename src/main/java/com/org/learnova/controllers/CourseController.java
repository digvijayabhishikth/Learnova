package com.org.learnova.controllers;

import com.org.learnova.dto.CourseListViewDTO;
import com.org.learnova.dto.CourseRegisterDTO;
import com.org.learnova.dto.CourseResponseDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping("/addcourse")
    public ResponseEntity<Course> addCourse(@RequestBody @Valid CourseRegisterDTO dto){
        return new ResponseEntity<>(this.courseService.registerCourse(dto),HttpStatus.CREATED);
    }


    @GetMapping("/getCourses")
    public ResponseEntity<List<CourseListViewDTO>> getAllCourses(){
        return ResponseEntity.ok(this.courseService.getAllCourses());
    }

    @GetMapping("/{cId}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable(name = "cId") Long cId){
        return ResponseEntity.ok(this.courseService.getCourse(cId));
    }

}
