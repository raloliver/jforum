package br.com.raloliver.jforum.controller.dto;

import br.com.raloliver.jforum.model.Reply;
import java.time.LocalDateTime;

public class ReplyDto {

  private Long id;
  private String message;
  private LocalDateTime dateCreation;
  private String authorName;

  public ReplyDto(Reply reply) {
    this.id = reply.getId();
    this.message = reply.getMessage();
    this.dateCreation = reply.getDateCreation();
    this.authorName = reply.getAuthor().getName();
  }

  public Long getId() {
    return id;
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
}
