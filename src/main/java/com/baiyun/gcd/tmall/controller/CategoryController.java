package com.baiyun.gcd.tmall.controller;

import com.baiyun.gcd.tmall.service.CategoryService;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public ServiceResponse checkLogin(HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null){
            return ServiceResponse.createByErrorMessage("未登录，请登录！");
        }
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping("manager/get_category")
    public String get_category(HttpSession session){
        session.removeAttribute("list");
        ServiceResponse response = this.checkLogin(session);
        if (!response.isSuccess()){
            return "managerLogin";
        }
        ServiceResponse categoryList = categoryService.get_category();
        session.setAttribute("categoryList",categoryList);
        return "manager";
    }

}
