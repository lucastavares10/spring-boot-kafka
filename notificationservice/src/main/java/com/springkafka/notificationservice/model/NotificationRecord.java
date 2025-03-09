package com.springkafka.notificationservice.model;

public record NotificationRecord(String userId, String message, NotificationType type) {

}
