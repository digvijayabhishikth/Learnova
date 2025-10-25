package com.org.learnova.services;

import com.org.learnova.dto.EnrollmentCourseViewDTO;
import com.org.learnova.dto.EnrollmentRegisterDTO;
import com.org.learnova.dto.EnrollmentStudentViewDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Enrollment;
import com.org.learnova.entities.Student;
import com.org.learnova.exception.AlreadyExistsException;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.mappers.EnrollmentMapper;
import com.org.learnova.repositories.CourseRepository;
import com.org.learnova.repositories.EnrollmentRepository;
import com.org.learnova.repositories.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private  final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseRepository courseRepository;
    private final StudentRespository studentRespository;

    @Autowired
    public  EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper, CourseRepository courseRepository, StudentRespository studentRespository){
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.courseRepository = courseRepository;
        this.studentRespository = studentRespository;

    }

    // student enrolling for the course
    public Enrollment addEnrollment(EnrollmentRegisterDTO dto ){
        if (enrollmentRepository.existsByStudentIdAndCourseId(dto.getStudentId(), dto.getCourseId())) {
            throw new AlreadyExistsException("Student already enrolled in this course");
        }
        Student student = this.studentRespository.findById(dto.getStudentId()).orElseThrow( () -> new NotFoundException("Student not found"));

        Course course = this.courseRepository.findById(dto.getCourseId()).orElseThrow(() -> new NotFoundException("Course not found"));

        Enrollment enrollment = this.enrollmentMapper.toEnrollment(dto, student, course);
        return this.enrollmentRepository.save(enrollment);
    }

    //all enrollments of a student
    public List<EnrollmentStudentViewDTO> getAllEnrollmentsForStudent(Long stdId){
        List<Enrollment> list = this.enrollmentRepository.findByStudent_Id(stdId);
        if(list.isEmpty()){
            throw new NotFoundException("No Enrollments are made by the student");
        }
        List<EnrollmentStudentViewDTO> enrollments = new ArrayList<>();
        for(Enrollment enrollment : list){
            Course course = enrollment.getCourse();
            Student student = enrollment.getStudent();
            enrollments.add(this.enrollmentMapper.toEnrollmentResponseDTO(enrollment,course,student));
        }
        return enrollments;
    }

    //all enrollments of s course
    public List<EnrollmentCourseViewDTO> getAllEnrollmentsOfCourse(long cId){
        List<Enrollment> list = this.enrollmentRepository.findByCourse_Id(cId);
        if(list.isEmpty())
                throw new NotFoundException("No Enrollments made for this course");
        List<EnrollmentCourseViewDTO> enrollments = new ArrayList<>();
        for(Enrollment enrollment: list){
            Student student = enrollment.getStudent();
            enrollments.add(this.enrollmentMapper.toEnrollmentCourseViewDTO(enrollment,student));
        }
        return enrollments;
    }
}
