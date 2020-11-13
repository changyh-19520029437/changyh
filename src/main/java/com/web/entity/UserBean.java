package com.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBean implements Serializable {

    private Long    id;         //用户id
    private String  username;   //账号
    private String  password;   //密码
    private Integer age;        //年龄

}
