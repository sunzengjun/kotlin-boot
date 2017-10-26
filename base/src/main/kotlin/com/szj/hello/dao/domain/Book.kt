package com.szj.hello.dao.domain

/**
 * 书目实体
 * Created by sunzengjun on 2017/10/16.
 */
data class Book constructor(
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
        var ctime:  java.sql.Timestamp?,
        var mtime:  java.sql.Timestamp?,
        var is_delete: Int?

        ){

}
