package uy.edu.cei.AmazonCEI.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Action {
    ADD_ITEM_TO_CART("add_item_to_cart"),
    REMOVE_ITEM_FROM_CART("remove_item_from_cart"),
    CLOSE_CART("close-cart");
    private String value;
}
