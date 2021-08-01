package br.com.raloliver.jforum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.raloliver.jforum.controller.dto.TopicDto;
import br.com.raloliver.jforum.controller.form.TopicForm;
import br.com.raloliver.jforum.model.Topic;
import br.com.raloliver.jforum.repository.CourseRepository;
import br.com.raloliver.jforum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    // @RequestMapping(value = "/topics", method = RequestMethod.GET)
    @GetMapping
    public List<TopicDto> getAll(String courseName) {

        if (courseName == null) {
            List<Topic> topics = topicRepository.findAll();

            return TopicDto.mapper(topics);
        } else {
            List<Topic> topics = topicRepository.findByCourse_Name(courseName);

            return TopicDto.mapper(topics);
        }

    }

    /**
     * É necessário converter o objeto topic(form) para topic
     * 
     * @param form
     */
    @PostMapping
    public void add(@RequestBody TopicForm form) {
        Topic topic = form.mapper(courseRepository);
        topicRepository.save(topic);
    }

}
