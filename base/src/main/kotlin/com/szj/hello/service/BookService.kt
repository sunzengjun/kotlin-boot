package com.szj.hello.service

import com.szj.hello.dao.domain.Book
import com.szj.hello.dao.domain.BookChapter
import com.szj.hello.dao.domain.BookContent
import com.szj.hello.service.dto.BookDto
import com.szj.hello.exception.ServiceException

/**
 * 书目service
 * Created by sunzengjun on 2017/10/16.
 */

interface BookService {
    /**
     * 根据bookId获取书目信息
     * @param bookId

     */
    @Throws(ServiceException::class)
    fun getBookInfoById(bookId: Int): Book

    /**
     * 根据bookId获取书目信息和章节信息
     * @param bookId

     */
    @Throws(ServiceException::class)
    fun getBookInfoAndChapterById(bookId: Int): BookDto

    /**
     * 根据bookId获取书目全部信息(基本信息、章节信息、内容)
     * @param bookId

     */
    @Throws(ServiceException::class)
    fun getBookAllInfoById(bookId: Int): BookDto

    /**
     * 根据bookId+章节id获取内容信息
     * @param bookId
     * @param chapterIds
     * @return MutableList
     */
    @Throws(ServiceException::class)
    fun getContentByChapters(bookId: Int,chapterIds:MutableList<Int>): MutableMap<Int,BookContent>?

    /**
     * 根据bookId+内容id获取内容信息
     * @param bookId
     * @param contentIds
     * @return MutableList
     */
    @Throws(ServiceException::class)
    fun getContentByContentIds(bookId: Int,contentIds:MutableList<Int>): MutableMap<Int,BookContent>?

    /**
     * 保存book基本信息
     * @param book
     * @return Int
     */
    @Throws(ServiceException::class)
    fun saveBook(book: Book): Int

    /**
     * 保存章节对应内容信息
     * @param book
     * @return Int
     */
    @Throws(ServiceException::class)
    fun saveChapterContent(chapterId: Int,content: String,contentType:Int?): Int

    /**
     * 保存章节结构信息
     * @param chapter
     * @return Int
     */
    @Throws(ServiceException::class)
    fun saveChapterInfo(chapter: BookChapter): Int

    /**
     * 删除章节结构信息
     * @param chapterId
     * @return Int
     */
    @Throws(ServiceException::class)
    fun deleteChapterInfo(chapterId: Int): Int
}