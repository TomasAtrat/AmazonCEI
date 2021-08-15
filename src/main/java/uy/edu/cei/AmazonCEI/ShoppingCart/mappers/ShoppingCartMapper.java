package uy.edu.cei.AmazonCEI.ShoppingCart.mappers;

import org.apache.ibatis.annotations.*;
import uy.edu.cei.AmazonCEI.common.models.Item;
import uy.edu.cei.AmazonCEI.common.models.ItemInShoppingCart;
import uy.edu.cei.AmazonCEI.common.models.ShoppingCart;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Select("SELECT id FROM shoppingCart where uuid = #{uuidCarrito}")
    Integer extracUUID(@Param("uuidCarrito") final String uuidCarrito);

    @Select("SELECT id, uuid, uuid_user, activeStatus FROM shoppingCart where activeStatus= 1 and uuid_user = #{uuid_user}")
    ShoppingCart getCartByUserUUID(@Param("uuid_user") final String uuid_user);

    @Select("SELECT uuid_item FROM item_in_shoppingCart where uuid_shoppingCart = #{uuidCart}")
    List<String> searchUUIDItem(@Param("uuidCart") final String uuidCart);

    @Select("SELECT uuid_shoppingCart, uuid_item, amount FROM item_in_shoppingCart WHERE uuid_shoppingCart= #{cart_uuid}")
    List<ItemInShoppingCart> getItems(@Param("cart_uuid") final String cart_uuid);

    @Select("SELECT uuid from ShoppingCart where uuid_user= #{user_uuid}")
    String getUUIDCartByUser(@Param("user_uuid") final String user_uuid);

    @Insert("Insert into item_in_shoppingCart(uuid_shoppingCart, uuid_item, amount) values(#{uuid_shoppingCart},#{item.uuid_item}, #{item.amount})")
    void addItemToCart(@Param("uuid_shoppingCart") final String uuid_shoppingCart,
                       @Param("item") final ItemInShoppingCart item);

    @Insert("INSERT INTO shoppingCart(uuid, activeStatus, uuid_user) values (#{newCar.uuid},#{newCar.ActiveStatus}, #{newCar.user_uuid})")
    void create(@Param("newCar") final ShoppingCart cart);

    @Delete("DELETE FROM item_in_shoppingCart where uuid_item = #{item.uuid} and uuid_shoppingCart= #{cartUUID}")
    void deleteItem(@Param("cartUUID") final String cartUUID,
                    @Param("item") final Item item);

    @Update("UPDATE item_in_shoppingCart set amount=#{it.amount} WHERE uuid_shoppingCart= #{shoppingCart_uuid} and uuid_item= #{it.uuid_item}")
    void updateItem(@Param("shoppingCart_uuid") final String shoppingCart_uuid,
                    @Param("it") final ItemInShoppingCart it);

}
