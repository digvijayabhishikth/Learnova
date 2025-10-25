package com.org.learnova.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegisterDTO {

    @NotBlank(message = "First Name should not be blank")
    private String firstName;

    @NotBlank(message = "Last name should not be blank")
    private String lastName;

    @Email
    @NotBlank(message = "Email can not be blank")
    private String email;

    @NotBlank(message = "organization can not be blank")
    private String organization;

    @Min(value = 0, message = "experience must be a positive value")
    private double experience;

    @NotBlank(message = "Password can not empty")
    @Size(min = 8, message = "Password should be at least 6 characters")
    private String password;
}
