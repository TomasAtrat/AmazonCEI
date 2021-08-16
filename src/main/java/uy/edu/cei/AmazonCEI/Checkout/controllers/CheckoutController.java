package uy.edu.cei.AmazonCEI.Checkout.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.cei.AmazonCEI.Checkout.services.CheckoutService;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/{uuid_user}")
    public void checkout(@PathVariable("uuid_user") final String uuid_user){
        this.checkoutService.checkout(uuid_user);
    }
}
