package com.org.learnova.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long skillId;

    @Column(name = "skillName", nullable = false)
    private String skillName;
}
