package com.course.SpringDataJPACourse;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaCourseApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));
			student.addBook(new Book(student,"Clean code", LocalDateTime.now().minusDays(4)));
			student.addBook(new Book(student,"Book 2",LocalDateTime.now()));
			student.addBook(new Book(student,"Book 3",LocalDateTime.now().minusYears(1)));
			StudentIdCard studentIdCard = new StudentIdCard(student,"123456789");
			studentIdCardRepository.save(studentIdCard);
			studentRepository.findById(1L).ifPresent(student1 -> {
				System.out.println("fetch book lazy");
				List<Book> books = student1.getBooks();
				books.forEach(book -> {
					System.out.println(student1.getFirstName() + " took a book named "  +book.getBookName());
				});
			});
			student.enrollToCourse(new Course("ECON 515","Economics"));
			student.enrollToCourse(new Course("MATH 615","Mathematics "));
			CourseRepository.
		};


	}

//	private static void sorting(StudentRepository studentRepository) {
//		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
//		studentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName()));
//	}

	private void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i = 0; i <= 20; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));
			studentRepository.save(student);
		}
	}
}

