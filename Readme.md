# Football Match Notification System

**Assignment3:**  Bridge Pattern  
**Student:** Daribayev Daniyar
**Group:** SE2516  

## 1. Project Description

In this assignment, I created a Football Match Notification System using Java and the Bridge Design Pattern.

The main idea of my project is to send football notifications through different channels like Telegram, Email, SMS, Kafka, and Push.

For example, the system can send messages about goals, upcoming matches, and football news.

All messages are simulated, so I did not use real APIs.

## 2. Bridge Pattern

I used the Bridge Pattern to separate notification types from message senders.

My project has four notification types:

- UrgentNotification
- EncryptedNotification
- ScheduledBatchNotification
- MarketingNotification

And five message senders:

- TelegramMessageSender
- EmailMessageSender
- SmsMessageSender
- KafkaEventSender
- PushNotificationSender

The `Notification` class is the Abstraction, and the `MessageSender` interface is the Implementor.

Each notification can work with different message senders.

## 3. Main Features

**UrgentNotification**

Sends important football news, for example, when a team scores a goal. If the main sender is unavailable, it tries another sender.

**EncryptedNotification**

Encodes the message using Base64 before sending it.

**ScheduledBatchNotification**

Sends the same message to several football fans and shows how many messages were successful or failed.

**MarketingNotification**

Sends promotional messages about upcoming football matches.

The system also supports changing the sender while the program is running.

## 4. Demo Scenarios

In the `Main.java` file, I demonstrated six scenarios:

1. Sending an urgent notification using Telegram.
2. Sending an encoded message using Email.
3. Sending batch notifications using SMS.
4. Changing the sender from Telegram to Email.
5. Using SMS as a fallback when Telegram is unavailable.
6. Sending a marketing notification using Push.

## 5. Bridge Pattern Questions

### 1. What are the two independent dimensions?

The two dimensions are notification types and message senders.

Notification types define what kind of message we send, and message senders define how we send it.

### 2. Where exactly is the Bridge?

The Bridge is in the `Notification` class, where I use the `MessageSender` interface.

```java
protected MessageSender sender;
```

This connects notifications with different senders.

### 3. Why is inheritance problematic here?

Without Bridge, I would need many classes like UrgentTelegramNotification, UrgentEmailNotification, and UrgentSmsNotification.

It would make the project bigger and harder to manage.

Bridge helps avoid creating too many classes.

### 4. What happens when a new provider is added?

I only need to create a new class that implements `MessageSender`.

For example, I can add WhatsAppMessageSender without changing the existing notification classes.


### 5. What happens when a new Notification type is added?

I create a new class that extends `Notification`.

For example, I can add SilentNotification and use it with the existing senders.

### 6. Why is this Bridge rather than Adapter?

Bridge separates notification types and message senders so they can change independently.

Adapter is used to connect classes with different interfaces.

In my project, I use Bridge because I want to work with different notification types and senders.

## 6. Unit Tests

I wrote 16 JUnit 5 tests to check the main functions of my project.

The tests check:

- Successful message delivery
- Unavailable senders
- Fallback mechanism
- SMS character limit
- Base64 encoding
- Batch notifications
- Runtime sender switching
- Push and Marketing notifications
- Kafka event sending

To run the tests:
You can run the tests in `NotificationGatewayTest` 

## 7. UML Diagram

It shows the Notification classes, MessageSender interface, and all sender implementations.

The diagram also shows the Bridge relationship between Notification and MessageSender.

## 8.  How to Run
You can run the code in `Main.java` file which inside in package com.football.

## 9. Conclusion
In this assignment3 I learned how the Bridge Pattern works in Java.

I also understand how o use them in Java.