package uy.edu.cei.AmazonCEI.Web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.Web.clients.IMSClientWeb;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.List;

@Service
public class WebServices {

    private IMSClientWeb imsClientWeb;

    @Autowired
    public WebServices(IMSClientWeb imsClientWeb){
        this.imsClientWeb= imsClientWeb;
    }

    public List<Item> getClientItem()
    {
        return this.imsClientWeb.getElements();
    }
}
