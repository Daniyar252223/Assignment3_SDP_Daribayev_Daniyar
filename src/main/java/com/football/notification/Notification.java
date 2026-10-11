package com.football.notification;

import com.football.sender.MessageSender;

public abstract class Notification {
    protected MessageSender sender;

    protected Notification(MessageSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException(
                    "Sender cannot be null"
            );
        }

        this.sender = sender;
    }

    public void setSender(MessageSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException(
                    "Sender cannot be null"
            );
        }

        this.sender = sender;
    }

    public MessageSender getSender() {
        return sender;
    }
    public abstract void send(
            String recipient,
            String message
    );
}
