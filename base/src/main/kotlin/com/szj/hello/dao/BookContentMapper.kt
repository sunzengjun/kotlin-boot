package com.szj.hello.dao

import com.szj.hello.dao.domain.BookContent
import org.apache.ibatis.annotations.Param

/**
 * 书目内容mapper
 * Created by sunzengjun on 2017/10/18.
 */
interface BookContentMapper {

    /**
     * 根据contentId获取书目内容
     * @param contentId
     * @return BookContent
     */
    fun getBookContentById(@Param("contentId") contentId: Int) : BookContent

    /**
     * 根据contentId获取书目内容
     * @param contentId
     * @return BookContent
     */
    fun getBookContentByIds(@Param("contentIds") contentIds: List<Int>) : MutableList<BookContent>

    /**
     * 保存书目内容
     * @param content
     * @param contentType
     * @return Int
     */
    fun updateBookContent(@Param("contentId") contentId:Int?,@Param("content") content:String?,@Param("contentType") contentType:Int?):Int

    /**
     * 添加书目内容
     * @param content
     * @param contentType
     * @return Int
     */
    fun insertBookContent(@Param("content") content:String,@Param("contentType") contentType:Int):Int

    /**
     * 删除书目内容
     * @param contentId
     * @return BookContent
     */
    fun deleteBookContentById(@Param("contentId") contentId: Int) : Int
}