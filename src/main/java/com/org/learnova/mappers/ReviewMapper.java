package com.org.learnova.mappers;

import com.org.learnova.dto.ReviewRegisterDTO;
import com.org.learnova.dto.ReviewResponseDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Reviews;
import com.org.learnova.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {


    public Reviews toReview(ReviewRegisterDTO reviewRegisterDTO, Student student, Course course){

        Reviews reviews = new Reviews();

        reviews.setCourse(course);
        reviews.setStudent(student);
        reviews.setReview(reviewRegisterDTO.getReview());

        return reviews;
    }

    public ReviewResponseDTO toDto(Reviews reviews, Student student){
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        reviewResponseDTO.setStudentName(student.getFirstName()+" "+student.getLastName());
        reviewResponseDTO.setReview(reviews.getReview());
        reviewResponseDTO.setCreatedAt(reviews.getCreatedAt().toLocalDate());

        return reviewResponseDTO;
    }
}
