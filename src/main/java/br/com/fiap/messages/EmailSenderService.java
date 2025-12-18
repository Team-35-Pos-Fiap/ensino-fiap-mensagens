package br.com.fiap.messages;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;

import java.util.List;
import java.util.stream.Collectors;

public class EmailSenderService implements IEmailSender {

    private final EmailClient emailClient;

    public EmailSenderService(String connectionString) {
        this.emailClient = new EmailClientBuilder()
            .connectionString(connectionString)
            .buildClient();
    }

    @Override
    public void send(String body, List<String> recipients, String sender, String subject) {
        EmailMessage message = new EmailMessage()
            .setSenderAddress(sender)
            .setToRecipients(
                recipients.stream()
                    .map(EmailAddress::new)
                    .collect(Collectors.toList())
            )
            .setSubject(subject)
            .setBodyPlainText(body);

        emailClient.beginSend(message); // async
    }
}