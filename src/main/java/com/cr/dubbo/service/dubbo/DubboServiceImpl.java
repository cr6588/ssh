package com.cr.dubbo.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version="1.0.0")
public class DubboServiceImpl implements DubboSer {

    @Override
    public int add() {
        return 5;
    }

}
