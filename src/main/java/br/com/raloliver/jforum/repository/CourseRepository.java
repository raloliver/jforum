package br.com.raloliver.jforum.repository;

import br.com.raloliver.jforum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findByName(String courseName);
}
