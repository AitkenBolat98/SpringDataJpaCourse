package com.course.SpringDataJPACourse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity(name = "Book")
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @SequenceGenerator(name = "book_id_sequence",
                        sequenceName = "book_id_sequence",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "book_id_sequence")
    @Column(name = "id",
            nullable = false,
            unique = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "student_id",
                nullable = false,
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "student_book_fk"))
    private Student student;

    @Column(name = "book_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String bookName;

    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime localDateTime;

    public Book(Student student, String bookName, LocalDateTime localDateTime) {
        this.student = student;
        this.bookName = bookName;
        this.localDateTime = localDateTime;
    }
}
