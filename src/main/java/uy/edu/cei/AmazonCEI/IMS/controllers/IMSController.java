package uy.edu.cei.AmazonCEI.IMS.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import java.util.List;

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
    public Item getElementById(@PathVariable("uuid") final String item_uuid){
        return this.imsService.getElementByUUID(item_uuid);
    }

    @PutMapping("/{uuid}")
    public void update(
            @PathVariable("uuid") final String item_uuid,
            @RequestBody final ItemInShoppingCart itemInShoppingCart){
        this.imsService.update(item_uuid, itemInShoppingCart);
    }
}
