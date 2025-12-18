package br.com.fiap.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;

import java.util.List;

public class EmailQueueFunction {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @FunctionName("ProcessNotificationQueue")
    public void run(
        @QueueTrigger(
            name = "message",
            queueName = "queue-cursos",
            connection = "AzureWebJobsStorage"
        )
        String messageJson,
        final ExecutionContext context
    ) {
        context.getLogger().info("Received message: " + messageJson);

        try {
            EmailService emailService = new EmailService();

            QueueNotification notification =
                objectMapper.readValue(messageJson, QueueNotification.class);

            String subject;
            String body;

            switch (notification.getType()) {
                case "BAD_REVIEW" -> {
                    subject = "Alert: Negative Review Received";
                    var payload = notification.getPayload();
                    body = String.format(
                        "You received a negative review.\nRating: %d\nComment: %s",
                        payload.getRating(),
                        payload.getReviewContent()
                    );
                }
                default -> {
                    context.getLogger().warning(
                        "Unknown notification type: " + notification.getType()
                    );
                    return;
                }
            }

            emailService.sendEmail(
                subject,
                body,
                List.of(notification.getRecipientEmail())
            );

            context.getLogger().info("Email sent successfully");

        } catch (Exception e) {
            context.getLogger().severe("Error processing message: " + e.getMessage());
            throw new RuntimeException(e); // triggers retry
        }
    }
}
