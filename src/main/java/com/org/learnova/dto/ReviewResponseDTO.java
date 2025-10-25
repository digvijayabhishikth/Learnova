package com.org.learnova.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    private String studentName;

    private LocalDate createdAt;

    private String review;
}
