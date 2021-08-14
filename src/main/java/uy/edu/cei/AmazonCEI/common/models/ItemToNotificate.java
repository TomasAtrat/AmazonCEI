package uy.edu.cei.AmazonCEI.common.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemToNotificate {
    private String item_uuid;
    private String name;
    private Float cost;
    private Integer amount;
}
