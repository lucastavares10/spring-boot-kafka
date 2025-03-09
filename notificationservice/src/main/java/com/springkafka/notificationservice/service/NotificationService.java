package com.springkafka.notificationservice.service;

import org.springframework.stereotype.Service;

import com.springkafka.notificationservice.model.NotificationRecord;

@Service
public class NotificationService {

  public String sendNotification(NotificationRecord notificationRecord) {

    System.out.println(notificationRecord);

    return "success";
  }

}
