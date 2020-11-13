package com.web.service.impl;

import com.web.entity.MenuBean;
import com.web.mapper.SysMapper;
import com.web.service.SysService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysServiceImpl implements SysService {
    @Resource
    private SysMapper sysMapper;


    public List<MenuBean> getMenuList() {
        return sysMapper.getMenuList();
    }
}
