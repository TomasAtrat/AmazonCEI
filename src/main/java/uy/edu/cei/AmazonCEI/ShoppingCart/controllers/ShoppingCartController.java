package uy.edu.cei.AmazonCEI.ShoppingCart.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import uy.edu.cei.AmazonCEI.ShoppingCart.clients.IMSClient;
import uy.edu.cei.AmazonCEI.ShoppingCart.mappers.ShoppingCartMapper;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartMapper ShoppingCartMapper;
    private final IMSClient Client;

    @GetMapping("/{idCarrito}")
    public List<Item>listShowItem() {
        final List<UUID> ItemsUUID = this.ShoppingCartMapper.extracUUID();

        final List<Item>ColItem = new ArrayList<>();

        for(UUID uuid:ItemsUUID) {
            final Item i = this.Client.fetchItem(uuid);
            ColItem.add(i);
        }
        return ColItem;
        }


    }



