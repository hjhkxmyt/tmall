package com.baiyun.gcd.tmall.controller;

import com.baiyun.gcd.tmall.beans.User;
import com.baiyun.gcd.tmall.dao.UserMapper;
import com.baiyun.gcd.tmall.service.UserService;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("managerLogin")
    public String managerLogin(String username,String password, HttpSession session){
        User user = userService.login(username, password);
        if (user == null){
            session.setAttribute("response",ServiceResponse.createByErrorMessage("帐号与密码不匹配"));
            return "managerLogin";
        }
        session.setAttribute("user",user);
        session.setAttribute("response",ServiceResponse.createBySuccess(user));
        return "redirect:/manager";
    }


    @PostMapping("login")
    public String login(String username,String password, HttpSession session){
        User user = userService.login(username, password);
        if (user == null){
            session.setAttribute("response",ServiceResponse.createByErrorMessage("帐号与密码不匹配"));
            return "login";
        }
        session.setAttribute("user",user);
        session.setAttribute("response",ServiceResponse.createBySuccess(user));
        return "redirect:/";
    }

    @PostMapping("register")
    @ResponseBody
    public ServiceResponse register(User user){
        ServiceResponse register = userService.register(user);
        if (!register.isSuccess()){
            return ServiceResponse.createByErrorMessage("注册失败");
        }
        return register;
    }

    @GetMapping("get_user_info")
    @ResponseBody
    public ServiceResponse get_user_info(HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null){
            return ServiceResponse.createByErrorMessage("未登录,请先登录");
        }
        if (user instanceof User){
            ((User) user).setPassword("");
        }
        return ServiceResponse.createBySuccess(user);
    }

    @GetMapping("logout")
    public String logout(HttpSession session){

        Object user = session.getAttribute("user");
        if (user != null){
            session.removeAttribute("user");
        }
        return "index";
    }

    @GetMapping("managerLogout")
    public String managerLogout(HttpSession session){

        Object user = session.getAttribute("user");
        if (user != null){
            session.removeAttribute("user");
        }
        return "manager";
    }


}
