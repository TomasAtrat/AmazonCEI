package uy.edu.cei.AmazonCEI.ShoppingCart.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.UUID;

@Component
public class IMSClient {

    private final RestTemplate restTemplate;

    public IMSClient() {
        this.restTemplate = new RestTemplate();
    }

    public Item fetchItem(final UUID uuid) {
        final String url = "http://localhost:8080/items/" + uuid;
        Item item = restTemplate.getForObject(url, Item.class);
        return item;
    }
}