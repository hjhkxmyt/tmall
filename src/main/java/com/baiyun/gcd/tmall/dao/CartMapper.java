package com.baiyun.gcd.tmall.dao;

import com.baiyun.gcd.tmall.beans.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select id,user_id userId,product_id productId,quantity,checked from cart where user_id = #{userId}")
    public List<Cart> getCartList(Integer userId);

    @Insert("insert into cart(user_id,product_id,quantity,checked) values(#{userId},#{productId},1,0)")
    public int addProduct(@Param("userId") Integer userId,@Param("productId") Integer productId);

    @Update("update cart set checked = #{checked} where user_id = #{userId} and product_id = #{productId}")
    public int setChecked(@Param("userId") Integer userId,@Param("productId") Integer productId,@Param("checked") Integer checked);

    @Update("update cart set quantity = #{quantity} where user_id = #{userId} and product_id = #{productId}")
    public int setQuantity(@Param("userId") Integer userId,@Param("productId") Integer productId,@Param("quantity") Integer quantity);
}
