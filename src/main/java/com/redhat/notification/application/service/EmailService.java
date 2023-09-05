package com.redhat.notification.application.service;

import com.redhat.notification.application.model.EmailNotification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(EmailNotification emailNotification) {
        logger.info("Enviando e-mail com sucesso para " + emailNotification.getEmailTo());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(emailNotification.getEmailFrom());
            messageHelper.setTo(emailNotification.getEmailTo());
            messageHelper.setSubject(emailNotification.getSubject());
            messageHelper.setText(emailNotification.getText());

            mailSender.send(message);

            logger.info("E-mail enviado com sucesso para " + emailNotification.getEmailTo());
        } catch (MessagingException exception) {
            var messageError = "Ocorreu erro ao enviar a mensagem: " + exception.getMessage();

            logger.error(messageError);
            throw new RuntimeException(messageError);
        }
    }
}
