package com.org.learnova.mappers;

import com.org.learnova.dto.EnrollmentCourseViewDTO;
import com.org.learnova.dto.EnrollmentRegisterDTO;
import com.org.learnova.dto.EnrollmentStudentViewDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Enrollment;
import com.org.learnova.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEnrollment(EnrollmentRegisterDTO enrollmentRegisterDTO, Student student, Course course){
        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollment;
    }

    public EnrollmentStudentViewDTO toEnrollmentResponseDTO(Enrollment enrollment, Course course, Student student){
        EnrollmentStudentViewDTO enrollmentResponseDTO = new EnrollmentStudentViewDTO();

        enrollmentResponseDTO.setStudentId(student.getId());
        enrollmentResponseDTO.setCourseId(course.getId());
        enrollmentResponseDTO.setCourseName(course.getName());
        enrollmentResponseDTO.setProgress(enrollment.getProgress());
        enrollmentResponseDTO.setStatus(enrollment.getStatus());

        return enrollmentResponseDTO;
    }

    public EnrollmentCourseViewDTO toEnrollmentCourseViewDTO(Enrollment enrollment, Student student){
        EnrollmentCourseViewDTO enrollmentCourseViewDTO = new EnrollmentCourseViewDTO();

        enrollmentCourseViewDTO.setStudentName(student.getFirstName()+" "+student.getLastName());
        enrollmentCourseViewDTO.setEmail(student.getEmail());
        enrollmentCourseViewDTO.setStatus(enrollment.getStatus());
        enrollmentCourseViewDTO.setStudentId(student.getId());

        return enrollmentCourseViewDTO;
    }
}
