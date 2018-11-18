package com.baiyun.gcd.tmall.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors
public class Cart {

    private Integer id;
    private Integer userId;
    private Integer productId;

    private Integer quantity;
    private Integer checked;

}
