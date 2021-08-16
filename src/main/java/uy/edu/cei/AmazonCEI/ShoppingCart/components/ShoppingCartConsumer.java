package uy.edu.cei.AmazonCEI.ShoppingCart.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.ShoppingCart.services.ShoppingCartServices;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;

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
        }else{
            shoppingCartService.close(payload.getShoppingCart_uuid());
        }
    }
}
