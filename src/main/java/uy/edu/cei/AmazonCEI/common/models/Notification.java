package uy.edu.cei.AmazonCEI.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    private List<ItemToNotificate> colItems;
    private String notification_uuid;
    private Float total_cost;
}
