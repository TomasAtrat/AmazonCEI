package uy.edu.cei.AmazonCEI.Checkout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.Checkout.clients.IMSClientCH;
import uy.edu.cei.AmazonCEI.Checkout.clients.ShoppingCartClient;
import uy.edu.cei.AmazonCEI.Checkout.components.CheckoutSender;
import uy.edu.cei.AmazonCEI.Checkout.mappers.CheckoutMapper;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutService {
    private final CheckoutMapper checkoutMapper;
    private final CheckoutSender checkoutSender;
    private final IMSClientCH imsClientCH;
    private final ShoppingCartClient shoppingCartClient;
    private Notification notification;
    @Autowired
    public CheckoutService(CheckoutMapper checkoutMapper, CheckoutSender checkoutSender, IMSClientCH imsClientCH, ShoppingCartClient shoppingCartClient) {
        this.checkoutMapper = checkoutMapper;
        this.checkoutSender = checkoutSender;
        this.imsClientCH = imsClientCH;
        this.shoppingCartClient= shoppingCartClient;
    }

    public void checkout(String uuid_user){
        ShoppingCart shoppingCart= this.shoppingCartClient.getCart(uuid_user);
        List<ItemInShoppingCart> itemsInCart= this.shoppingCartClient.getItemsInCart(shoppingCart.getUuid());

        Checkout chout= this.add(shoppingCart.getUuid(), itemsInCart);
        //Llamar a pasarela de datos
        closeShoppingCart(shoppingCart.getUuid());
        sendNotification(chout.getUuid());
    }

    public Checkout add(String shopping_cart_uuid, List<ItemInShoppingCart> itemsInCart){
        Checkout checkout= new Checkout();
        checkout.setShopping_cart_uuid(shopping_cart_uuid);
        checkout.setUuid(UUID.randomUUID().toString());
        checkout.setTotal_cost(this.totalCost(checkout, itemsInCart));
        this.checkoutMapper.add(checkout);
        return checkout;
    }

    public void updateStock(Checkout checkout, ItemInShoppingCart item){
        final CheckoutMessage message = CheckoutMessage.builder()
                .action(CheckoutAction.UPDATE_STOCK)
                .itemInShoppingCart(item)
                .checkout_uuid(checkout.getUuid())
                .shoppingCartUUID(checkout.getShopping_cart_uuid())
                .build();

        checkoutSender.sendUpdateMessage(message);
    }

    public float totalCost(Checkout checkout, List<ItemInShoppingCart> colItems){
        float total=0;
        List<ItemToNotificate> notificationItems= new ArrayList<>();
        for (ItemInShoppingCart item: colItems) {
            final Item it= this.imsClientCH.fetchItem(item.getUuid_item());
            total+= it.getCost()*item.getAmount();
            ItemToNotificate itemToNotificate= ItemToNotificate.builder()
                    .item_uuid(it.getUuid())
                    .amount(item.getAmount())
                    .name(it.getName())
                    .cost(it.getCost())
                    .build();
            notificationItems.add(itemToNotificate);
            this.updateStock(checkout, item);
        }
        this.notification= new Notification();
        this.notification.setTotal_cost(total);
        this.notification.setColItems(notificationItems);
        return total;
    }

    public void closeShoppingCart(String shoppingCart_uuid){
        final CheckoutMessage message = CheckoutMessage.builder()
                .action(CheckoutAction.CLOSE_SHOPPING_CART)
                .shoppingCartUUID(shoppingCart_uuid)
                .build();

        checkoutSender.sendCloseMessage(message);
    }

    public void sendNotification(String checkout_uuid){
        final CheckoutMessage message = CheckoutMessage.builder()
                .action(CheckoutAction.SEND_NOTIFICATION)
                .checkout_uuid(checkout_uuid)
                .notification(this.notification)
                .build();

        checkoutSender.sendNotificationMessage(message);
    }
}
