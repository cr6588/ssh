package com.cr.dubbo.service.mvc;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cr.dubbo.service.dubbo.DubboSer;

@Service
public class DubboSerMVCImpl {

    @Reference(version="1.0.0")
    DubboSer dubboSer;

    public int add() {
        return dubboSer.add();
    }

}
