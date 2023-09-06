package com.redhat.notification.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SMSNotification {
    private String name;
    private String phoneNumber;
    private String text;
}
