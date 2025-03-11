package com.springkafka.smssender.model;

public record NotificationRecord(String userId, String message, NotificationType type) {

}
