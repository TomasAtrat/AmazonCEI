package uy.edu.cei.AmazonCEI.ShoppingCart.mappers;

import org.apache.ibatis.annotations.*;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;
import java.util.UUID;
@Mapper
public interface ShoppingCartMapper {


   @Select("Select Item_id from shopping_cart")
   List<String> extracUUID();

   @Insert("Insert into shopping_cart values(#{user},#{item.uuid},1)")
   public void addItemToCart(@Param("user") final String userUUID,
                             @Param("item") final Item item);


   @Insert("INSERT INTO shopping_cart values (#{newCar.uuid},#{newCar.ActiveStatus}," +
                                             "#{newCar.user_uuid})")
   void create(ShoppingCart newCar);


   // @Update()
   // public void close(UUID)

   //@Delete()
   //public void removeFromCart(UUID,item)
}
