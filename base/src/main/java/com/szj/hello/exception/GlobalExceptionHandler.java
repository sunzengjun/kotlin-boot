package com.szj.hello.exception;

import com.szj.hello.utils.constants.CommonConstants;
import com.szj.hello.utils.entity.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunzengjun on 2018/2/28.
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        StringBuilder requestInfo = new StringBuilder();
        try {
            requestInfo.append(" uri:");
            requestInfo.append(request.getRequestURI());

            requestInfo.append(" requestInfo: { ");
            Map map = request.getParameterMap();
            for (Object key : map.keySet().toArray()) {
                requestInfo.append(key.toString());
                requestInfo.append(":");
                requestInfo.append(request.getParameter(key.toString()));
                requestInfo.append(",");
            }

        } catch (Exception e) {
            logger.error("get requestInfo error ", ex);
        }
        requestInfo.append("}");
        logger.error("ExceptionHandler " + requestInfo.toString(), ex);
        ApiResponse result = new ApiResponse();
        result.setCode(CommonConstants.SERVICE_ERROR_CODE);
        result.setMsg("系统异常");
        ModelAndView modelAndView = new ModelAndView("errorApi");
        response.setContentType( "application/json;charset=UTF-8");
        modelAndView.addObject("message",result.toString());
        return modelAndView;
    }
}
