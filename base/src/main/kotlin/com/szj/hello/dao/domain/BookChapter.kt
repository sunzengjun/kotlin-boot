package com.szj.hello.dao.domain

/**
 * 书目章节实体
 * Created by sunzengjun on 2017/10/18.
 */
data class BookChapter constructor(
        var chapter_id:Int?,
        var parent_chapter_id: Int?,
        var chapter_name: String,
        var book_id: Int?,
        var content_id: Int?,
        var sort_id: Int?,
        var ctime:  java.sql.Timestamp?,
        var mtime:  java.sql.Timestamp?,
        var is_delete: Int?
){
}