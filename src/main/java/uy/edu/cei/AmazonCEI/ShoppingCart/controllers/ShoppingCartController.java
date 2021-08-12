package uy.edu.cei.AmazonCEI.ShoppingCart.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uy.edu.cei.AmazonCEI.ShoppingCart.clients.IMSClient;
import uy.edu.cei.AmazonCEI.ShoppingCart.mappers.ShoppingCartMapper;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartMapper ShoppingCartMapper;
    private final IMSClient Client;

    @GetMapping("/{idCarrito}")
    public List<Item>listShowItem() {
        final List<String> ItemsUUID = this.ShoppingCartMapper.extracUUID();

        final List<Item>ColItem = new ArrayList<>();

        for(String uuid:ItemsUUID) {
            final Item i = this.Client.fetchItem(uuid);
            ColItem.add(i);
        }
        return ColItem;
        }

        @PostMapping("/{uuidUser}")
    public void createMensagge(@PathVariable("uuidUser") String uuidUser)
        {
            String uuid = java.util.UUID.randomUUID().toString();
            ShoppingCart newCar= new ShoppingCart().builder()
            .uuid(uuid)
            .ActiveStatus(true)
            .user_uuid(uuidUser)
                    .build();

            ShoppingCartMapper.create(newCar);
        }

    }



