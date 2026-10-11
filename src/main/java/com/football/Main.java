package com.football;

import com.football.notification.*;
import com.football.sender.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MessageSender telegram = new TelegramMessageSender();
        MessageSender email = new EmailMessageSender();
        MessageSender sms = new SmsMessageSender();
        MessageSender kafka = new KafkaEventSender();
        MessageSender push = new PushNotificationSender();

        System.out.println(
                "===================================="
        );
        System.out.println(
                " FOOTBALL MATCH NOTIFICATION SYSTEM"
        );
        System.out.println(
                "===================================="
        );

        System.out.println(
                "\nSCENARIO 1: Urgent + Telegram"
        );

        UrgentNotification urgent =
                new UrgentNotification(telegram, sms);

        urgent.send(
                "football_fan",
                "GOAL! Real Madrid 1-0 Barcelona!"
        );

        System.out.println(
                "\nSCENARIO 2: Encrypted + Email"
        );

        EncryptedNotification encrypted =
                new EncryptedNotification(email);

        encrypted.send(
                "fan@gmail.com",
                "Private football match information"
        );

        System.out.println(
                "\nSCENARIO 3: Batch + SMS"
        );

        ScheduledBatchNotification batch =
                new ScheduledBatchNotification(sms);

        batch.addRecipients(List.of(
                "+77010000001",
                "+77010000002",
                "+77010000003",
                "+77010000004",
                "+77010000005",
                "+77010000006",
                "+77010000007",
                "+77010000008",
                "+77010000009",
                "+77010000010"
        ));

        batch.send(
                null,
                "Real Madrid vs Barcelona starts tonight!"
        );

        System.out.println(
                "\nSCENARIO 4: Telegram -> Email"
        );

        Notification notification =
                new UrgentNotification(telegram);

        notification.send(
                "football_fan",
                "Match starts in 10 minutes!"
        );

        notification.setSender(email);

        notification.send(
                "fan@gmail.com",
                "Match has started!"
        );

        System.out.println(
                "\nSCENARIO 5: Telegram unavailable -> SMS"
        );

        TelegramMessageSender unavailableTelegram =
                new TelegramMessageSender(false);

        UrgentNotification fallbackNotification =
                new UrgentNotification(
                        unavailableTelegram,
                        sms
                );

        fallbackNotification.send(
                "+77010000001",
                "GOAL! Barcelona scores!"
        );

        System.out.println(
                "\nSCENARIO 6: Marketing + Push"
        );

        MarketingNotification marketing =
                new MarketingNotification(push);

        marketing.send(
                "device 1234",
                "Wath Real Madrid vs Barcelona tonight!"
        );

        System.out.println(
                "\nAll demonstrations completed!"
        );
    }
}

