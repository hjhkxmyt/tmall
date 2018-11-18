package com.baiyun.gcd.tmall.controller;


import com.baiyun.gcd.tmall.beans.User;
import com.baiyun.gcd.tmall.service.CartService;
import com.baiyun.gcd.tmall.utils.CartVo;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    public ServiceResponse checkLogin(HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null){
            return ServiceResponse.createByErrorMessage("未登录，请登录！");
        }
        return ServiceResponse.createBySuccess();
    }



    @RequestMapping("cart/list")
    public String list(HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "login";
        }
        User user = (User) session.getAttribute("user");
        ServiceResponse<List<CartVo>> response = cartService.list(user.getId());
        session.setAttribute("cartVoList",response.getData());
        return "cart";
    }


    @RequestMapping("cart/checked")
    public String checked(@RequestParam("productId") Integer productId,@RequestParam("checked") Integer checked,HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "login";
        }
        User user = (User) session.getAttribute("user");
        ServiceResponse response = cartService.setChecked(user.getId(), productId, checked);
        return "cart";
    }

    @RequestMapping("cart/quantity")
    public String quantity(@RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity,HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "login";
        }
        User user = (User) session.getAttribute("user");
        ServiceResponse response = cartService.quantity(user.getId(), productId, quantity);
        return "cart";
    }

    @RequestMapping("addProduct")
    @ResponseBody
    public ServiceResponse addProduct(@RequestParam("productId") Integer productId,HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return checkLogin;
        }
        ServiceResponse response = cartService.addProduct(((User)(session.getAttribute("user"))).getId(), productId);
        return response;
    }

}
