package uy.edu.cei.AmazonCEI.Notification.services;

import org.springframework.stereotype.Service;
import uy.edu.cei.AmazonCEI.common.models.ItemToNotificate;
import uy.edu.cei.AmazonCEI.common.models.Notification;

import java.util.UUID;

@Service
public class NotificationService {
    public void send(Notification notification) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("ID de la notificación: " + UUID.randomUUID() + '\n' + "TUS PRODUCTOS");
        System.out.println("ID Item                             | Nombre      | Precio unitario | Cantidad|");
        System.out.println("-------------------------------------------------------------------------");
        for (ItemToNotificate item:notification.getColItems()) {
            System.out.println(item.getItem_uuid() + '|' + item.getName() + "  |" + item.getCost() + "            |" +item.getAmount());
            System.out.println("-------------------------------------------------------------------------");
        }
        System.out.println("Precio final: $" + notification.getTotal_cost());
    }
}
