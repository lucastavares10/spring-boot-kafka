package com.springkafka.smssender.config;

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
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.springkafka.smssender.model.KafkaTopics;
import com.springkafka.smssender.model.NotificationRecord;

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

  @KafkaListener(topics = KafkaTopics.SMS_TOPIC, groupId = "group")
  public void listen1(NotificationRecord data) {
    System.out.println(data);
  }
}
