package com.football.notification;

import com.football.sender.MessageSender;

public class MarketingNotification extends Notification {

    public MarketingNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(
            String recipient,
            String message
    ) {
        if (!sender.isAvailable()) {
            System.out.println(
                    "Marketing notification failed: "
                            + sender.getChannelName()
                            + " unavailable"
            );
            return;
        }

        String marketingMessage =
                "⚽ FOOTBALL SPECIAL! ⚽\n"
                        + message
                        + "\nDon't miss the match!";

        sender.sendMessage(
                recipient,
                "Football Promotion",
                marketingMessage
        );
    }
}