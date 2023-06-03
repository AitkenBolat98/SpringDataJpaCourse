package com.course.SpringDataJPACourse;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(
        name = "Student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private StudentIdCard studentIdCard;
    @OneToMany(mappedBy = "student",
                orphanRemoval = true,
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(name = "enrollment",
                joinColumns = @JoinColumn(
                        name = "student_id",
                        foreignKey = @ForeignKey(name = "enrollment_student_id_fk")
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "course_id",
                        foreignKey = @ForeignKey(name = "enrollment_course_id_fk")
                ))
    private List<Course> courses = new ArrayList<>();

    public void enrollToCourse(Course course){
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course){
        courses.remove(course);
        course.getStudents().remove(this);
    }
    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
    }
    public void removeBook(Book book){
        if(this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }
    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}
