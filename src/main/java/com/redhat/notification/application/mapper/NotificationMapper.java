package com.redhat.notification.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.notification.application.model.Leed;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationMapper {
    public Leed convertJsonStringToModel(String message) {
        try {
            return new ObjectMapper().readValue(message, Leed.class);
        } catch (IOException exception) {
            throw new RuntimeException("Ocorreu erro ao converter o JSON para objeto Leed: " + exception.getMessage());
        }
    }
}
