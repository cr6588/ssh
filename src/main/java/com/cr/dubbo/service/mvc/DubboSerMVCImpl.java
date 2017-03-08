package com.cr.dubbo.service.mvc;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cr.dubbo.service.dubbo.DubboService;

//@Service
public class DubboSerMVCImpl {

//    @Reference(version="1.0.0")
    DubboService dubboSer;

    public int add() {
        return dubboSer.add();
    }

}
