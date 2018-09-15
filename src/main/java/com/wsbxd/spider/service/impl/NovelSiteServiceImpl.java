package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.domain.po.NovelSite;
import com.wsbxd.spider.mapper.NovelSiteMapper;
import com.wsbxd.spider.service.INovelSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:49
 */
@Service
public class NovelSiteServiceImpl implements INovelSiteService {

    @Autowired
    private NovelSiteMapper novelSiteMapper;

    @Override
    public List<NovelSite> findAll() {
        return novelSiteMapper.selectAll();
    }

}
