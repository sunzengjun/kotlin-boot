package com.szj.hello.dao;

import com.szj.hello.dao.domain.Image;

import java.util.List;

/**
 * Created by Sun on 2017/3/31.
 */
public interface ImageMapper {

    /**
     * 单个查询
     * @param refundId    图片id
     * @return            单个图片
     */
    Image selectById(Long refundId);
}
