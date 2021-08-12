package uy.edu.cei.AmazonCEI.Checkout.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.cei.AmazonCEI.Checkout.services.CheckoutService;

import java.util.UUID;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping("/{shopping_cart_uuid}")
    public void checkout(@PathVariable("shopping_cart_uuid") final UUID shopping_cart_uuid){
        this.checkoutService.checkout(shopping_cart_uuid);
    }
}
