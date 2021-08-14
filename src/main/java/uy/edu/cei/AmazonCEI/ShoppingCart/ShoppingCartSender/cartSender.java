package uy.edu.cei.AmazonCEI.ShoppingCart.ShoppingCartSender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.SHOPPING_CART_QUEUE;

@Slf4j
@Component
@AllArgsConstructor
public class cartSender {
    private final JmsTemplate jmsTemplate;



}
