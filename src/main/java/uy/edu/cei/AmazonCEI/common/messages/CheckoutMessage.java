package uy.edu.cei.AmazonCEI.common.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.Notification;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutMessage {
    private CheckoutAction action;
    private String checkout_uuid;
    private ShoppingCart shoppingCart;
    private String shoppingCartUUID;
    private ItemInShoppingCart itemInShoppingCart;
    private Notification notification;
}
