package com.baiyun.gcd.tmall.service;

import com.baiyun.gcd.tmall.beans.Product;
import com.baiyun.gcd.tmall.dao.ProductMapper;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public ServiceResponse SaveOrUpdateProduct(Product product) {
        if (product != null) {

            if (product.getId() != null) {
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount > 0) {
                    return ServiceResponse.createBySuccess("修改成功");
                } else {
                    return ServiceResponse.createByErrorMessage("修改失败");
                }
            } else {
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServiceResponse.createBySuccess("添加成功");
                } else {
                    return ServiceResponse.createByErrorMessage("添加失败");
                }
            }

        }

        return ServiceResponse.createByErrorMessage("未知错误");
    }


    public ServiceResponse getProductList(){
        List<Product> productList = productMapper.selectList();
        return ServiceResponse.createBySuccess(productList);
    }

    public ServiceResponse<Product> getProductDetail(Integer productId){

        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            return ServiceResponse.createByErrorMessage("产品已删除或下架");
        }
        return ServiceResponse.createBySuccess(product);
    }

    public ServiceResponse setStatus(Integer productId,Integer status){
        if (productId == null || status == null){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateStatusByPrimaryKey(product);
        if (rowCount > 0){
            return ServiceResponse.createBySuccess("已修改");
        }
        return ServiceResponse.createByErrorMessage("修改失败");
    }

    public ServiceResponse searchProduct(String productName){
        if (StringUtils.isNoneBlank(productName)) {
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByName(productName);
        if (productList.size()>0){
            return ServiceResponse.createBySuccess(productList);
        }else {
            return ServiceResponse.createByErrorMessage("无结果");
        }
    }

}
