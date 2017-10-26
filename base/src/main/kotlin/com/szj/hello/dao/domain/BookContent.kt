package com.szj.hello.dao.domain

/**
 * 书目内容实体
 * Created by sunzengjun on 2017/10/18.
 */
data class BookContent constructor(
        var content_id:Int?,
        var content: String?,
        var content_type: Int?,
        var ctime:  java.sql.Timestamp?,
        var mtime:  java.sql.Timestamp?,
        var is_delete: Int?
){
}