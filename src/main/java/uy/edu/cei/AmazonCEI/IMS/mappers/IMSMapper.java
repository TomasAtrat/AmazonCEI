package uy.edu.cei.AmazonCEI.IMS.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import uy.edu.cei.AmazonCEI.common.models.Item;

import java.util.List;
import java.util.UUID;

@Mapper
public interface IMSMapper {
    @Select("SELECT id, uuid, stock, name, cost FROM item")
    List<Item> getElements();

    @Update("update item set stock= #{item.stock} where uuid= #{item.uuid}")
    void update(@Param("item") final Item item);

    @Select("SELECT id, uuid, stock, name, cost FROM item where uuid= #{uuid}")
    Item getElementByUUID(@Param("uuid") final String uuid);
}
