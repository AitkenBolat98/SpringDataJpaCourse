package com.course.SpringDataJPACourse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
                        )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "course_sequence")
    @Column(name = "id",
            updatable = false)
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;

    @Column(name = "department",
            nullable = false,
            columnDefinition = "TEXT")
    private String department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }
}
