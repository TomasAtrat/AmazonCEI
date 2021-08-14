package uy.edu.cei.AmazonCEI.ShoppingCart.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.ShoppingCart.clients.IMSClient;
import uy.edu.cei.AmazonCEI.ShoppingCart.mappers.ShoppingCartMapper;
import uy.edu.cei.AmazonCEI.ShoppingCart.services.ShoppingCartServices;
import uy.edu.cei.AmazonCEI.common.messages.Action;
import uy.edu.cei.AmazonCEI.common.messages.ShoppingCartMessage;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/ShoppingCart")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServices ShoppingCartSerrvices;

    @PostMapping("/{uuidUser}")
    public void createMensagge(@PathVariable("uuidUser") String uuidUser)
    {
        this.ShoppingCartSerrvices.create(uuidUser);
    }

    @GetMapping("/{uuidCarrito}")
    public List<Item>listShowItem(@PathVariable("uuidCarrito") String uuidCarrito) {
       return this.ShoppingCartSerrvices.getListCart(uuidCarrito);
    }

    @PostMapping("/{uuidCart}")
    public void createMesaggeAddItemCart(@PathVariable("uuidCart") String uuidCart, @RequestBody Item item)
        {
            this.ShoppingCartSerrvices.addItemCart(uuidCart,item );
        }

    @DeleteMapping("/{uuidCart}")
    public void deleteCartMessage(@PathVariable("uuidCart") String uuidItem)
    {
        this.ShoppingCartSerrvices.messageDeleteCart(uuidItem);
    }
}



