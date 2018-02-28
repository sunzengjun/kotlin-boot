package com.szj.hello.web;


import com.szj.hello.dao.domain.Image;
import com.szj.hello.exception.ServiceException;
import com.szj.hello.service.BookService;
import com.szj.hello.service.ImageService;
import com.szj.hello.service.dto.BookDto;
import com.szj.hello.utils.entity.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by Sun on 2017/3/31.
 */
@Controller
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
    @ResponseBody
    public String index(){
        return "Hello World !" + this.port + " : " + this.name;
    }

    @RequestMapping( value = "result" )
    @ResponseBody
    public ApiResponse getResult() throws Exception {
        ApiResponse response = new ApiResponse();
        response.setData("test");
        throw new Exception("突发异常");
        //return response;
    }

    @RequestMapping( value = "image" )
    @ResponseBody
    public Image image(@RequestParam(value = "image_id" ,defaultValue = "1") long imageId){
        Long id = new Long(imageId);
        Image img = imageService.getById(id);
        return img;
    }

    @RequestMapping("/hi")
    public String hi(Map<String, Object> model) {
        model.put("time", "02.30");
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老许");
        return "welcome";
    }

    @RequestMapping("/hi2")
    public ModelAndView hi2(Map<String, Object> model) {
        model.put("time", "02.30");
        model.put("message", "这是测试的内容。。。");
        model.put("toUserName", "张三");
        model.put("fromUserName", "老孙");
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject(model);
        return modelAndView;
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
