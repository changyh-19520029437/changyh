package com.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 班级表
 */


@Data
public class GradeBean implements Serializable {

    private Long    gid;    //班级id
    private String  gname;  //班级名称

}
