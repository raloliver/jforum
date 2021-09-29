package br.com.raloliver.jforum.controller.form;

import br.com.raloliver.jforum.model.Topic;
import br.com.raloliver.jforum.repository.TopicRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class TopicFormUpdate {

  @NotNull
  @NotEmpty
  @Length(min = 5, max = 160)
  private String title;

  @NotNull
  @NotEmpty
  @Length(min = 50, max = 3000)
  private String message;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Topic update(Long id, TopicRepository topicRepository) {
    Topic topic = topicRepository.getById(id);

    topic.setTitle(this.title);
    topic.setMessage(this.message);

    return topic;
  }
}
