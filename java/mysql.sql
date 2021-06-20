create table category
(
    id int unsigned primary key auto_increment comment '分类ID',
    name varchar(32) default '' not null comment '分类标题'
) comment '文章分类表';

create table t_article
(
    id int unsigned primary key auto_increment comment '文章ID',
    title varchar(32) default '' not null comment '文章标题',
    image varchar(64) default '' not null comment '文章展示图片',
    category_id int unsigned default 0 not null comment '分类ID',
    tags varchar(64) default '' not null comment '标签',
    sequence bigint unsigned default 0 not null comment '排序，默认更新时间截',
    description varchar(128) default '' not null comment '描述',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除',
    word_count int unsigned default 0 not null comment '字数统计',
    read_time int unsigned default 0 not null comment '阅读时间，根据字数',
    view_count int unsigned default 0 not null comment '阅读人数',
    comment_count int unsigned default 0 not null comment '评论数量',
    content text comment '文章内容，内容为Markdown文档',
    constraint fk_article_category foreign key(category_id) references t_article(id)
) comment '文章';