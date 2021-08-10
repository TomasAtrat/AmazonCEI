package uy.edu.cei.AmazonCEI.common.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartMessage {
    private Action action;
    private UUID userUUID;
    private Item item;
}
