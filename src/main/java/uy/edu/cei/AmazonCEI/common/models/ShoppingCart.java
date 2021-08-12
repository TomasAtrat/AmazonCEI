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
    private String uuid;
    private boolean ActiveStatus;
    private String user_uuid;
}
