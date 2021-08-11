package uy.edu.cei.AmazonCEI.ShoppingCart.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.SHOPPING_CART_QUEUE;

@Slf4j
@Service
@AllArgsConstructor
public class ShoppingCartSender {

    private final JmsTemplate jmsTemplate;

    public void send(ShoppingCartMessage message) {
        log.info("message: {}", message);
        jmsTemplate.convertAndSend(SHOPPING_CART_QUEUE, message);
    }
}