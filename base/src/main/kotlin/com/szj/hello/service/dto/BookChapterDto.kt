package com.szj.hello.service.dto

/**
 * Created by sunzengjun on 2017/10/18.
 */
class BookChapterDto constructor(
        var chapter_id:Int?,
        var parent_chapter_id: Int?,
        var chapter_name: String,
        var book_id: Int?,
        var content_id: Int?,
        var sort_id: Int?,
        var content: String?,
        var children: MutableList<BookChapterDto>?
){
}