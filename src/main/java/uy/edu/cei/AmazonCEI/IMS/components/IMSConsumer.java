package uy.edu.cei.AmazonCEI.IMS.components;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.CHECKOUT_QUEUE_FOR_UPDATE;
import static uy.edu.cei.AmazonCEI.Configuration.ActiveMQConfig.LIST_USER_ITEM;
import java.util.List;
@Component
@Slf4j
@AllArgsConstructor
public class IMSConsumer {
    private final IMSService imsService;

    @JmsListener(destination = CHECKOUT_QUEUE_FOR_UPDATE)
    public void receiveMessage(@Payload CheckoutMessage payload) {
        log.info("checkout action: {}", payload);
        ItemInShoppingCart item = payload.getItemInShoppingCart();
        if(payload.getAction()== CheckoutAction.UPDATE_STOCK){
            imsService.update(item);
        }
    }


}
