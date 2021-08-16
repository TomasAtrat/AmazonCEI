package uy.edu.cei.AmazonCEI.Web.services;

import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.Web.mappers.ItemMapper;
import uy.edu.cei.AmazonCEI.common.models.Item;
import java.util.List;
public class WebServices {

    private IMSService maper;

    public List<Item> getClientItem()
    {
        return this.maper.getElements();
    }
}
