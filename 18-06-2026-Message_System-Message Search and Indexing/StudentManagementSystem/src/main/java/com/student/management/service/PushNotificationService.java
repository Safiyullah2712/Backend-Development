package com.student.management.service;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    public void sendPushNotification(
            String receiverName,
            String senderName,
            String preview) {

        System.out.println(
                "PUSH NOTIFICATION -> " +
                receiverName +
                " | From: " +
                senderName +
                " | Message: " +
                preview);
    }
}
