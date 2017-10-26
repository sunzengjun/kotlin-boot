package com.szj.hello.utils

import com.szj.hello.exception.ExceptionPrefix


/**
 * book 异常枚举类
 * Created by sunzengjun on 2017/10/19.
 */
enum class BookExceptionEnum (val errorCode: String,val errorMsg: String,val chineseMessage: String){

    BOOK_NOT_EXIST(ExceptionPrefix.BOOK_EXCEPTION_CODE_PREFIX + "001", ExceptionPrefix.BOOK_EXCEPTION_MSG_PREFIX + "BOOK_NOT_EXIST", "对应书目不存在!"),
    BOOK_PARAM_ERROR(ExceptionPrefix.BOOK_EXCEPTION_CODE_PREFIX + "002", ExceptionPrefix.BOOK_EXCEPTION_MSG_PREFIX + "BOOK_PARAM_ERROR", "书目参数错误!")




}