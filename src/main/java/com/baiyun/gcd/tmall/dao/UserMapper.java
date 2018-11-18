package com.baiyun.gcd.tmall.dao;

import com.baiyun.gcd.tmall.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    public User checkUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select count(1) from user where username = #{username}")
    public int checkUsername(String username);

    @Select("select count(1) from user where email = #{email}")
    public int checkEmail(String email);

    @Insert("insert into user(username,password,email,phone,question,answer,role) values(#{username},#{password},#{email},#{phone},#{question},#{answer},#{role})")
    public int insert(User user);

}
