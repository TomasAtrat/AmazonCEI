package uy.edu.cei.AmazonCEI.ShoppingCart.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;
@Mapper
public interface ShoppingCartMapper {


   @Select("Select Item_id from shopping_cart")
   List<UUID> extracUUID();



}
