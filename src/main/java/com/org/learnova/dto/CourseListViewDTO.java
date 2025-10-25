package com.org.learnova.dto;


import com.org.learnova.enums.Languauge;
import com.org.learnova.enums.Levels;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseListViewDTO {

    private String courseName;

    private String description;

    private String category;

    private double price;

    private String mentorName;

    private Languauge language;

    private Levels levels;
}