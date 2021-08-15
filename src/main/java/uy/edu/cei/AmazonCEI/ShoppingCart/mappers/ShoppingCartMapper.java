package uy.edu.cei.AmazonCEI.ShoppingCart.mappers;

import org.apache.ibatis.annotations.*;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;
import java.util.UUID;
@Mapper
public interface ShoppingCartMapper {


   @Select("  SELECT  id FROM shoppingCart where uuid = {uuidCarrito}")
   int extracUUID(@Param("uuidCarrito") final String uuidCarrito);

    @Select("SELECT  uuid_item FROM item_in_shoppingCart where id_shoppingCart = {idCart}")
    List<String> searchUUIDItem(@Param("idCart") final int idCart);

    @Select("SELECT uuid_item, uuid_shoppingCart, amount FROM item_in_shoppingCart WHERE uuid_shoppingCart= #{cart-uuid}")
    List<ItemInShoppingCart> getItems(@Param("cart_uuid")final String cart_uuid);

   @Insert("Insert into shoppingCart(uuid_shoppingCart, uuid_item, amount) values(#{uuid_shoppingCart},#{item.uuid_item}, #{item.amount})")
    void addItemToCart(@Param("uuid_shoppingCart") final String uuid_shoppingCart,
                       @Param("item") final ItemInShoppingCart item);


   @Insert("INSERT INTO shoppingCart values (#{newCar.uuid},#{newCar.ActiveStatus}, #{newCar.user_uuid})")
    void create(@Param("newCar") final ShoppingCart cart);

    @Delete("DELETE FROM item_in_shoppingCart where uuid_item = #{itemUUID} and uuid_shoppingCart= #{cartUUID}}")
    void deleteItem(@Param("cartUUID") final String cartUUID,
                    @Param("itemUUID") final String itemUUID);

    @Update("UPDATE item_in_shoppingCart set amount=#{it.amount} WHERE uuid_shoppingCart= #{shoppingCart_uuid}")
    void updateItem(@Param("shoppingCart_uuid") final String shoppingCart_uuid,
                    @Param("it") final ItemInShoppingCart it);


    // @Update()h bjtf
   // public void close(UUID)

   //@Delete()
   //public void removeFromCart(UUID,item)
}
