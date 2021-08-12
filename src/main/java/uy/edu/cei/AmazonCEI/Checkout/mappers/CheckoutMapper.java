package uy.edu.cei.AmazonCEI.Checkout.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;
import uy.edu.cei.AmazonCEI.common.models.Checkout;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.List;

@Mapper
public interface CheckoutMapper {

    @Insert("insert into checkout (uuid, shoppingCart_uuid, total_cost) values (#{chout.uuid}, #{chout.shopping_cart_uuid}, #{total_cost})")
    void add(@Param("chout") final Checkout checkout);
}
