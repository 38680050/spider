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
    public List<Site> selectSiteAll() {
        return siteMapper.selectAll();
    }

    @Override
    public List<Type> selectTypesBySiteUrl(String siteUrl) {
        return typeMapper.selectBySiteUrl(siteUrl);
    }

    @Override
    public void deleteTypeBySiteUrl(String siteUrl) {
        typeMapper.deleteTypeBySiteUrl(siteUrl);
    }

    @Override
    public boolean insertTypes(String siteUrl) {
        try {
            ISiteSpider siteService = SiteSpiderFactory.getSiteService(siteUrl);
            List<Type> types = siteService.crawlTypes(siteUrl);
            int successNum = typeMapper.insertList(types);
            return successNum == types.size();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Type selectTypeByUrl(String url) {
        return typeMapper.selectTypeByUrl(url);
    }

}
