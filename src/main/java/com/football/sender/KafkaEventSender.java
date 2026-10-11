package com.football.sender;

public class KafkaEventSender implements MessageSender {

    private boolean available;

    public KafkaEventSender() {
        this.available = true;
    }

    public KafkaEventSender(boolean available) {
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
            System.out.println("Kafka is unavailable!");
            return false;
        }

        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        System.out.println("\n--- KAFKA EVENT ---");
        System.out.println("Topic: football-events");
        System.out.println("Key: " + recipient);
        System.out.println("Event title: " + title);
        System.out.println("Payload: " + body);
        System.out.println("Event published successfully!");

        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Kafka";
    }
}