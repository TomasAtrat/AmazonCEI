package uy.edu.cei.AmazonCEI.IMS.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ims")
@AllArgsConstructor
public class IMSController {

/*
    @Autowired
    public IMSController(final IMSService imsService) {
        this.imsService= imsService;
    }
*/

    private final IMSService imsService;

    @GetMapping("/")
    public List<Item> getElements(){
        return this.imsService.getElements();
    }

    @GetMapping("/{uuid}")
    public Item getElementById(@PathVariable("uuid") final UUID item_uuid){
        return this.imsService.getElementByUUID(item_uuid);
    }

    @PutMapping("/{uuid}")
    public void update(
            @PathVariable("uuid") final UUID item_uuid,
            @RequestBody final ItemInShoppingCart itemInShoppingCart){
        this.imsService.update(item_uuid, itemInShoppingCart);
    }
}
