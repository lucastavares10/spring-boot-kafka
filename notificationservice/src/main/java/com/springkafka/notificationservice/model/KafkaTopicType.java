package com.springkafka.notificationservice.model;

public enum KafkaTopicType {
  EMAIL_TOPIC("notificacoes-email"),
  SMS_TOPIC("notificacoes-sms");

  public String name;

  KafkaTopicType(String name) {
    this.name = name;
  }

}