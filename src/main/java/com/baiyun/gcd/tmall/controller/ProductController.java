package com.baiyun.gcd.tmall.controller;

import com.baiyun.gcd.tmall.beans.Product;
import com.baiyun.gcd.tmall.service.ProductService;
import com.baiyun.gcd.tmall.utils.FileUtil;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    public ServiceResponse checkLogin(HttpSession session){
        Object user = session.getAttribute("user");
        if (user == null){
            return ServiceResponse.createByErrorMessage("未登录，请登录！");
        }
        return ServiceResponse.createBySuccess();
    }


    @RequestMapping("manager/list")
    public String List(HttpSession session){
        if (session.getAttribute("categoryList")!=null) {
            session.removeAttribute("categoryList");
        }
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "managerLogin";
        }
        session.setAttribute("list",productService.getProductList());
        return "manager";
    }

    @RequestMapping("manager/status")
    public String setStatus(@RequestParam("productId") Integer productId,@RequestParam("status") Integer status,HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "managerLogin";
        }
        ServiceResponse response = productService.setStatus(productId, status);
        return "redirect:/manager/list";
    }

    @RequestMapping(value = {"manager/saveProduct"},method = RequestMethod.POST)
    public String saveOrUpdate(@RequestParam(value = "id",required = false) Integer id,@RequestParam("category_id") Integer category_id,@RequestParam("name") String name,@RequestParam("subTitle") String subTitle,@RequestParam("main_image") MultipartFile main_image,@RequestParam("price") BigDecimal price,@RequestParam("stock") Integer stock,@RequestParam("status") Integer status, HttpSession session){
        ServiceResponse checkLogin = checkLogin(session);
        if (!checkLogin.isSuccess()){
            return "managerLogin";
        }
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath = path + "static/img/";
        String filename = main_image.getOriginalFilename();
        try {
            FileUtil.uploadFile(main_image,filePath,filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory_id(category_id);
        product.setSubTitle(subTitle);
        product.setMain_image("img/"+main_image.getOriginalFilename());
        product.setPrice(price);
        product.setStock(stock);
        product.setStatus(status);
        ServiceResponse response = productService.SaveOrUpdateProduct(product);
        return "redirect:/manager/list";
    }

    @RequestMapping("product/list")
    public String getAll(HttpSession session){
        session.removeAttribute("categoryList");
        ServiceResponse productList = productService.getProductList();
        session.setAttribute("productList",productList);
        return "index";
    }

    @RequestMapping("product/detail")
    public String getProductDetail(Integer productId,HttpSession session){
        ServiceResponse<Product> productDetail = productService.getProductDetail(productId);
        Product product = productDetail.getData();
        session.setAttribute("product",product);
        return "product";
    }

    @RequestMapping(value = "product/search",method = RequestMethod.POST)
    public String searchProduct(@RequestParam("productName") String productName,HttpSession session){
        ServiceResponse productList = productService.searchProduct(productName);
        session.setAttribute("productList",productList);
        return "index";
    }

    @RequestMapping("/addproduct")
    public String addProduct(HttpSession session){
        session.setAttribute("product",new Product());
        return "product";
    }
}
