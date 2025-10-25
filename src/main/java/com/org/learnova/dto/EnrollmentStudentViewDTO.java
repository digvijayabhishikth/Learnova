package com.org.learnova.dto;

import com.org.learnova.enums.EnrollmentStatus;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentStudentViewDTO {

    private Long studentId;

    private Long courseId;

    private String courseName;

    private double progress;

    private EnrollmentStatus status;
}
