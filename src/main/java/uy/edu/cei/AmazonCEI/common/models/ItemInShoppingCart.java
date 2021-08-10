package uy.edu.cei.AmazonCEI.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ItemInShoppingCart {
    private Long idShoppingCart;
    private UUID item_uuid;
    private Integer cantidad;
}
