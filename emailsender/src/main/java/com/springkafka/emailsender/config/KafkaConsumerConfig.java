package com.springkafka.emailsender.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.springkafka.emailsender.model.KafkaTopics;
import com.springkafka.emailsender.model.NotificationRecord;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String kafkaAddress;

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, NotificationRecord> kafkaListenerContainerFactory(
      ConsumerFactory<String, NotificationRecord> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, NotificationRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    return factory;
  }

  @Bean
  public ConsumerFactory<String, NotificationRecord> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerProps(),
        new StringDeserializer(),
        new JsonDeserializer<>(NotificationRecord.class));
  }

  private Map<String, Object> consumerProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, NotificationRecord.class.getName());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return props;
  }

  @KafkaListener(topics = KafkaTopics.EMAIL_TOPIC, groupId = "email-group")
  public void listen(NotificationRecord notification, Acknowledgment ack) {
    try {
      System.out.println("Processando EMAIL: " + notification);
      ack.acknowledge();
    } catch (Exception e) {
      System.err.println("Erro ao processar EMAIL: " + e.getMessage());
    }
  }
}
