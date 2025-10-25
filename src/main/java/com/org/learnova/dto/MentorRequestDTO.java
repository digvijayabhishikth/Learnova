package com.org.learnova.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MentorRequestDTO {

    private Long mentorId;

    private String firstName;

    private String lastName;

    private String email;

    private String company;

    private double experience;
}