package br.com.raloliver.jforum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.raloliver.jforum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByCourse_Name(String course);

}
