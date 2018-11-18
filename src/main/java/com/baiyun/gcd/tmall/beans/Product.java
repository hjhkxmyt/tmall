package com.baiyun.gcd.tmall.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors
public class Product {

    private Integer id;
    private Integer category_id;
    private String name;

    private String subTitle;
    private String main_image;
    private String sub_images;

    private String detail;
    private BigDecimal price;
    private Integer stock;

    private Integer status;
}
