package uy.edu.cei.AmazonCEI.Web.clients;

import org.springframework.web.client.RestTemplate;
import uy.edu.cei.AmazonCEI.common.models.Item;

public class IMSClientWeb {

    private final RestTemplate restTemplate;

    public IMSClientWeb() {
        this.restTemplate = new RestTemplate();
    }

    public Item fetchItem(final String uuid) {
        final String url = "http://localhost:8080/ims/" + uuid;
        return restTemplate.getForObject(url, Item.class);

    }

}
