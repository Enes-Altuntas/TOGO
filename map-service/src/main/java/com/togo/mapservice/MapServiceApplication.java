package com.togo.mapservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableElasticsearchRepositories
public class MapServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MapServiceApplication.class, args);
  }
}
