package br.com.raloliver.jforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class JforumApplication {

  public static void main(String[] args) {
    SpringApplication.run(JforumApplication.class, args);
  }
}
