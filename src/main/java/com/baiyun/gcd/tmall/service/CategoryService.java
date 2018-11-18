package com.baiyun.gcd.tmall.service;

import com.baiyun.gcd.tmall.beans.Category;
import com.baiyun.gcd.tmall.dao.CategoryMapper;
import com.baiyun.gcd.tmall.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public ServiceResponse get_category(){
        List<Category> categoryList = categoryMapper.get_category();
        return  ServiceResponse.createBySuccess(categoryList);
    }

    public ServiceResponse add_category(Integer parentId , String categoryName){
        //检查参数
        int result = 0;
        result = categoryMapper.checkName(categoryName);
        if (result > 0){
            return ServiceResponse.createByErrorMessage("类别已存在");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        result = categoryMapper.add_category(category);
        if (result > 0){
            return ServiceResponse.createBySuccess("添加成功");
        }
        return ServiceResponse.createBySuccessMessage("未知错误");
    }

}
