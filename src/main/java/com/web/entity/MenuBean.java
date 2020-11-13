package com.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单表
 */


@Data
public class MenuBean implements Serializable {

    private Long    mid;    //菜单id
    private String  mname;  //菜单名称
    private Long    pid;    //子菜单父id
    private String  url;    //
    private String  target; //
    private String  icon;   //

}
