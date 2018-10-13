package com.wsbxd.spider.service.impl;

import com.wsbxd.spider.mapper.ContentMapper;
import com.wsbxd.spider.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 小说内容impl
 *
 * @author chenhaoxuan
 * @date 2018/10/13 16:46
 */
@Service
public class ContentServiceImpl implements IContentService {

    private final ContentMapper contentMapper;

    @Autowired
    public ContentServiceImpl(ContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }
}
