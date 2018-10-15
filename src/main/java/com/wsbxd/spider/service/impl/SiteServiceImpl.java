package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.domain.po.Site;
import com.wsbxd.spider.domain.po.Type;
import com.wsbxd.spider.factory.SiteSpiderFactory;
import com.wsbxd.spider.interfaces.ISiteSpider;
import com.wsbxd.spider.mapper.SiteMapper;
import com.wsbxd.spider.mapper.TypeMapper;
import com.wsbxd.spider.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * description:
 *
 * @author chenhaoxuan
 * @date 2018/9/15 15:49
 */
@Service
public class SiteServiceImpl implements ISiteService {

    private final SiteMapper siteMapper;
    
    private final TypeMapper typeMapper;

    @Autowired
    public SiteServiceImpl(SiteMapper siteMapper, TypeMapper typeMapper) {
        this.siteMapper = siteMapper;
        this.typeMapper = typeMapper;
    }

    @Override
    public List<Site> findAll() {
        return siteMapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertTypes(String siteUrl) {
        try {
            typeMapper.deleteBySiteUrl(siteUrl);
            ISiteSpider siteService = SiteSpiderFactory.getSiteService(siteUrl);
            List<Type> types = siteService.crawlTypes(siteUrl);
            int successNum = typeMapper.insertList(types);
            return successNum == types.size();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

}
