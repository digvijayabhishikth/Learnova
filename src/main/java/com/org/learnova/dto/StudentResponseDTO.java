package com.org.learnova.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private String firstName;

    private String lastName;

    private String email;

    private double experience;

    private String organization;
}
