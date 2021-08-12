package uy.edu.cei.AmazonCEI.Checkout.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.UUID;

@Component
public class IMSClientCH {
    private final RestTemplate restTemplate;

    public IMSClientCH() {
        this.restTemplate = new RestTemplate();
    }

    public Item fetchItem(final String uuid) {
        final String url = "http://localhost:8080/ims/" + uuid;
        Item item = restTemplate.getForObject(url, Item.class);
        return item;
    }
}
