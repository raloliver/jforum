package br.com.raloliver.jforum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.raloliver.jforum.controller.dto.DetailsTopicDto;
import br.com.raloliver.jforum.controller.dto.TopicDto;
import br.com.raloliver.jforum.controller.form.TopicForm;
import br.com.raloliver.jforum.controller.form.TopicFormUpdate;
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
    @Transactional
    public ResponseEntity<TopicDto> add(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.mapper(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    /**
     * Como o valor do @GetMapping é o mesmo do Long no método details, o SB entende
     * que um diz respeito ao outro, porém, se forem diferentes, é necessário passar
     * um valor no @PathVariable.
     * 
     * .isPresent() se existir um registro de fato presente, vou retornar um new
     * DetalhesDoTopicoDto(topico), passando como parâmetro topico.
     * 
     * É necessário chamar o método get(), que é para pegar o topico de fato que
     * está dentro do Optional.
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicDto> details(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        // Ao instanciar um novo TopicoDto posso passar um tópico como parâmetro, onde o
        // mesmo será convertido para um DTO.
        if (topic.isPresent()) {
            return ResponseEntity.ok(new DetailsTopicDto(topic.get()));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Mesmo sendo PUT, diferente do que fazemos no cadastro (TopicForm), nem sempre
     * podemos atualizar todos os campos. O ideal seria ter outra classe Form que
     * representa a atualização.
     * 
     * Para atualizar no banco de dados, não precisamos chamar nenhum método do
     * Repository, porque a partir do momento em que carreguei ele do banco de dados
     * pelo id, pela JPA ele já está sendo gerenciado. Qualquer atributo que eu
     * setar, no final do méotodo, o Spring roda dentro de uma transação. Então eu
     * vou carregar o tópico do banco de dados, no final do método ele vai commitar
     * a transação, a JPA vai detectar que foram alterados os atributos e ela vai
     * disparar o update no banco de dados automaticamente, não preciso chamar.
     * 
     * @param id
     * @param form
     * @return
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicFormUpdate form) {
        Optional<Topic> optional = topicRepository.findById(id);

        if (optional.isPresent()) {
            Topic topic = form.update(id, topicRepository);

            return ResponseEntity.ok(new TopicDto(topic));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Usamos o <?> para evitar o warning do generics
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Topic> optional = topicRepository.findById(id);

        if (optional.isPresent()) {
            topicRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
