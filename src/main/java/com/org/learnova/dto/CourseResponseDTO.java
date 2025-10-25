package com.org.learnova.dto;


import com.org.learnova.entities.Reviews;
import com.org.learnova.enums.Languauge;
import com.org.learnova.enums.Levels;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {

    private String courseName;

    private String description;

    private String category;

    private double price;

    private String mentorName;

    private List<ReviewResponseDTO> reviews = new ArrayList<>();

    private Languauge language;

    private Levels levels;
}
