package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.domain.po.NovelSelector;
import com.wsbxd.spider.mapper.NovelMapper;
import com.wsbxd.spider.service.INovelSelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 16:56
 */
@Service
public class NovelSelectorServiceImpl implements INovelSelectorService {

    @Autowired
    private NovelMapper novelMapper;

    @Override
    public List<NovelSelector> findAll() {
        return novelMapper.selectAll();
    }

}
