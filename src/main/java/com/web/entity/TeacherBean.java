package com.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 教师表
 */


@Data
public class TeacherBean implements Serializable {

    private Long    tid;        //教师id
    private String  tname;      //教师名称
    private String  taccount;   //用户名
    private String  telphone;   //电话
    private String  email;      //邮件地址
    private String  pwd;        //密码

}
