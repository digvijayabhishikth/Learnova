package com.org.learnova.repositories;

import com.org.learnova.dto.StudentCourseListDTO;
import com.org.learnova.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment> findByStudent_Id(Long studentId);

    List<Enrollment> findByCourse_Id(Long studentId);

    @Query("Select Count(e) from Enrollment e WHERE e.course.id = :courseId")
    long countEnrollmentsByCourseId(@Param("id")Long courseId);

    @Query("""
    SELECT new com.org.learnova.dto.StudentCourseListDTO(
        c.name,
        e.enrolledAt,
        e.progress,
        c.levels
    )
    FROM Enrollment e
    JOIN e.course c
    WHERE e.student.id = :stdId
    """)
    List<StudentCourseListDTO> findCourseDetailsByStudentId(@Param("stdId") Long stdId);

}
