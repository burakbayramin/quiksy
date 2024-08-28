package com.burak.kafka;


import com.burak.email.EmailService;
import com.burak.kafka.order.OrderConfirmation;
import com.burak.kafka.payment.PaymentConfirmation;
import com.burak.notification.Notification;
import com.burak.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.burak.notification.NotificationType.ORDER_CONFIRMATION;
import static com.burak.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        var customerName = paymentConfirmation.userFirstname() + " " + paymentConfirmation.userLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.userEmail(),
                customerName,
                BigDecimal.valueOf(paymentConfirmation.amount()),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.user().firstname() + " " + orderConfirmation.user().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.user().email(),
                customerName,
                BigDecimal.valueOf(orderConfirmation.totalAmount()),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
