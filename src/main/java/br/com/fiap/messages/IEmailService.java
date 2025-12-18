package br.com.fiap.messages;

import java.util.List;

public interface IEmailService {
    void sendEmail(String subject, String body, List<String> recipients);
}
