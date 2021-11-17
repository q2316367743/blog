# 博客

# CREATE DATABASE `blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table category
(
    id int unsigned primary key comment '分类ID',
    name varchar(32) default '' not null comment '分类标题',
    p_id int unsigned default 0 not null comment '父级分类',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除'
) comment '文章分类表';

create table article
(
    id int unsigned primary key comment '文章ID',
    identification varchar(32) not null comment '文章标识',
    title varchar(32) default '' not null comment '文章标题',
    image varchar(255) default '' not null comment '文章展示图片',
    category_id int unsigned default 0 not null comment '分类ID',
    tags varchar(64) default '' not null comment '标签，使用英文逗号隔开',
    sequence bigint unsigned default 0 not null comment '排序，默认更新时间截',
    description varchar(128) default '' not null comment '描述',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除',
    word_count int unsigned default 0 not null comment '字数统计',
    read_time int unsigned default 0 not null comment '阅读时间，根据字数，单位分钟',
    view_count int unsigned default 0 not null comment '阅读人数',
    comment_count int unsigned default 0 not null comment '评论数量',
    content text comment '文章内容，内容为html文件',
    original_content text comment '文章原始内容，内容为Markdown文档',
    constraint fk_article_category foreign key(category_id) references category(id),
    constraint uk_article_identification unique (identification)
) comment '文章';

create index idx_article_identification
on article(identification);

create table notice
(
    id int unsigned primary key comment 'ID',
    title varchar(64) default '' not null comment '通知标题',
    content varchar(255) default '' not null comment '通知内容，html字符串',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除'
) comment '通知';

create table comment
(
    id int unsigned primary key comment 'ID',
    email varchar(64) default '' not null comment '电子邮箱',
    website varchar(64) default '' not null comment '个人网站',
    nickname varchar(32) default '' not null comment '昵称',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    is_show tinyint(1) unsigned default 0 not null comment '是否展示，0：未审查，1：通过，2：不通过',
    browser varchar(64) default '' not null comment '浏览器版本',
    system_version varchar(32) default '' not null comment '系统',
    type tinyint unsigned default 1 not null comment '评论类型；1：访客',
    article_id int unsigned default 0 not null comment '文章ID',
    p_id int unsigned default 0 not null comment '所在评论的ID，顶级为0',
    root_id int unsigned default 0 not null comment '所属跟评论，顶级为0',
    target_id int unsigned comment '回复对象ID，冗余',
    target_nickname varchar(32) default '' not null comment '回复对象昵称，冗余',
    target_website varchar(64) default '' not null comment '回复对象个人网站，冗余',
    content text not null comment '评论内容'
) comment '评论表';

create table message
(
    id int unsigned primary key comment 'ID',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除',
    type tinyint unsigned default 0 not null comment '类型',
    name varchar(32) default '' not null comment '名称',
    email varchar(48) default '' not null comment '电子邮箱',
    content text comment '内容'
) comment '留言板';

# 测试数据

insert into category(id, name) value (1, '个人杂谈');

insert into article (id, identification, title, image, category_id, tags, sequence, description, create_time, update_time, is_delete,
                       word_count, read_time, view_count, comment_count, content, original_content)
values (1, 'ce_shi_wen_zhang', '测试文章标题', 'https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png', 1, '杂谈,随笔', 1,
        '测试文章', '2021-06-21 00:00:00', '2021-06-21 00:00:00', 0, 1315, 120, 0, 0,
        '测试内容', '测试内容');