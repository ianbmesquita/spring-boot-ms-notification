package com.redhat.notification.application.service;

import com.redhat.notification.application.model.EmailNotification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(EmailNotification emailNotification) {
        log.info("Enviando e-mail com sucesso para " + emailNotification.getEmailTo());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(emailNotification.getEmailFrom());
            messageHelper.setTo(emailNotification.getEmailTo());
            messageHelper.setSubject(emailNotification.getSubject());
            messageHelper.setText(emailNotification.getText());

            mailSender.send(message);

            log.info("E-mail enviado com sucesso para " + emailNotification.getEmailTo());
        } catch (MessagingException exception) {
            throw new RuntimeException("Ocorreu erro ao enviar a mensagem: " + exception.getMessage());
        }
    }
}
