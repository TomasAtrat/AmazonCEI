package uy.edu.cei.AmazonCEI.IMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.IMS.mappers.IMSMapper;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import java.util.List;

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

    public void update(String item_uuid, ItemInShoppingCart itemInShoppingCart) {
        final Item currentItem= this.imsMapper.getElementByUUID(item_uuid);
        if(currentItem.getUuid() != null && itemInShoppingCart.getAmount()!=null)
            currentItem.setStock(currentItem.getStock()-itemInShoppingCart.getAmount());
         this.imsMapper.update(currentItem);
    }

    public Item getElementByUUID(String item_uuid) {
        return this.imsMapper.getElementByUUID(item_uuid);
    }

    public void update(ItemInShoppingCart item) {
        final Item currentItem= this.imsMapper.getElementByUUID(item.getUuid_item());
        if(currentItem.getUuid() != null && item.getAmount()!=null)
            currentItem.setStock(currentItem.getStock()-item.getAmount());
        this.imsMapper.update(currentItem);
    }
}
