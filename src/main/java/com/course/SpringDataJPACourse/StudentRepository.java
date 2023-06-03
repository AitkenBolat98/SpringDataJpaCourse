package com.course.SpringDataJPACourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "Select * from Student s WHERE s.email = ?1",nativeQuery = true)
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "SELECT * from student WHERE first_name = :firstName AND age >= :age",nativeQuery = true
    )
    List<Student> findStudentByFirstNameEqualsAndAgeIsGreaterThanEqual(
            @Param("firstName") String firstName,
            @Param("age") Integer age);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Student u where u.id = ?1")
    int delteStudentById(Long id);
}
