package com.org.learnova.dto;

import com.org.learnova.entities.Course;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponseDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String company;

    private double experience;

    private List<Course> courses = new ArrayList<>();
}
