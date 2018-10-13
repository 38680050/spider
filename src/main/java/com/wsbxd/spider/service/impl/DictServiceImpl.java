package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.mapper.DictMapper;
import com.wsbxd.spider.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 字典impl
 *
 * @author chenhaoxuan
 * @date 2018/10/13 19:44
 */
@Service
public class DictServiceImpl implements IDictService {

    private final DictMapper dictMapper;

    @Autowired
    public DictServiceImpl(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }
}
