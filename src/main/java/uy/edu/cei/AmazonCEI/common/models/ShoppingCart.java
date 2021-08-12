package uy.edu.cei.AmazonCEI.common.models;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCart {
    private Long id;
    private UUID uuid;
    private boolean ActiveStatus;
    private UUID user_uuid;
}
