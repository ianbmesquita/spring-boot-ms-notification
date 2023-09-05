package com.redhat.notification.application.service;

import com.redhat.notification.application.mapper.NotificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private NotificationMapper mapper;

    @Autowired
    private NotificationService service;

    @KafkaListener(topics = "leed-notification", groupId = "3af4c3a0-13b4-4fe4-9f50-af0462b95808")
    public void receiveMessage(String message) {
        logger.info("Mensagem recebida: " + message);
        service.processNotification(mapper.convertJsonStringToModel(message));
    }
}
