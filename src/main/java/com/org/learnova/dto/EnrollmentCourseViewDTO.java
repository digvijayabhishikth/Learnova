package com.org.learnova.dto;

import com.org.learnova.enums.EnrollmentStatus;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentCourseViewDTO {

    private Long StudentId;

    private String studentName;

    private String Email;

    private EnrollmentStatus status;
}