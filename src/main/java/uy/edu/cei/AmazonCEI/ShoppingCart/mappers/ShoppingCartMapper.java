package uy.edu.cei.AmazonCEI.ShoppingCart.mappers;

import org.apache.ibatis.annotations.*;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;
import java.util.UUID;
@Mapper
public interface ShoppingCartMapper {


   @Select("  SELECT  id FROM shoppingCart where uuid = {uuidCarrito}")
   int extracUUID(@Param("uuidCarrito") final String uuidCarrito);

    @Select("SELECT  uuid_item FROM item_in_shoppingCart where id_shoppingCart = {idCart}")
    List<String> searchUUIDItem(@Param("idCart") final int idCart);


   @Insert("Insert into shoppingCart values(#{user},#{item.uuid},1)")
    void addItemToCart(@Param("user") final String userUUID, @Param("item") final Item item);


   @Insert("INSERT INTO shoppingCart values (#{newCar.uuid},#{newCar.ActiveStatus}, #{newCar.user_uuid})")
    void create(@Param("newCar") final ShoppingCart cart);

    @Delete("DELETE FROM item_in_shoppingCart where uuid_item = {userUUID}}")
    void deleteItem(@Param("userUUID") final String userUUID);



    // @Update()h bjtf
   // public void close(UUID)

   //@Delete()
   //public void removeFromCart(UUID,item)
}
