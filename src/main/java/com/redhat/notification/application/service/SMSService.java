package com.redhat.notification.application.service;

import com.redhat.notification.application.model.SMSNotification;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SMSService {
    private static final Logger logger = LoggerFactory.getLogger(SMSService.class);
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSMS(SMSNotification smsNotification) {
        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                new PhoneNumber(smsNotification.getPhoneNumber()),
                new PhoneNumber(fromPhoneNumber),
                smsNotification.getText()
        ).create();

        System.out.println("Mensagem SMS enviada com sucesso! SID: " + message.getSid());
    }
}
