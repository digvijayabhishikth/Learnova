package com.org.learnova.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorRegisterDTO {

    @NotBlank(message = "First Name can not be blank")
    private String firstName;

    @NotBlank(message = "Last Name can not be blank")
    private String lastName;

    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotBlank(message = "Company can not be blank")
    private String company;

    @Min(value = 3, message = "Minimum Experience for the mentor should be 3 years")
    private double experience;

    @NotBlank(message = "password can not be empty")
    @Size(min = 8, message = "The password must be of 8 characters minimum")
    private String password;
}
