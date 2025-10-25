package com.org.learnova.dto;


import com.org.learnova.enums.Levels;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class StudentCourseListDTO {

    private String name;

    private LocalDateTime enrolledAt;

    private double progress;

    private Levels levels;

    public StudentCourseListDTO(String name, LocalDateTime enrolledAt, double progress, Levels levels) {
        this.name = name;
        this.enrolledAt = enrolledAt;
        this.progress = progress;
        this.levels = levels;
    }

}
