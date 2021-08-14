package uy.edu.cei.AmazonCEI.Notification.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.Notification.services.NotificationService;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.models.Notification;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.CHECKOUT_QUEUE_FOR_NOTIFICATION;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @JmsListener(destination = CHECKOUT_QUEUE_FOR_NOTIFICATION)
    public void receiveMessage(@Payload CheckoutMessage payload) {
        log.info("checkout action: {}", payload);
        Notification notification = payload.getNotification();
        if(payload.getAction()== CheckoutAction.SEND_NOTIFICATION){
            notificationService.send(notification);
        }
    }
}
