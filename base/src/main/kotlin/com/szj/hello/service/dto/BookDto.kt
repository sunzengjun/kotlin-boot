package com.szj.hello.service.dto

import com.szj.hello.dao.domain.BookContent

/**
 * Created by sunzengjun on 2017/10/18.
 */
class BookDto constructor(
        var book_id:Int?,
        var book_name: String,
        var head_picture: String?,
        var author_name: String?,
        var author_picture: String?,
        var author_introduction: String?,
        var content_introduction: String?,
        var abstract: String?,
        var is_try_read: Int?,
        var is_online: Int?,
        var ucid: String?,
        var is_free: Int?,
        var chapters: MutableList<BookChapterDto>?,
        var contents:MutableList<BookContent>?

){
}