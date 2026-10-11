package com.football.sender;

public class SmsMessageSender implements MessageSender {

    private boolean available;

    private int maxLength = 160;

    public SmsMessageSender() {
        this.available = true;
    }

    public SmsMessageSender(boolean available) {
        this.available = available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setMaxLength(int maxLength) {
        if (maxLength <= 0) {
            throw new IllegalArgumentException(
                    "SMS limit must be positive"
            );
        }

        this.maxLength = maxLength;
    }

    @Override
    public boolean sendMessage(
            String recipient,
            String title,
            String body
    ) {
        if (!isAvailable()) {
            System.out.println("SMS is unavailable!");
            return false;
        }

        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        String message = title + ": " + body;

        if (message.length() > maxLength) {
            System.out.println(
                    "SMS failed: message exceeds "
                            + maxLength + " characters!"
            );
            return false;
        }

        System.out.println("\n--- SMS ---");
        System.out.println("Phone: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("SMS sent successfully!");

        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Sms";
    }
}
