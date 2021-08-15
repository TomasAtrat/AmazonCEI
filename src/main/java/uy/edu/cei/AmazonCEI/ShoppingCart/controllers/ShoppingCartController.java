package uy.edu.cei.AmazonCEI.ShoppingCart.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.ShoppingCart.services.ShoppingCartServices;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;

import java.util.List;
@RestController
@RequestMapping("/ShoppingCart")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServices shoppingCartServices;

    @GetMapping("/{uuid_user}")
    public List<Item>listShowItem(@PathVariable("uuid_user") String uuidCarrito) {
       return this.shoppingCartServices.getListCart(uuidCarrito);
    }

    @PostMapping("/{uuid_item}")
    public void createMesaggeAddItemCart(@PathVariable("uuid_item") String uuid_item,
                                         @RequestBody final ItemInShoppingCart item)
        {
            this.shoppingCartServices.addItemCart(uuid_item, item);
        }

    @DeleteMapping("/{uuidUser}")
    public void deleteCartMessage(@PathVariable("uuidUser") final String uuidUser,
                                  @RequestBody final Item item)
    {
        this.shoppingCartServices.messageDeleteCart(uuidUser, item);
    }
}



