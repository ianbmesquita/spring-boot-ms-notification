package com.redhat.notification.application.service;

import com.redhat.notification.application.model.Leed;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void processNotification(Leed leed) {
        System.out.println(leed.toString());
    }
}
