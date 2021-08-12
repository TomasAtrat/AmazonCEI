package uy.edu.cei.AmazonCEI.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CheckoutAction {
    SEND_NOTIFICATION("send-notification"),
    UPDATE_STOCK("update-stock");
    private String value;
}
