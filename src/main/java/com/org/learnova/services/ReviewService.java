package com.org.learnova.services;

import com.org.learnova.dto.ReviewRegisterDTO;
import com.org.learnova.dto.ReviewResponseDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Reviews;
import com.org.learnova.entities.Student;
import com.org.learnova.exception.AlreadyExistsException;
import com.org.learnova.exception.NotAuthorizedException;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.mappers.ReviewMapper;
import com.org.learnova.repositories.CourseRepository;
import com.org.learnova.repositories.ReviewsRepository;
import com.org.learnova.repositories.StudentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewsRepository reviewsRepository;
    private final StudentRespository studentRespository;
    private final CourseRepository courseRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewsRepository reviewsRepository, StudentRespository studentRespository,CourseRepository courseRepository,ReviewMapper reviewMapper){
        this.reviewsRepository = reviewsRepository;
        this.studentRespository = studentRespository;
        this.courseRepository = courseRepository;
        this.reviewMapper = reviewMapper;
    }

    //add a review
    public Reviews addReview(ReviewRegisterDTO dto){

        boolean isEnrolled = this.reviewsRepository.existsByStudentIdAndCourseId(dto.getSId(), dto.getCId());
        if(!isEnrolled)
            throw new NotAuthorizedException("You are not authorized to leave a review.");

        if (reviewsRepository.existsByStudentIdAndCourseId(dto.getSId(), dto.getCId())) {
            throw new AlreadyExistsException("You have already reviewed this course.");
        }


        Student student = this.studentRespository.findById(dto.getSId()).orElseThrow(()-> new NotFoundException("Student not Found"));
        Course course = this.courseRepository.findById((dto.getCId())).orElseThrow(()-> new NotFoundException("Course not found"));

        Reviews reviews = this.reviewMapper.toReview(dto,student,course);
        return this.reviewsRepository.save(reviews);
    }

    //list all reviews for a course
    public List<ReviewResponseDTO> reviewsOfCourse(Long cId){

        List<Reviews> list = this.reviewsRepository.findByCourse_Id(cId);

        if(list.isEmpty())
            throw new NotFoundException("No reviews for this course");

        List<ReviewResponseDTO> reviews = new ArrayList<>();

        for(Reviews review : list){
            Student student = review.getStudent();
            reviews.add(this.reviewMapper.toDto(review,student));
        }

        return reviews;
    }
}
