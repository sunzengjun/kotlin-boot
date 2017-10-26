package com.szj.hello.service.impl

import com.szj.hello.dao.BookChapterMapper
import com.szj.hello.dao.BookContentMapper
import com.szj.hello.dao.BookMapper
import com.szj.hello.dao.domain.Book
import com.szj.hello.dao.domain.BookChapter
import com.szj.hello.dao.domain.BookContent
import com.szj.hello.service.BookService
import com.szj.hello.service.dto.BookChapterDto
import com.szj.hello.service.dto.BookDto
import com.szj.hello.utils.BookExceptionEnum

import com.szj.hello.exception.ServiceException
import com.szj.hello.utils.KBCollectionUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 书目serviceImpl
 * Created by sunzengjun on 2017/10/16.
 */
@Service
open class BookServiceImpl : BookService{

    companion object {
        val MAX_CHAPTER_LEVEL = 20   //最大可访问层级数
    }


    @Resource
    private val bookMapper :BookMapper? = null
    @Resource
    private val bookChapterMapper : BookChapterMapper? = null
    @Resource
    private val bookContentMapper : BookContentMapper? = null

    private val LOGGER = LoggerFactory.getLogger(BookServiceImpl::class.java)

    override fun getBookInfoById(bookId: Int): Book {
        val book = bookMapper!!.getBookById(bookId)?:throw ServiceException(BookExceptionEnum.BOOK_NOT_EXIST.errorCode, BookExceptionEnum.BOOK_NOT_EXIST.errorMsg)
        return book
    }

    @Throws(ServiceException::class)
    override fun getBookInfoAndChapterById(bookId: Int): BookDto {
        //获取书目信息
        val book = bookMapper!!.getBookById(bookId)?:throw ServiceException(BookExceptionEnum.BOOK_NOT_EXIST.errorCode,BookExceptionEnum.BOOK_NOT_EXIST.errorMsg)

        try{

            //获取章节信息
            val bookChapters :MutableList<BookChapter>? =  bookChapterMapper!!.getBookChapterByBookId(bookId)
            val bookChapterStructure = if(null!= bookChapters) getChapterStructure(bookChapters) else mutableListOf()

            //返回信息
            val returnBookDto =BookDto(
                    book.book_id,
                    book.book_name,
                    book.head_picture,
                    book.author_name,
                    book.author_picture,
                    book.author_introduction,
                    book.content_introduction,
                    book.abstract,
                    book.is_try_read,
                    book.is_online,
                    book.ucid,
                    book.is_free,
                    bookChapterStructure,
                    mutableListOf())

            return returnBookDto
        }catch(e:Exception){
            LOGGER.error("getBookInfoAndChapterById error;bookId:{$bookId}",e)
            throw ServiceException("BookServiceImpl.getBookInfoAndChapterById error",e)
        }

    }

    override fun getBookAllInfoById(bookId: Int): BookDto {
        //获取书目信息
        val book = bookMapper!!.getBookById(bookId)?:throw ServiceException(BookExceptionEnum.BOOK_NOT_EXIST.errorCode,BookExceptionEnum.BOOK_NOT_EXIST.errorMsg)

        try{

            //获取章节信息
            val bookChapters :MutableList<BookChapter>? =  bookChapterMapper!!.getBookChapterByBookId(bookId)
            val contentIds :List<Int> = KBCollectionUtil.extractPropertyToList(bookChapters,"content_id")
            val bookChapterStructure = if(null!= bookChapters) getChapterStructure(bookChapters) else mutableListOf()

            //获取内容信息
            val bookContents :MutableList<BookContent>?
                    = if( !contentIds.isEmpty() ){ bookContentMapper!!.getBookContentByIds(contentIds) }else mutableListOf()

            //返回信息
            val returnBookDto =BookDto(
                    book.book_id,
                    book.book_name,
                    book.head_picture,
                    book.author_name,
                    book.author_picture,
                    book.author_introduction,
                    book.content_introduction,
                    book.abstract,
                    book.is_try_read,
                    book.is_online,
                    book.ucid,
                    book.is_free,
                    bookChapterStructure,
                    bookContents)

            return returnBookDto
        }catch(e:Exception){
            LOGGER.error("getBookAllInfoById error;bookId:{$bookId}",e)
            throw ServiceException("BookServiceImpl.getBookAllInfoById error",e)
        }
    }

    override fun getContentByChapters(bookId: Int, chapterIds: MutableList<Int>): MutableMap<Int,BookContent>?{
        try{
            //获取章节信息
            val bookChapters :MutableList<BookChapter>?
                    =  bookChapterMapper!!.getBookChapterByBookIdAndChapterIds(bookId,chapterIds)?:return mutableMapOf()

            val contentIds :List<Int> = KBCollectionUtil.extractPropertyToList(bookChapters,"content_id")
            //获取内容信息
            val bookContents :MutableList<BookContent>?
                    = if( !contentIds.isEmpty() ){ bookContentMapper!!.getBookContentByIds(contentIds) }else mutableListOf()
            //组成成mapList
            var map :MutableMap<Int,BookContent>?= mutableMapOf()
            if(null == bookContents) return map
            for(bookChapter in bookChapters!!){
                if(0 != bookChapter.content_id){
                    for(bookContent in bookContents){
                        if(bookContent.content_id == bookChapter.content_id){
                            map!!.put(bookChapter.chapter_id!! , bookContent)
                            break
                        }
                    }
                }
            }
            return map
        }catch(e:Exception){
            LOGGER.error("getContentByChapter error;bookId:{$bookId}",e)
            throw ServiceException("BookServiceImpl.getContentByChapter error",e)
        }
    }

    override fun getContentByContentIds(bookId: Int, contentIds: MutableList<Int>):MutableMap<Int,BookContent>? {
        try{
            //获取章节信息
            val bookChapters :MutableList<BookChapter>? =  bookChapterMapper!!.getBookChapterByBookId(bookId)
            val contentIdsAllChapter :List<Int> = KBCollectionUtil.extractPropertyToList(bookChapters,"content_id")
            //获取contentIds和contentIdsAllChapter的交集
            val intersectContentIds =contentIdsAllChapter.intersect(contentIds).toList()
            //获取内容信息
            val bookContents :MutableList<BookContent>?
                    = if( !intersectContentIds.isEmpty() ){ bookContentMapper!!.getBookContentByIds(intersectContentIds) }else mutableListOf()
            //return bookContents!!.mapTo(arrayList(),{bookContents.})
            return bookContents!!.associateBy{it.content_id!! }.toMutableMap()
        }catch(e:Exception){
            LOGGER.error("getContentByChapter error;bookId:{$bookId}",e)
            throw ServiceException("BookServiceImpl.getContentByChapter error",e)
        }
    }

    override fun saveBook(book: Book): Int {
        try{
            if(book.book_id == 0){
                return bookMapper!!.insertBook(book)
            }else{
                return bookMapper!!.updateBook(book)
            }
        }catch(e:Exception){
            LOGGER.error("saveBook error!book:${book.toString()} ",e)
            return 0
        }
    }

    override fun saveChapterContent(chapterId: Int, content: String, contentType: Int?): Int {
        try{
            var  chapter =bookChapterMapper!!.getBookChapterByChapterId(chapterId)
            if(null == chapter){
                return 0
            }else if(0 == chapter.content_id){
                //新增章节内容
                val contentId = bookContentMapper!!.insertBookContent(content,contentType?:0)
                return bookChapterMapper!!.updateChapterContent(chapterId,contentId)
            }else{
                //修改章节内容
                return bookContentMapper!!.updateBookContent(chapter.content_id,content,contentType?:0)
            }
        }catch(e:Exception){
            LOGGER.error("saveChapterContent error!chapterId:$chapterId contentType:$contentType ",e)
            return 0
        }
    }

    override fun saveChapterInfo(chapter: BookChapter): Int {
        try{
            if(0 == chapter.chapter_id){
                return bookChapterMapper!!.insertBookChapter(chapter)
            }else{
                return bookChapterMapper!!.updateBookChapter(chapter)
            }
        }catch(e:Exception){
            LOGGER.error("saveChapterInfo error!chapter:${chapter.toString()} ",e)
            return 0
        }
    }

    override fun deleteChapterInfo(chapterId: Int): Int {
        try{
            val chapter = bookChapterMapper!!.getBookChapterByChapterId(chapterId)
            if(null != chapter && 0 == chapter.content_id){
                return bookChapterMapper!!.deleteBookChapter(chapterId)
            }else if (null != chapter && 0 != chapter.content_id){
                bookContentMapper!!.deleteBookContentById(chapter.content_id!!)
                return bookChapterMapper!!.deleteBookChapter(chapterId)
            }else{
                return 0
            }

        }catch(e:Exception){
            LOGGER.error("deleteChapterInfo error!chapterId:$chapterId ",e)
            return 0
        }
    }

    //获取章节结构体
    private fun getChapterStructure(bookChapters:MutableList<BookChapter>):MutableList<BookChapterDto>{
        var structures :MutableList<BookChapterDto> = mutableListOf()
        val iterator = bookChapters.iterator()
        while(iterator.hasNext()){
            //根目录
            val chapter = iterator.next()
            val id = chapter.chapter_id
            if(0 == chapter.parent_chapter_id){
                var structureRoot = BookChapterDto(
                        chapter.chapter_id,
                        chapter.parent_chapter_id,
                        chapter.chapter_name,
                        chapter.book_id,
                        chapter.content_id,
                        chapter.sort_id,
                        null,
                        mutableListOf())
                structures.add(structureRoot)
                //递归获取children
                getStructure(id!!,bookChapters,structureRoot,1)

            }
        }
        return structures
    }

    /**
     * 递归获取结构
     * @param parentId  当前节点父id
     * @param bookChapters  所有章节list
     * @param nowStruct   当前结构
     * @param level     当前层级
     * @return BookChapterDto
     */
    private fun getStructure(parentId:Int,bookChapters:MutableList<BookChapter>,nowStruct :BookChapterDto,level :Int):BookChapterDto{
        try{
            //层数判断
            var level2 = level
            if(level2 >= MAX_CHAPTER_LEVEL) return nowStruct

            val iterator = bookChapters.iterator()
            while(iterator.hasNext()){
                var chapter = iterator.next()
                var id = chapter.chapter_id
                if(parentId == chapter.parent_chapter_id){
                    var structureChild = BookChapterDto(
                            chapter.chapter_id,
                            chapter.parent_chapter_id,
                            chapter.chapter_name,
                            chapter.book_id,
                            chapter.content_id,
                            chapter.sort_id,
                            null,
                            mutableListOf())
                    //添加该子节点
                    nowStruct.children?.add(structureChild)
                    //层级+1,,递归该子节点
                    level2++
                    getStructure(id!!,bookChapters,structureChild,level2)

                }
            }
        }catch(e:Exception){
            LOGGER.error("getStructure error!parentId:{$parentId} bookChapters:{$bookChapters} nowStruct:{$nowStruct}",e)
        }finally{
            return nowStruct
        }



    }

}
