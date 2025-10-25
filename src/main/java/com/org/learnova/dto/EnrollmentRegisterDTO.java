package com.org.learnova.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRegisterDTO {

    private Long studentId;

    private Long courseId;
}
