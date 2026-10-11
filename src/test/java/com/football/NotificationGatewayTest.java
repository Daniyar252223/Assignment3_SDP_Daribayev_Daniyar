package com.football;

import com.football.notification.*;
import com.football.sender.*;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationGatewayTest {

    @Test
    void telegramSuccessfulDelivery() {
        TelegramMessageSender sender = new TelegramMessageSender();

        assertTrue(sender.sendMessage(
                "fan", "Goal", "Real Madrid scored"
        ));
    }

    @Test
    void telegramUnavailable() {
        TelegramMessageSender sender = new TelegramMessageSender(false);

        assertFalse(sender.isAvailable());
        assertFalse(sender.sendMessage(
                "fan", "Goal", "Match update"
        ));
    }

    @Test
    void emailSuccessfulDelivery() {
        EmailMessageSender sender = new EmailMessageSender();

        assertTrue(sender.sendMessage(
                "fan@gmail.com",
                "Football",
                "Match starts soon"
        ));
    }

    @Test
    void smsRejectsLongMessage() {
        SmsMessageSender sender = new SmsMessageSender();

        String longMessage = "A".repeat(170);

        assertFalse(sender.sendMessage(
                "+77010000001",
                "Football",
                longMessage
        ));
    }

    @Test
    void encryptedMessageUsesBase64() {
        EncryptedNotification notification =
                new EncryptedNotification(
                        new EmailMessageSender()
                );

        String encoded = notification.encodeMessage("GOAL!");

        String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8
        );

        assertEquals("GOAL!", decoded);
        assertNotEquals("GOAL!", encoded);
    }

    @Test
    void batchSuccessfulDelivery() {
        ScheduledBatchNotification batch =
                new ScheduledBatchNotification(
                        new SmsMessageSender()
                );

        batch.addRecipients(List.of(
                "+77010000001",
                "+77010000002",
                "+77010000003"
        ));

        batch.send(null, "Match reminder");

        assertEquals(3, batch.getTotal());
        assertEquals(3, batch.getSuccessful());
        assertEquals(0, batch.getFailed());
    }

    @Test
    void runtimeSenderSwitching() {
        Notification notification =
                new UrgentNotification(
                        new TelegramMessageSender()
                );

        assertEquals(
                "Telegram",
                notification.getSender().getChannelName()
        );

        notification.setSender(
                new EmailMessageSender()
        );

        assertEquals(
                "Email",
                notification.getSender().getChannelName()
        );
    }

    @Test
    void fallbackWorks() {
        class RecordingSender implements MessageSender {
            boolean called = false;

            @Override
            public boolean sendMessage(
                    String recipient,
                    String title,
                    String body
            ) {
                called = true;
                return true;
            }

            @Override
            public boolean isAvailable() {
                return true;
            }

            @Override
            public String getChannelName() {
                return "Recording";
            }
        }

        RecordingSender fallback = new RecordingSender();

        UrgentNotification urgent =
                new UrgentNotification(
                        new TelegramMessageSender(false),
                        fallback
                );

        urgent.send("fan", "GOAL!");

        assertTrue(fallback.called);
    }

    @Test
    void pushSenderWorks() {
        PushNotificationSender sender =
                new PushNotificationSender();

        assertTrue(sender.sendMessage(
                "device_123",
                "Match",
                "Kickoff soon"
        ));
    }

    @Test
    void marketingNotificationWorks() {
        class RecordingSender implements MessageSender {
            String lastTitle;
            String lastBody;

            @Override
            public boolean sendMessage(
                    String recipient,
                    String title,
                    String body
            ) {
                lastTitle = title;
                lastBody = body;
                return true;
            }

            @Override
            public boolean isAvailable() {
                return true;
            }

            @Override
            public String getChannelName() {
                return "Recording";
            }
        }

        RecordingSender sender = new RecordingSender();

        MarketingNotification marketing =
                new MarketingNotification(sender);

        marketing.send(
                "fan",
                "Watch the final tonight"
        );

        assertEquals(
                "Football Promotion",
                sender.lastTitle
        );

        assertTrue(
                sender.lastBody.contains(
                        "Watch the final tonight"
                )
        );
    }

    @Test
    void KafkaEventWorks() {
        KafkaEventSender sender = new KafkaEventSender();

        assertTrue(sender.sendMessage(
                "mstch-01",
                "Red card",
                "Player received red card"
        ));
    }
}

