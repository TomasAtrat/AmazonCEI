package uy.edu.cei.AmazonCEI.IMS.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.IMS.services.IMSService;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ims")
@AllArgsConstructor
public class IMSController {
    private final IMSService imsService;

    @GetMapping("/")
    public List<Item> getElements(){
        return this.imsService.getElements();
    }

    @PutMapping("/{item_uuid}, {amount} ")
    public void update(
            @PathVariable("item_uuid") final UUID item_uuid,
            @PathVariable("amount") final Integer amount){
        this.imsService.update(item_uuid, amount);
    }
}
