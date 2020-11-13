package com.web.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 选项表
 */

@Data
public class ExamOption implements Serializable {

    private Long    optionid;   //选项id
    private String  oname;      //选项内容
    private Integer istrue = 0;     //选项是否正确

    private Long    examid;     //考题id

}


