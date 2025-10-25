package com.org.learnova.repositories;


import com.org.learnova.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    boolean existsByStudentIdAndCourseId(Long sId, Long cId);

    List<Reviews> findByCourse_Id(Long cId);
}
