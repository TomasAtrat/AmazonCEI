package uy.edu.cei.AmazonCEI.Checkout.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.*;

@Slf4j
@Component
@AllArgsConstructor
public class CheckoutSender {
    private final JmsTemplate jmsTemplate;

    public void sendUpdateMessage(CheckoutMessage message) {
        log.info("message: {}", message);
        jmsTemplate.convertAndSend(CHECKOUT_QUEUE_FOR_UPDATE, message);
    }

    public void sendNotificationMessage(CheckoutMessage message) {
        log.info("message: {}", message);
        jmsTemplate.convertAndSend(CHECKOUT_QUEUE_FOR_NOTIFICATION, message);
    }

    public void sendCloseMessage(CheckoutMessage message){
        log.info("message: {}", message);
        jmsTemplate.convertAndSend(CHECKOUT_QUEUE_FOR_CLOSE, message);
    }
}
