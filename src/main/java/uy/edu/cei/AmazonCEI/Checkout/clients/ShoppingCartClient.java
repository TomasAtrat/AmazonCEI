package uy.edu.cei.AmazonCEI.Checkout.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.ItemsInCartWrapper;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;

@Component
public class ShoppingCartClient {
    private final RestTemplate restTemplate;

    public ShoppingCartClient() {
        this.restTemplate = new RestTemplate();
    }

    public ShoppingCart getCart(final String uuid_user) {
        final String url = "http://localhost:8080/ShoppingCart/foreign1/" + uuid_user;
        ShoppingCart shoppingCart = restTemplate.getForObject(url, ShoppingCart.class);
        return shoppingCart;
    }

    public List<ItemInShoppingCart> getItemsInCart(String uuid_cart){
        final String url = "http://localhost:8080/ShoppingCart/foreign2/" + uuid_cart;
        ItemsInCartWrapper wrapper = restTemplate.getForObject(url, ItemsInCartWrapper.class);
        return wrapper.getItemsInCart();
    }
}
