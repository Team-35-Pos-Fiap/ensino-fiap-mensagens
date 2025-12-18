package br.com.fiap.messages;

import java.util.List;

public interface IEmailSender {
    void send(String body, List<String> recipients, String sender, String subject);
}
