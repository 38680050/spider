package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.domain.po.Dict;
import com.wsbxd.spider.mapper.DictMapper;
import com.wsbxd.spider.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean insertDict(String name, String mapName, Integer pid) {
        int successNum = dictMapper.insert(new Dict(null, name, mapName, pid));
        return successNum == 1;
    }

    @Override
    public List<Dict> selectByPid(Integer pid) {
        return dictMapper.selectByPid(pid);
    }

}
