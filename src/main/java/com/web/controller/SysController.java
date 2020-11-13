package com.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.web.entity.MenuBean;
import com.web.service.SysService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("/sys")
@Controller
public class SysController {

    @Resource
    private SysService sysService;

    @RequestMapping("/getMenuJson")
    public String getMenuJson(Model model){

        List<MenuBean> list = sysService.getMenuList();

        String json = JSONArray.toJSONString(list);

        model.addAttribute("json", json);

        return "left";
    }
}
