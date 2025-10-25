package com.org.learnova.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.org.learnova.enums.Levels;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course",uniqueConstraints = @UniqueConstraint(columnNames = {"name", "mentor_id"}))
@Setter
@Getter
public class Course  extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    @JsonManagedReference
    private Mentor mentor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Reviews> reviews = new ArrayList<>();

    @Column(name = "language", nullable = false)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Levels levels;
}
