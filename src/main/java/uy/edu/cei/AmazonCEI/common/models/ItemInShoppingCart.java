package uy.edu.cei.AmazonCEI.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ItemInShoppingCart {
    private String uuid_shoppingCart;
    private String uuid_item;
    private Integer amount;
}
