package com.baiyun.gcd.tmall.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class Category {

    private Integer id;
    private Integer parentId;
    private String name;

    private boolean status;
    private Integer sortOrder;

}
