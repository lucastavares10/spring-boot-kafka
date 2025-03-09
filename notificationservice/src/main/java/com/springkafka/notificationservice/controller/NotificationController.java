package com.springkafka.notificationservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springkafka.notificationservice.model.NotificationRecord;
import com.springkafka.notificationservice.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @PostMapping("/send-notification")
  public String postMethodName(@RequestBody NotificationRecord notificationRecord) {
    return this.notificationService.sendNotification(notificationRecord);
  }

}
