package com.szj.hello.dao

import com.szj.hello.dao.domain.BookChapter
import org.apache.ibatis.annotations.Param

/**
 * 书目章节Mapper
 * Created by sunzengjun on 2017/10/18.
 */
interface BookChapterMapper {

    /**
     * 根据bookId获取章节内容
     * @param bookId
     * @return MutableList
     */
    fun getBookChapterByBookId(@Param("bookId") bookId: Int) : MutableList<BookChapter>?

    /**
     * 根据bookId获取章节内容
     * @param chapterId
     * @return BookChapter
     */
    fun getBookChapterByChapterId(@Param("chapterId") chapterId: Int) : BookChapter?

    /**
     * 根据bookId+章节id获取章节内容
     * @param bookId
     * @param chapterIds
     * @return MutableList
     */
    fun getBookChapterByBookIdAndChapterIds(@Param("bookId") bookId: Int,@Param("chapterIds") chapterIds:MutableList<Int>) : MutableList<BookChapter>?

    /**
     * 保存书目章节
     * @param bookChapter
     * @return Int
     */
    fun updateBookChapter(@Param("bookChapter") bookChapter: BookChapter):Int

    /**
     * 添加书目章节
     * @param bookChapter
     * @return Int
     */
    fun insertBookChapter(@Param("bookChapter") bookChapter: BookChapter):Int

    /**
     * 保存书目章节的对应内容id
     * @param chapterId
     * @param contentId
     * @return Int
     */
    fun updateChapterContent(@Param("chapterId") chapterId: Int,@Param("contentId") contentId: Int):Int

    /**
     * 删除书目章节
     * @param bookChapter
     * @return Int
     */
    fun deleteBookChapter(@Param("chapterId") chapterId: Int):Int
}