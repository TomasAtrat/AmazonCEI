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
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServices {
    private ShoppingCartMapper mapeo;
    private final IMSClient Client;
    private final ShoppingCartSender cartSender;
    private static ShoppingCart shoppingCart;

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

    public void addItemCart(String uuidCart, ItemInShoppingCart item) {
        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
        .action(Action.ADD_ITEM_TO_CART)
                .itemInShoppingCart(item)
                .shoppingCart_uuid(uuidCart)
                .build();
        this.cartSender.sendMessageAdd(message);

    }

    public void messageDeleteCart(String uuidItem) {

        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
                .action(Action.REMOVE_ITEM_FROM_CART)
                .shoppingCart_uuid(shoppingCart.getUuid())
                .itemUUID(uuidItem)
                .build();
        this.cartSender.sendMessageAdd(message);
    }

    public void add(String shoppingCart_uuid, ItemInShoppingCart item) {
        //Primero verificar si está en el carrito --> SI --> Actualizar la cantidad
                                                    //NO --> Añadir al carrito
        List<ItemInShoppingCart> itemsInCart= this.mapeo.getItems(shoppingCart_uuid);
        if(itemsInCart.contains(item.getItem_uuid())){
            ItemInShoppingCart it= itemsInCart.stream().filter(i-> i.getItem_uuid()==item.getItem_uuid()).findFirst().orElse(null);
            it.setAmount(it.getAmount()+item.getAmount());
            this.mapeo.updateItem(shoppingCart_uuid, it);
        }else{
            this.mapeo.addItemToCart(shoppingCart_uuid, item);
        }
    }

    public void delete(String shoppingCart_uuid, String item_uuid){
        this.mapeo.deleteItem(shoppingCart_uuid, item_uuid);
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

