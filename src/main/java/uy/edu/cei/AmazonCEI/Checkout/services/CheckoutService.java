package uy.edu.cei.AmazonCEI.Checkout.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.Checkout.clients.IMSClientCH;
import uy.edu.cei.AmazonCEI.Checkout.components.CheckoutSender;
import uy.edu.cei.AmazonCEI.Checkout.mappers.CheckoutMapper;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutAction;
import uy.edu.cei.AmazonCEI.common.messages.CheckoutMessage;
import uy.edu.cei.AmazonCEI.common.models.Checkout;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutService {
    private final CheckoutMapper checkoutMapper;
    private final CheckoutSender checkoutSender;
    private final IMSClientCH imsClientCH;
    private List<Item> colItems;
    @Autowired
    public CheckoutService(CheckoutMapper checkoutMapper, CheckoutSender checkoutSender, IMSClientCH imsClientCH) {
        this.checkoutMapper = checkoutMapper;
        this.checkoutSender = checkoutSender;
        this.imsClientCH = imsClientCH;
        this.colItems= new ArrayList<>();
    }

    public void checkout(UUID shopping_cart_uuid){
        //Obtener lista de items en carrito a partir de la obtención del carrito con un cliente web
        List<ItemInShoppingCart> itemsInCart= new ArrayList<>();
        Checkout chout= this.add(shopping_cart_uuid, itemsInCart); //calcula precio total y envía mensajes de actualización de stock
        //Llamar a pasarela de datos
        closeShoppingCart(shopping_cart_uuid);
        sendNotification(this.colItems, chout.getUuid());
    }

    public Checkout add(UUID shopping_cart_uuid, List<ItemInShoppingCart> itemsInCart){
        Checkout checkout= new Checkout();
        checkout.setShopping_cart_uuid(shopping_cart_uuid);
        checkout.setUuid(UUID.randomUUID());
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
        //Calcula precio final y envía mensajes de actualización a la cola simultáneamente
        float total=0;
        for (ItemInShoppingCart item: colItems) {
            final Item it= this.imsClientCH.fetchItem(item.getItem_uuid());
            total+= it.getCost()*item.getAmount();
            this.colItems.add(it);
            this.updateStock(checkout, item);
        }
        return total;
    }

    public void closeShoppingCart(UUID shoppingCart_uuid){
        final CheckoutMessage message = CheckoutMessage.builder()
                .action(CheckoutAction.CLOSE_SHOPPING_CART)
                .shoppingCartUUID(shoppingCart_uuid)
                .build();

        checkoutSender.sendCloseMessage(message);
    }

    public void sendNotification(List<Item> colItems, UUID checkout_uuid){
        final CheckoutMessage message = CheckoutMessage.builder()
                .action(CheckoutAction.CLOSE_SHOPPING_CART)
                .checkout_uuid(checkout_uuid)
                .colItems(colItems)
                .build();

        checkoutSender.sendNotificationMessage(message);
    }
}
