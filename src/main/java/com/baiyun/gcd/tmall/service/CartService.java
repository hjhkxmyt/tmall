package com.baiyun.gcd.tmall.service;

import com.baiyun.gcd.tmall.beans.Cart;
import com.baiyun.gcd.tmall.beans.Product;
import com.baiyun.gcd.tmall.dao.CartMapper;
import com.baiyun.gcd.tmall.dao.ProductMapper;
import com.baiyun.gcd.tmall.utils.CartVo;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    public ServiceResponse<List<CartVo>> list(Integer userId){
        List<Cart> cartList = cartMapper.getCartList(userId);
        List<CartVo> cartVoList = new ArrayList<CartVo>();
        BigDecimal sum = new BigDecimal("0");
        for (int i=0;i<cartList.size();i++){
            CartVo cartVo = new CartVo();
            Product p = productMapper.selectByPrimaryKey(cartList.get(i).getProductId());
            cartVo.setProduct(p);
            cartVo.setChecked(cartList.get(i).getChecked());
            cartVo.setCount(cartList.get(i).getQuantity());
            if (cartList.get(i).getChecked()==1){
                BigDecimal price = p.getPrice();
                BigDecimal q = new BigDecimal(String.valueOf(cartList.get(i).getQuantity()));
                BigDecimal b = price.multiply(q);
                sum = sum.add(b);
            }
            cartVoList.add(cartVo);
        }
        if (cartVoList.size()>0){
            cartVoList.get(0).setTotalPrice(sum);
        }else {
            return ServiceResponse.createByErrorMessage("未添加商品");
        }
        return ServiceResponse.createBySuccess(cartVoList);
    }


    public ServiceResponse addProduct(Integer userId,Integer productId){
        int result = cartMapper.addProduct(userId, productId);
        if (result <=0){
            return ServiceResponse.createByErrorMessage("加入购物车失败,未知错误");
        }
        return ServiceResponse.createBySuccess("添加成功");
    }

    public ServiceResponse setChecked(Integer userId,Integer productId,Integer checked){
        int result = cartMapper.setChecked(userId,productId,checked);
        if (result>0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByErrorMessage("未知错误");
    }

    public ServiceResponse quantity(Integer userId,Integer productId,Integer quantity){
        int result = cartMapper.setQuantity(userId,productId,quantity);
        if (result>0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByErrorMessage("未知错误");
    }
}

