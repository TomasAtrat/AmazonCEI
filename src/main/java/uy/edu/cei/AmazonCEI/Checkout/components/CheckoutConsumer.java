package uy.edu.cei.AmazonCEI.Checkout.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.Checkout.services.CheckoutService;
import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.SHOPPING_CART_QUEUE_FOR_CHECKOUT;

@Component
@Slf4j
@AllArgsConstructor
public class CheckoutConsumer {
    private final CheckoutService checkoutService;

    @JmsListener(destination = SHOPPING_CART_QUEUE_FOR_CHECKOUT)
    public void receiveMessage(@Payload ShoppingCartMessage payload) {
        log.info("checkout action: {}", payload);
        String cart_uuid = payload.getShoppingCart_uuid();
        if(payload.getAction()== Action.CLOSE_CART){
            checkoutService.checkout(cart_uuid);
        }
    }
}
