package com.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 城市表
 */

@Data
public class CityBean implements Serializable {
    private Long id;
    private Long pid;
    private String cname;
}
