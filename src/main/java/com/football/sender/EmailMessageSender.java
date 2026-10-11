package com.football.sender;

public class EmailMessageSender implements MessageSender {

    private boolean available;

    public EmailMessageSender() {
        this.available = true;
    }

    public EmailMessageSender(boolean available) {
        this.available = available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(
            String recipient,
            String title,
            String body
    ) {
        if (!isAvailable()) {
            System.out.println("Email is unavailable!");
            return false;
        }

        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        System.out.println("\n--- EMAIL ---");
        System.out.println("To: " + recipient);
        System.out.println("Subject: " + title);
        System.out.println("Message: " + body);
        System.out.println("\nBest regards,");
        System.out.println("Football Notification Team");
        System.out.println("Email sent successfully!");

        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}