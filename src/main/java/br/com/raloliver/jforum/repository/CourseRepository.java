package br.com.raloliver.jforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.raloliver.jforum.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String courseName);

}
