package uy.edu.cei.AmazonCEI.ShoppingCart.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uy.edu.cei.AmazonCEI.ShoppingCart.ShoppingCartSender.cartSender;
import uy.edu.cei.AmazonCEI.ShoppingCart.clients.IMSClient;
import uy.edu.cei.AmazonCEI.ShoppingCart.components.ShoppingCartSender;
import uy.edu.cei.AmazonCEI.ShoppingCart.mappers.ShoppingCartMapper;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServices {
    private ShoppingCartMapper mapeo;
    private final IMSClient Client;
    private final ShoppingCartSender cartSender;
    @Autowired
    public ShoppingCartServices(ShoppingCartMapper mapper, IMSClient client,ShoppingCartSender cartSender) {
        this.mapeo = mapper;
        this.Client = client;
        this.cartSender = cartSender;
    }



        public void create(String uuid) {
            String uuidCart = UUID.randomUUID().toString();
            ShoppingCart cart = new ShoppingCart();
            cart.setActiveStatus(true);
            cart.setUuid(uuidCart);
            cart.setUser_uuid(uuid);
            mapeo.create(cart);
        }


    public int  searchIdCart(String uuidCart) {
        return this.mapeo.extracUUID(uuidCart);
    }
    public List<Item> getListCart(String uuidCarrito) {
        int idCart = searchIdCart(uuidCarrito);
        final List<String> ItemsUUID = this.mapeo.searchUUIDItem(idCart);

        final List<Item>ColItem = new ArrayList<>();

        for(String uuid:ItemsUUID) {
            final Item i = this.Client.fetchItem(uuid);
            ColItem.add(i);
        }
        return ColItem;
    }


  /*  public void addItem(String uuidUser, Item item)
    {
        ShoppingCart cart = new ShoppingCart();
        cart.setActiveStatus(true);
        cart.setUuid(uuidCart);
        cart.setUser_uuid(uuid);
        mapeo.create(cart);
    }*/

    public void addItemCart(String uuidCart, Item item) {
        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
        .action(Action.ADD_ITEM_TO_CART)
                .item(item)
                .shoppingCart_uuid(uuidCart)
                .build();
        this.cartSender.sendMessageAdd(message);

    }

    public void messageDeleteCart(String uuidItem) {
        Item itemUuid = new Item();
        itemUuid.setUuid(uuidItem);
        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
                .action(Action.REMOVE_ITEM_FROM_CART)
                .item(itemUuid)
                .build();
        this.cartSender.sendMessageAdd(message);
    }
}
/*(@Param("user") final String userUUID,
                             @Param("item") final Item item);*/





/*
*public void AddCart(String uuid)
    {
        String uuidCart = java.util.UUID.randomUUID().toString();
        ShoppingCart cart = new ShoppingCart();
        cart.setActiveStatus(true);
        cart.setUuid(uuidCart);
        cart.setUser_uuid(uuid);


    }
* */

