package com.redhat.notification.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leed {
    private Long id;
    private String nome;
    private String email;
    private String whatsApp;
    private String cidade;
}
