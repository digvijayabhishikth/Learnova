package com.org.learnova.mappers;

import com.org.learnova.dto.*;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Mentor;
import com.org.learnova.enums.Languauge;
import com.org.learnova.enums.Levels;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public Course toCourse(CourseRegisterDTO dto, Mentor mentor){
        Course course = new Course();

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setCategory(dto.getCategory());
        course.setPrice(dto.getPrice());
        course.setLanguage(dto.getLanguage());
        course.setLevels(dto.getLevels());
        course.setMentor(mentor);

        return course;
    }

    public CourseMentorViewDTO courseMentorViewDTO(Course course, Long count){
        CourseMentorViewDTO dto = new CourseMentorViewDTO();

        dto.setCourseName(course.getName());
        dto.setCategory(course.getCategory());
        dto.setCreatedAt(course.getCreatedAt().toLocalDate());
        dto.setNoOdEnrollments(count);
        dto.setLanguauge(Languauge.valueOf(course.getLanguage()));
        dto.setLevels(course.getLevels());

        return dto;
    }

    public CourseResponseDTO courseResponseDTO(Course course, Mentor mentor){
        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setCourseName(course.getName());
        dto.setCategory(course.getCategory());
        dto.setDescription(course.getDescription());
        dto.setLevels(Levels.valueOf(String.valueOf(course.getLevels())));
        dto.setLanguage(Languauge.valueOf(course.getLanguage()));
        dto.setPrice(course.getPrice());
        dto.setMentorName(mentor.getFirstName()+" "+mentor.getLastName());

        List<ReviewResponseDTO> reviewDTOs = course.getReviews().stream()
                .map(review -> {
                    ReviewResponseDTO r = new ReviewResponseDTO();
                    r.setStudentName(review.getStudent().getFirstName() + " " + review.getStudent().getLastName());
                    r.setReview(review.getReview());
                    r.setCreatedAt(review.getCreatedAt().toLocalDate());
                    return r;
                }).toList();

        dto.setReviews(reviewDTOs);

        return dto;

    }

    public CourseListViewDTO courseListViewDTO(Course course, Mentor mentor){
        CourseListViewDTO dto = new CourseListViewDTO();

        dto.setCourseName(course.getName());
        dto.setCategory(course.getCategory());
        dto.setDescription(course.getDescription());
        dto.setLevels(Levels.valueOf(String.valueOf(course.getLevels())));
        dto.setLanguage(Languauge.valueOf(course.getLanguage()));
        dto.setPrice(course.getPrice());
        dto.setMentorName(mentor.getFirstName()+" "+mentor.getLastName());

        return dto;
    }
}
