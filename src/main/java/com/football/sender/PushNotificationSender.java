package com.football.sender;

public class PushNotificationSender implements MessageSender {

    private boolean available;

    public PushNotificationSender() {
        this.available = true;
    }

    public PushNotificationSender(boolean available) {
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
            System.out.println("Push service is unavailable!");
            return false;
        }

        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        System.out.println("\n--- PUSH NOTIFICATION ---");
        System.out.println("Device: " + recipient);
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);
        System.out.println("Push notification delivered!");

        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Push";
    }
}