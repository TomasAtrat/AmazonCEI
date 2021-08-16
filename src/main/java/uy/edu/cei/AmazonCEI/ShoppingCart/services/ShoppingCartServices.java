package uy.edu.cei.AmazonCEI.ShoppingCart.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private final ShoppingCartMapper mapeo;
    private final IMSClient Client;
    private final ShoppingCartSender cartSender;

    @Autowired
    public ShoppingCartServices(ShoppingCartMapper mapper, IMSClient client,ShoppingCartSender cartSender) {
        this.mapeo = mapper;
        this.Client = client;
        this.cartSender = cartSender;
    }

        public ShoppingCart create(String uuid) {
            String uuidCart = UUID.randomUUID().toString();
            ShoppingCart cart = new ShoppingCart();
            cart.setActiveStatus(true);
            cart.setUuid(uuidCart);
            cart.setUser_uuid(uuid);
            mapeo.create(cart);
            return mapeo.getCartByUserUUID(uuid); //Solo existe un carrito activo por usuario
        }

    public Integer searchIdCart(String uuidCart) {
        return this.mapeo.extracUUID(uuidCart);
    }

    public List<Item> getListCart(String user_uuid) {
        ShoppingCart shoppingCart= this.mapeo.getCartByUserUUID(user_uuid);

        final List<String> ItemsUUID = this.mapeo.searchUUIDItem(shoppingCart.getUuid());

        final List<Item>ColItem = new ArrayList<>();

        for(String uuid:ItemsUUID) {
            final Item i = this.Client.fetchItem(uuid);
            ColItem.add(i);
        }
        return ColItem;
    }

    public void addItemCart(String uuidUsuario, ItemInShoppingCart item) {
        ShoppingCart shoppingCart= this.mapeo.getCartByUserUUID(uuidUsuario);
        if(shoppingCart== null){
            shoppingCart= this.create(uuidUsuario);
        }
        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
        .action(Action.ADD_ITEM_TO_CART)
                .itemInShoppingCart(item)
                .shoppingCart_uuid(shoppingCart.getUuid())
                .build();
        this.cartSender.sendMessageAdd(message);
    }

    public void messageDeleteCart(String uuid_user, Item item) {
        ShoppingCart shoppingCart= this.mapeo.getCartByUserUUID(uuid_user);

        final ShoppingCartMessage message = new ShoppingCartMessage().builder()
                .action(Action.REMOVE_ITEM_FROM_CART)
                .shoppingCart_uuid(shoppingCart.getUuid())
                .item(item)
                .build();
        this.cartSender.sendMessageAdd(message);
    }

    public void add(String shoppingCart_uuid, ItemInShoppingCart item) {
        //Primero verificar si está en el carrito --> SI --> Actualizar la cantidad
                                                    //NO --> Añadir al carrito
        List<ItemInShoppingCart> itemsInCart= this.mapeo.getItems(shoppingCart_uuid);

        if(itemsInCart!=null && itemsInCart.stream().anyMatch(i-> i.getUuid_item().equals(item.getUuid_item()))){
            ItemInShoppingCart it= itemsInCart.stream().filter(i-> i.getUuid_item().equals(item.getUuid_item())).findFirst().orElse(null);
            it.setAmount(it.getAmount()+item.getAmount());
            this.mapeo.updateItem(shoppingCart_uuid, it);
        }else{
            this.mapeo.addItemToCart(shoppingCart_uuid, item);
        }
    }

    public void delete(String shoppingCart_uuid, Item item){
        this.mapeo.deleteItem(shoppingCart_uuid, item);
    }

    public ShoppingCart getElementByUserUUID(String uuid_user) {
        return this.mapeo.getCartByUserUUID(uuid_user);
    }

    public List<ItemInShoppingCart> getItemsInCart(String uuid_cart) {
        return this.mapeo.getItems(uuid_cart);
    }

    public void close(String shoppingCart_uuid) {
        this.mapeo.close(shoppingCart_uuid);
    }
}