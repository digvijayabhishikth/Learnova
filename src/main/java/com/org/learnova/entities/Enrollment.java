package com.org.learnova.entities;

import com.org.learnova.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "enrollment", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDateTime enrolledAt;

    @Column(name = "progress")
    private double progress = 0.0;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @PrePersist
    protected void onEnroll() {
        this.enrolledAt = LocalDateTime.now();
    }

}
