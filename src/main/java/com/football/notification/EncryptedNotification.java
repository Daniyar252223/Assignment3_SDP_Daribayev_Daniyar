package com.football.notification;

import com.football.sender.MessageSender;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptedNotification extends Notification {

    public EncryptedNotification(MessageSender sender) {
        super(sender);
    }

    public String encodeMessage(String message) {
        return Base64.getEncoder().encodeToString(
                message.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public void send(
            String recipient,
            String message
    ) {
        if (!sender.isAvailable()) {
            System.out.println(
                    "Encrypted notification failed: "
                            + sender.getChannelName()
                            + " unavailable"
            );
            return;
        }

        String encoded = encodeMessage(message);

        sender.sendMessage(
                recipient,
                "Encrypted Football Update",
                encoded
        );
    }
}

