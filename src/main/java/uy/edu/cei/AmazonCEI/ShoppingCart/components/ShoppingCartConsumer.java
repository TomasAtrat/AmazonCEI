package uy.edu.cei.AmazonCEI.ShoppingCart.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.ShoppingCart.mappers.ShoppingCartMapper;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;
import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.SHOPPING_CART_QUEUE;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class ShoppingCartConsumer {

     final private ShoppingCartMapper shoppingCatMapper;

    @JmsListener( destination = SHOPPING_CART_QUEUE )
    public void receiveMessage(@Payload ShoppingCartMessage payload) {
        log.info("shopping cart action: {}", payload);
        Item item = payload.getItem();
        UUID userUUID = payload.getUserUUID();
        if (payload.getAction() == Action.ADD_ITEM_TO_CART) {
            shoppingCatMapper.addItemToCart(userUUID, item);
        }
    }




}
