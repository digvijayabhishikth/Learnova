package com.org.learnova.dto;


import com.org.learnova.enums.Languauge;
import com.org.learnova.enums.Levels;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMentorViewDTO {

    private String courseName;

    private LocalDate createdAt;

    private String category;

    private long noOdEnrollments;

    private Languauge languauge;

    private Levels levels;
}
