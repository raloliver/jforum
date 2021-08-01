package br.com.raloliver.jforum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
     * É necessário converter o objeto topic(form) para topic. O ideal é devolver o
     * código 201 (created).
     * 
     * A URI devolve no cabeçalho (location) da resposta a url para requisição dos
     * detalhes do dado que foi inserido no banco de dados.
     * 
     * @Valid: Indicar ao SB que as validações devem ser executadas a partir do Bean
     *         Validation.
     * @param form
     */
    @PostMapping
    public ResponseEntity<TopicDto> add(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.mapper(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

}
