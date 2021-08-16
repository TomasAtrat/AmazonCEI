package uy.edu.cei.AmazonCEI.Web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.cei.AmazonCEI.Web.services.WebServices;
import uy.edu.cei.AmazonCEI.common.models.Item;
import java.util.List;



@RestController
@RequestMapping("/Web")
@AllArgsConstructor
public class ItemController {

    private WebServices service;

    @GetMapping()
    public List<Item> getUserItem()
    {
        return this.service.getClientItem();
    }
}
