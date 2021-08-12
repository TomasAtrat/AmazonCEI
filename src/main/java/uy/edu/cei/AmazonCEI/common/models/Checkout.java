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
public class Checkout {
    private Long id;
    private String uuid;
    private String shopping_cart_uuid;
    private Float total_cost;
}
