package br.com.fiap.messages;

import java.util.List;

public class EmailService implements IEmailService {

    private final IEmailSender emailSender;
    private final String sender;

    public EmailService() {
        String connectionString = System.getenv("EMAIL_CONNECTION_STRING");
        this.sender = System.getenv("EMAIL_SENDER_ADDRESS");

        if (connectionString == null || sender == null) {
            throw new IllegalStateException("Email environment variables are missing");
        }

        this.emailSender = new EmailSenderService(connectionString);
    }

    @Override
    public void sendEmail(String subject, String body, List<String> recipients) {
        emailSender.send(body, recipients, sender, subject);
    }
}
