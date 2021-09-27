package br.com.raloliver.jforum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * @RequestMapping: (value = "/topics", method = RequestMethod.GET)
     * @RequestParam: caso não seja informado o parâmetro na requisição, receberemos
     *                uma exception, por isso o required = false.
     * @param courseName
     * @param page
     * @param quantity
     * @return
     */
    //
    @GetMapping
    public Page<TopicDto> getAll(@RequestParam(required = false) String courseName, @RequestParam int page,
            @RequestParam int quantity) {

        Pageable pagination = PageRequest.of(page, quantity);

        if (courseName == null) {
            Page<Topic> topics = topicRepository.findAll(pagination);

            return TopicDto.mapper(topics);
        } else {
            Page<Topic> topics = topicRepository.findByCourse_Name(courseName, pagination);

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
