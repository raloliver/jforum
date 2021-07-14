package br.com.raloliver.jforum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.raloliver.jforum.model.Topic;

public class TopicDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dateCreation;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dateCreation = topic.getDateCreation();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    /**
     * Com a API de stream do Java 8 é possível mapear Topic para TopicDto em apenas
     * uma linha
     * 
     * @param topics
     * @return
     */
    public static List<TopicDto> mapper(List<Topic> topics) {
        return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }

}
