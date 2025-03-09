package com.springkafka.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springkafka.notificationservice.model.NotificationRecord;
import com.springkafka.notificationservice.model.NotificationType;

@Service
public class NotificationService {

  @Autowired
  private KafkaTemplate<String, NotificationRecord> kafkaTemplate;

  public String sendNotification(NotificationRecord notification) {

    String topic = notification.type().equals(NotificationType.EMAIL) ? "notificacoes-email" : "notificacoes-sms";

    System.out.println(topic);

    System.out.println(notification);

    return "success";
  }

}
