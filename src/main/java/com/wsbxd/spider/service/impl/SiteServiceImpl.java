package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.mapper.SiteMapper;
import com.wsbxd.spider.service.ISiteService;
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
public class SiteServiceImpl implements ISiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public List<Site> findAll() {
        return siteMapper.selectAll();
    }

}
