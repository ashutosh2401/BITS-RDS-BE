package com.resume.userservice.notification.dto;

public class NotificationMessage {
    private String recipientEmail;
    private String subject;
    private String body;

    // Constructors
    public NotificationMessage() {}
    public NotificationMessage(String recipientEmail, String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.body = body;
    }

    // Getters and Setters
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String recipientEmail) { this.recipientEmail = recipientEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}
