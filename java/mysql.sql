create table t_article
(
    id int primary key auto_increment comment '文章ID',
    title varchar(32) default '' not null comment '文章标题'
) comment '文章';