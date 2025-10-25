package com.org.learnova.repositories;

import com.org.learnova.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long> {
    boolean existsByNameAndMentor_Id(String name, Long mId);

    List <Course> findByMentor_Id(Long mId);

}
