package com.szj.hello.dao

import com.szj.hello.dao.domain.Book
import org.apache.ibatis.annotations.Param


/**
 * 书目Mapper
 * Created by sunzengjun on 2017/10/16.
 */
interface BookMapper {

    /**
     * 根据bookId获取书目信息
     * @param bookId
     * @return List
     */
    fun getBookById(@Param("bookId") bookId: Int) : Book?

    /**
     * 插入书目信息
     * @param book
     * @return Int
     */
    fun insertBook(@Param("book") book: Book) : Int

    /**
     * 更新书目信息
     * @param book
     * @return Int
     */
    fun updateBook(@Param("book") book: Book) : Int


}