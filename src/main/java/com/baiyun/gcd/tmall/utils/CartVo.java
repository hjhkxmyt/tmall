package com.baiyun.gcd.tmall.utils;

import com.baiyun.gcd.tmall.beans.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {

    private Product product;
    private Integer count;
    private Integer checked;
    private BigDecimal totalPrice;
}
