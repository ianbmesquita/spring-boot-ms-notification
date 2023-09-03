package com.redhat.notification.application.service;

import com.redhat.notification.application.model.EmailNotification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(EmailNotification emailNotification) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(emailNotification.getEmailFrom());
            messageHelper.setTo(emailNotification.getEmailTo());
            messageHelper.setSubject(emailNotification.getSubject());
            messageHelper.setText(emailNotification.getText());

            mailSender.send(message);

            System.out.println("Email enviado com sucesso para " + emailNotification.getEmailTo());
        } catch (MessagingException exception) {
            throw new RuntimeException("Ocorreu erro ao enviar a mensagem: " + exception.getMessage());
        }
    }
}
