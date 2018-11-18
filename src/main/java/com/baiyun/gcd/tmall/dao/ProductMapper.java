package com.baiyun.gcd.tmall.dao;

import com.baiyun.gcd.tmall.beans.Category;
import com.baiyun.gcd.tmall.beans.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product where id = #{productId}")
    public Product selectByPrimaryKey(Integer productId);

    @Insert("insert into product(category_id,name,subtitle,main_image,sub_images,detail,price,stock,status) values(#{category_id},#{name},#{subTitle},#{main_image},#{sub_images},#{detail},#{price},#{stock},#{status})")
    public int insert(Product product);

    @Update("update product set category_id = #{category_id},name = #{name},subtitle = #{subTitle},main_image = #{main_image},sub_images = #{sub_images},detail = #{detail},price=#{price},stock = #{stock},status=#{status} where id = #{id}")
    public int updateByPrimaryKey(Product product);

    @Update("update product set status = #{status} where id = #{id}")
    public int updateStatusByPrimaryKey(Product product);

    @Select("select * from product order by id asc")
    public List<Product> selectList();

    @Select("select * from product where name like #{productName}")
    public  List<Product> selectByName(@Param("productName") String productName);
}
