package br.com.raloliver.jforum.repository;

import br.com.raloliver.jforum.model.Topic;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  Page<Topic> findByCourse_Name(String course, Pageable pagination);
}
