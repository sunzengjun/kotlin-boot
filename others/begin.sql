CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'book_id主键自增',
  `book_name` varchar(255) NOT NULL DEFAULT '' COMMENT '书名',
  `head_picture` varchar(255) NOT NULL DEFAULT '' COMMENT '头图',
  `author_name` varchar(255) NOT NULL DEFAULT '' COMMENT '作者姓名',
  `author_picture` varchar(255) NOT NULL DEFAULT '' COMMENT '作者图片',
  `author_introduction` varchar(2095) NOT NULL DEFAULT '',
  `content_introduction` varchar(2095) NOT NULL DEFAULT '' COMMENT '内容简介',
  `abstract` varchar(2095) NOT NULL,
  `is_try_read` tinyint(4) NOT NULL DEFAULT '0',
  `is_online` tinyint(4) NOT NULL DEFAULT '0',
  `ucid` varchar(32) NOT NULL DEFAULT '' COMMENT '发布者ucid',
  `is_free` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否收费：0免费 1收费',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（逻辑删除，0-不删除，1-删除）',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书目表';


CREATE TABLE `book_chapter` (
  `chapter_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '章节id，主键自增',
  `parent_chapter_id` int(11) NOT NULL DEFAULT '0' COMMENT '父章节id',
  `chapter_name` varchar(255) NOT NULL DEFAULT '' COMMENT '章节名字',
  `book_id` int(11) NOT NULL COMMENT '对应书目id',
  `content_id` int(11) NOT NULL DEFAULT '0' COMMENT '对应内容id',
  `sort_id` int(11) NOT NULL DEFAULT '0' COMMENT '排序id',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（逻辑删除，0-不删除，1-删除）',
  PRIMARY KEY (`chapter_id`),
  INDEX  parent_chapter_id ( `parent_chapter_id` ),
  INDEX  book_id ( `book_id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书目章节表';



CREATE TABLE `book_content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容id,主键自增',
  `content` text COMMENT '内容',
  `content_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '内容类别',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（逻辑删除，0-不删除，1-删除）',
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书目内容表';


CREATE TABLE `book_image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键索引唯一',
  `book_id` bigint(20) NOT NULL,
  `url` varchar(200) NOT NULL,
  `ctime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除（逻辑删除，0-不删除，1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;






