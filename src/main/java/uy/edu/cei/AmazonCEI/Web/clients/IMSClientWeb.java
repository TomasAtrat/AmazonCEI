package uy.edu.cei.AmazonCEI.Web.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemWrapper;

import java.util.List;
@Component
public class IMSClientWeb {

    private final RestTemplate restTemplate;

    public IMSClientWeb() {
        this.restTemplate = new RestTemplate();
    }

    public List<Item> getElements() {
        final String url= "http://localhost:8080/ims/";
        ItemWrapper wrapper= restTemplate.getForObject(url, ItemWrapper.class);
        return wrapper.getColItems();
    }
}
