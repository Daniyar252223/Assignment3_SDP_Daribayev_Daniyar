package com.football.notification;

import com.football.sender.MessageSender;

import java.util.ArrayList;
import java.util.List;

public class ScheduledBatchNotification extends Notification {

    private final List<String> recipients = new ArrayList<>();

    private int total;
    private int successful;
    private int failed;

    public ScheduledBatchNotification(MessageSender sender) {
        super(sender);
    }

    public void addRecipient(String recipient) {
        recipients.add(recipient);
    }

    public void addRecipients(List<String> newRecipients) {
        recipients.addAll(newRecipients);
    }

    public int getTotal() {
        return total;
    }

    public int getSuccessful() {
        return successful;
    }

    public int getFailed() {
        return failed;
    }

    @Override
    public void send(
            String recipient,
            String message
    ) {
        if (recipient != null && !recipient.isBlank()) {
            recipients.add(recipient);
        }

        total = recipients.size();
        successful = 0;
        failed = 0;

        for (String fan : recipients) {
            boolean success = false;

            if (sender.isAvailable()) {
                success = sender.sendMessage(
                        fan,
                        "Football Match Reminder",
                        message
                );
            }

            if (success) {
                successful++;
            } else {
                failed++;
            }
        }

        System.out.println("\n---Batch Summary---");
        System.out.println("Total: " + total);
        System.out.println("Successfull: " + successful);
        System.out.println("Failed: " + failed);
    }
}
