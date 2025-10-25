package com.org.learnova.services;


import com.org.learnova.dto.CourseListViewDTO;
import com.org.learnova.dto.CourseRegisterDTO;
import com.org.learnova.dto.CourseResponseDTO;
import com.org.learnova.entities.Course;

import com.org.learnova.entities.Mentor;
import com.org.learnova.exception.AlreadyExistsException;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.mappers.CourseMapper;
import com.org.learnova.repositories.CourseRepository;
import com.org.learnova.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final MentorRepository mentorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,CourseMapper courseMapper,MentorRepository mentorRepository){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.mentorRepository = mentorRepository;
    }

    //add a new Course
    public Course registerCourse(CourseRegisterDTO dto){

        if(this.courseRepository.existsByNameAndMentor_Id(dto.getName(),dto.getMentorId()))
            throw new AlreadyExistsException("Course Already Exists");

        Mentor mentor = this.mentorRepository.findById(dto.getMentorId()).orElseThrow(()->new NotFoundException("Mentor not found exception"));

        return this.courseRepository.save(this.courseMapper.toCourse(dto,mentor));
    }

    //get a course by its id
    public CourseResponseDTO getCourse(Long cId){
        Course course = this.courseRepository.findById(cId).orElseThrow(() -> new NotFoundException("Course not found"));
        Mentor mentor = course.getMentor();

        return this.courseMapper.courseResponseDTO(course,mentor);
    }

    //get All courses list view
    public List<CourseListViewDTO> getAllCourses(){
        List<Course> list = this.courseRepository.findAll();
        if(list.isEmpty())
                throw new NotFoundException("NO Courses Available");

        List<CourseListViewDTO> courseListViewDTOS = new ArrayList<>();
        for(Course course : list){
            Mentor mentor = course.getMentor();
            courseListViewDTOS.add(this.courseMapper.courseListViewDTO(course,mentor));
        }

        return courseListViewDTOS;
    }


}
