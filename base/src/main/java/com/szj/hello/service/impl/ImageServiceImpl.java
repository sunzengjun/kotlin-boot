package com.szj.hello.service.impl;

import com.szj.hello.dao.ImageMapper;
import com.szj.hello.dao.domain.Image;
import com.szj.hello.service.ImageService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by sunzengjun on 2017/10/26.
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageMapper imageMapper;
    @Override
    public Image getById(Long id) {
        return imageMapper.selectById(id);
    }
}
