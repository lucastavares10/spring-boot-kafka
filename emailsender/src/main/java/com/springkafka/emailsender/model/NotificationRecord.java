package com.springkafka.emailsender.model;

public record NotificationRecord(String userId, String message, NotificationType type) {

}
