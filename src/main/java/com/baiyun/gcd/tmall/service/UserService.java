package com.baiyun.gcd.tmall.service;

import com.baiyun.gcd.tmall.beans.User;
import com.baiyun.gcd.tmall.utils.MD5Util;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import com.baiyun.gcd.tmall.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {

        String md5Password = MD5Util.MD5(password);
        User user = userMapper.checkUserByUsernameAndPassword(username, md5Password);
        return user;
    }


    public ServiceResponse register(User user) {
        int resultCount = userMapper.checkUsername(user.getUsername());
        if (resultCount > 0) {
            return ServiceResponse.createByErrorMessage("用户名已存在");
        }
        resultCount = userMapper.checkEmail(user.getEmail());
        if (resultCount > 0) {
            return ServiceResponse.createByErrorMessage("email已存在");
        }
        user.setRole(1);
        user.setPassword(MD5Util.MD5(user.getPassword()));

        resultCount = userMapper.insert(user);
        if (resultCount > 0) {
            return ServiceResponse.createBySuccess("注册成功");
        }
        return ServiceResponse.createByError();
    }

    public ServiceResponse checkValid(String str,String type){
        if ("username".equals(type)){
            int resultCout = userMapper.checkUsername(str);
            if (resultCout > 0){
                return ServiceResponse.createByErrorMessage("用户名已存在");
            }else{
                return ServiceResponse.createBySuccess("校验成功");
            }
        }

        if ("email".equals(type)){
            int resultCout = userMapper.checkEmail(str);
            if (resultCout > 0){
                return ServiceResponse.createByErrorMessage("email已存在");
            }else{
                return ServiceResponse.createBySuccess("校验成功");
            }
        }
        return ServiceResponse.createByError();
    }


}
