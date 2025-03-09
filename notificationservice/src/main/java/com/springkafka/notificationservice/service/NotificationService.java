package com.springkafka.notificationservice.service;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.springkafka.notificationservice.model.NotificationRecord;
import com.springkafka.notificationservice.model.NotificationType;

@Service
public class NotificationService {

  @Autowired
  private KafkaTemplate<String, NotificationRecord> kafkaTemplate;

  public void sendNotification(NotificationRecord notification) {
    String topic = notification.type().equals(NotificationType.EMAIL) ? "notificacoes-email" : "notificacoes-sms";

    CompletableFuture<SendResult<String, NotificationRecord>> future = kafkaTemplate.send(topic, notification);

    future.whenComplete((result, ex) -> {
      System.out.println(result);
    });

  }

}
