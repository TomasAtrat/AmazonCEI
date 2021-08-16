package uy.edu.cei.AmazonCEI.ShoppingCart.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uy.edu.cei.AmazonCEI.ShoppingCart.services.ShoppingCartServices;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.ItemsInCartWrapper;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

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

    @GetMapping("/foreign1/{uuid_user}")
    public ShoppingCart getElementByUserUUID(@PathVariable("uuid_user") final String uuid_user){
        return this.shoppingCartServices.getElementByUserUUID(uuid_user);
    }

    @GetMapping("/foreign2/{uuid_cart}")
    public ItemsInCartWrapper getItemsInCart(@PathVariable("uuid_cart") final String uuid_cart){
        ItemsInCartWrapper wrapper= new ItemsInCartWrapper();
        wrapper.setItemsInCart(shoppingCartServices.getItemsInCart(uuid_cart));
        return wrapper;
    }

    @PostMapping("/{uuid_user}")
    public void createMesaggeAddItemCart(@PathVariable("uuid_user") String uuid_user,
                                         @RequestBody final ItemInShoppingCart item)
        {
            this.shoppingCartServices.addItemCart(uuid_user, item);
        }

    @DeleteMapping("/{uuidUser}")
    public void deleteCartMessage(@PathVariable("uuidUser") final String uuidUser,
                                  @RequestBody final Item item)
    {
        this.shoppingCartServices.messageDeleteCart(uuidUser, item);
    }
}



