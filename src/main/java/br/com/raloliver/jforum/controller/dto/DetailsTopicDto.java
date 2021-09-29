package br.com.raloliver.jforum.controller.dto;

import br.com.raloliver.jforum.model.Topic;
import br.com.raloliver.jforum.model.TopicStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsTopicDto {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime dateCreation;
  private String authorName;
  private TopicStatus status;
  private List<ReplyDto> replies;

  public DetailsTopicDto(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.dateCreation = topic.getDateCreation();
    this.authorName = topic.getAuthor().getName();
    this.status = topic.getStatus();
    this.replies = new ArrayList<>();
    this.replies.addAll(
        topic.getReplies().stream().map(ReplyDto::new).collect(Collectors.toList())
      );
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

  public String getAuthorName() {
    return authorName;
  }

  public TopicStatus getStatus() {
    return status;
  }

  public List<ReplyDto> getReplies() {
    return replies;
  }
}
