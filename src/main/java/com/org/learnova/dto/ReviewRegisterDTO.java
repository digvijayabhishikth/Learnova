package com.org.learnova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRegisterDTO {

    @NotNull(message = "Student Id can not be blank")
    private Long sId;

    @NotNull(message = "Course id can not be blank")
    private Long cId;

    @NotBlank(message = "review can not be blank")
    private String review;
}
