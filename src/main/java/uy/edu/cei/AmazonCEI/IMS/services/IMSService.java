package uy.edu.cei.AmazonCEI.IMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uy.edu.cei.AmazonCEI.IMS.mappers.IMSMapper;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.List;
import java.util.UUID;

@Service
public class IMSService {
    private IMSMapper imsMapper;

    @Autowired
    public IMSService(final IMSMapper imsMapper) {
        this.imsMapper= imsMapper;
    }

    public List<Item> getElements(){
        return this.imsMapper.getElements();
    }

    public void update(UUID item_uuid, Integer amount) {
        final Item currentItem= this.imsMapper.getElementByUUID(item_uuid);
        if(currentItem.getUuid() != null)
            currentItem.setStock(currentItem.getStock()-amount);
         this.imsMapper.update(currentItem);
    }

}
