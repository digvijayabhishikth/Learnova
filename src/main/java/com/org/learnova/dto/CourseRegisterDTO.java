package com.org.learnova.dto;


import com.org.learnova.enums.Levels;
import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegisterDTO {

    @NotBlank(message = "Course name can not be blank")
    @Size(max = 30, message = "Course name must not exceed 30 characters")
    private String name;

    @NotBlank(message = "Course description can not be blank")
    @Size(max = 250, message = "Course description can not be more than 250 characters")
    private String description;

    @NotBlank(message = "Category can not be Blank")
    private String category;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be at least 0")
    @DecimalMax(value = "2999.99", inclusive = true, message = "Price must not exceed 2999.99")
    private double price;

    @NotNull(message = "Mentor Id must be provided")
    private Long mentorId;

    @NotBlank(message = "Language can not be blank")
    private String language;

    @NotNull(message = "Level must be provided")
    private Levels levels;
}
