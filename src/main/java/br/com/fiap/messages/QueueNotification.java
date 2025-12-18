package br.com.fiap.messages;

public class QueueNotification {

    private String type;
    private String recipientEmail;
    private BadReviewPayload payload;
    private String timestamp;

    public QueueNotification() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public BadReviewPayload getPayload() {
        return payload;
    }

    public void setPayload(BadReviewPayload payload) {
        this.payload = payload;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class BadReviewPayload {
        private int rating;
        private String reviewContent;

        public BadReviewPayload() {}

        public int getRating() { return rating; }
        public void setRating(int rating) { this.rating = rating; }

        public String getReviewContent() { return reviewContent; }
        public void setReviewContent(String reviewContent) { this.reviewContent = reviewContent; }
    }
}
