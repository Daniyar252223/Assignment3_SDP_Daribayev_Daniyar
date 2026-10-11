package com.football.notification;

import com.football.sender.MessageSender;

public class UrgentNotification extends Notification {

    private MessageSender fallbackSender;

    public UrgentNotification(MessageSender sender) {
        super(sender);
    }

    public UrgentNotification(
            MessageSender sender,
            MessageSender fallbackSender
    ) {
        super(sender);
        this.fallbackSender = fallbackSender;
    }

    public void setFallbackSender(
            MessageSender fallbackSender
    ) {
        this.fallbackSender = fallbackSender;
    }

    @Override
    public void send(
            String recipient,
            String message
    ) {
        String title = "[URGENT] Football Alert";
        String urgentMessage = "[URGENT] " + message;

        System.out.println(
                "\nPrimary channel: "
                        + sender.getChannelName()
        );

        if (sender.isAvailable()) {
            boolean success = sender.sendMessage(
                    recipient,
                    title,
                    urgentMessage
            );

            if (success) {
                return;
            }
        }

        System.out.println("Primary sender failed!");

        if (fallbackSender != null
                && fallbackSender.isAvailable()) {

            System.out.println(
                    "Trying fallback: "
                            + fallbackSender.getChannelName()
            );

            boolean success = fallbackSender.sendMessage(
                    recipient,
                    title,
                    urgentMessage
            );

            if (success) {
                System.out.println(
                        "Fallback successful!"
                );
                return;
            }

            System.out.println(
                    "Urgent Notification delivery failed"
            );
        }
    }
}