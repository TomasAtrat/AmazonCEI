package uy.edu.cei.AmazonCEI.ShoppingCart.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.ShoppingCart.services.ShoppingCartServices;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.CHECKOUT_QUEUE_FOR_CLOSE;
import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.SHOPPING_CART_QUEUE;

@Component
@Slf4j
@AllArgsConstructor
public class ShoppingCartConsumer {

     final private ShoppingCartServices shoppingCartService;

    @JmsListener( destination = SHOPPING_CART_QUEUE )
    public void receiveMessage(@Payload ShoppingCartMessage payload) {
        log.info("shopping cart action: {}", payload);
        if (payload.getAction() == Action.ADD_ITEM_TO_CART) {
            shoppingCartService.add(payload.getShoppingCart_uuid(), payload.getItemInShoppingCart());
        }
        else if(payload.getAction() == Action.REMOVE_ITEM_FROM_CART)
        {
            shoppingCartService.delete(payload.getShoppingCart_uuid(), payload.getItem());
        }
    }

    @JmsListener(destination = CHECKOUT_QUEUE_FOR_CLOSE )
    public void receiveCheckoutMessage(@Payload CheckoutMessage payload){
        log.info("checkout action: {}", payload);
        if(payload.getAction() == CheckoutAction.CLOSE_SHOPPING_CART){
            shoppingCartService.close(payload.getShoppingCartUUID());
        }
    }
}
