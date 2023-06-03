package com.course.SpringDataJPACourse;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_id_card_number_unique",
                        columnNames = "card_number")
        })
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;

    @Column(name = "card_number",
            nullable = false,
            length =  15)
    private String cardNumber;

    public StudentIdCard(Student student, String cardNumber) {
        this.student = student;
        this.cardNumber = cardNumber;
    }
}
