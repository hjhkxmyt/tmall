package com.baiyun.gcd.tmall.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors
public class User {

    private Integer id;
    private String username;
    private String password;

    private String email;
    private String phone;
    private String question;

    private String answer;
    private Integer role;

}
