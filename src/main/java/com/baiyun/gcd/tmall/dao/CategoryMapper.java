package com.baiyun.gcd.tmall.dao;


import com.baiyun.gcd.tmall.beans.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    public List<Category> get_category();

    @Insert("insert into category(parent_id,name,status,sort_order) values(#{parentId},#{name},1,null)")
    public int add_category(Category category);

    @Select("select count(1) from category where name = #{categoryName}")
    public int checkName(String categoryName);


}
