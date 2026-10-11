package com.football.sender;

public class TelegramMessageSender implements MessageSender {

    private boolean available;

    public TelegramMessageSender() {
        this.available = true;
    }

    public TelegramMessageSender(boolean available) {
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
            System.out.println("Telegram is unavailable!");
            return false;
        }

        if (recipient == null || recipient.isBlank()) {
            return false;
        }

        System.out.println("\n--- TELEGRAM ---");
        System.out.println("To: @" + recipient);
        System.out.println("⚽ " + title);
        System.out.println("**" + body + "**");
        System.out.println("Telegram message sent!");

        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Telegram";
    }
}