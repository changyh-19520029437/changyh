package com.web.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 */

@Data
public class StudentBean implements Serializable {
    private Long sid;
    private String sname;
    private Integer age;
    private Date birthday;
    private String address;
    private GradeBean gb = new GradeBean();
}
