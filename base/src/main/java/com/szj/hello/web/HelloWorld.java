package com.szj.hello.web;


import com.szj.hello.dao.ImageMapper;
import com.szj.hello.dao.domain.Image;
import com.szj.hello.exception.ServiceException;
import com.szj.hello.service.BookService;
import com.szj.hello.service.ImageService;
import com.szj.hello.service.dto.BookDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by Sun on 2017/3/31.
 */
@RestController
@RequestMapping( value = "hello" )
public class HelloWorld {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    @Value("${value.name}")
    private String name;

    @Value("${server.port}")
    private String port;


    @Resource
    private ImageService imageService;

    @Resource
    private BookService bookService;

    @RequestMapping( value = "world" )
    public String index(){
        return "Hello World !" + this.port + " : " + this.name;
    }

    @RequestMapping( value = "image" )
    public Image image(@RequestParam(value = "image_id" ,defaultValue = "1") long imageId){
        Long id = new Long(imageId);
        Image img = imageService.getById(id);
        return img;
    }

    @RequestMapping( value = "book" )
    public BookDto book(@RequestParam(value = "book_id" ,defaultValue = "2") int bookId){
        BookDto bookInfo = null;
        try {
         bookInfo = bookService.getBookAllInfoById(bookId);
        } catch (ServiceException e) {
            LOGGER.error("请求接口错误",e);
        }
        return bookInfo;
    }
}
