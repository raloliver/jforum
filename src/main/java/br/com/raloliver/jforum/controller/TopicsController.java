package br.com.raloliver.jforum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raloliver.jforum.controller.dto.TopicDto;
import br.com.raloliver.jforum.model.Course;
import br.com.raloliver.jforum.model.Topic;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDto> getAll() {
        Topic topic = new Topic("Dúvida", "Erro Typescript", new Course("Angular", "Front-end"));

        return TopicDto.mapper(Arrays.asList(topic, topic, topic));
    }
}
