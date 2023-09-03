package com.redhat.notification.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.notification.application.model.EmailNotification;
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

    public EmailNotification convertLeedToEmail(Leed leed) {
        return EmailNotification.builder()
                .ownerRef("Ian Mesquita - Demo")
                .emailFrom("ian.mesquita.demo@gmail.com")
                .emailTo(leed.getEmail())
                .subject("Parabéns! Você foi cadastrado com sucesso")
                .text(String.format("Olá %s, informamos que seu cadastro foi efetuado com sucesso. \n" +
                        "Fique atento que em breve entraremos em contato pelo número %s",
                        leed.getNome(), leed.getWhatsApp()))
                .build();
    }
}
