package com.redhat.notification.application.service;

import com.redhat.notification.application.mapper.NotificationMapper;
import com.redhat.notification.application.model.Leed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private SMSService service;

    @Autowired
    private NotificationMapper mapper;

    public void processNotification(Leed leed) {
        service.sendSMS(mapper.convertLeedToSMS(leed));
    }
}
