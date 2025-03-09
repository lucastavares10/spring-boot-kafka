package com.springkafka.notificationservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import com.springkafka.notificationservice.model.KafkaTopicType;

@Configuration
public class KafkaTopicConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String kafkaAddress;

  @Bean
  public KafkaAdmin admin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic emailTopic() {
    return TopicBuilder.name(KafkaTopicType.EMAIL_TOPIC.name)
        .partitions(1)
        .replicas(2)
        .compact()
        .build();
  }

  @Bean
  public NewTopic smsTopic() {
    return TopicBuilder.name(KafkaTopicType.SMS_TOPIC.name)
        .partitions(1)
        .replicas(2)
        .compact()
        .build();
  }

}
