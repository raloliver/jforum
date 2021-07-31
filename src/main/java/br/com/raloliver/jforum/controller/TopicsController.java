package br.com.raloliver.jforum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raloliver.jforum.controller.dto.TopicDto;
import br.com.raloliver.jforum.model.Topic;
import br.com.raloliver.jforum.repository.TopicRepository;

@RestController
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping("/topics")
    public List<TopicDto> getAll(String course) {

        if (course == null) {
            List<Topic> topics = topicRepository.findAll();

            return TopicDto.mapper(topics);
        } else {
            List<Topic> topics = topicRepository.findByCourse_Name(course);

            return TopicDto.mapper(topics);
        }

    }
}
