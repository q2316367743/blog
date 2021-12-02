# 博客
# CREATE DATABASE `blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table category
(
    id          int unsigned primary key comment '分类ID',
    name        varchar(32)  default ''                    not null comment '分类标题',
    p_id        int unsigned default 0                     not null comment '父级分类',
    create_time datetime     default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime     default '1998-08-06 00:00:00' not null comment '更新时间'
) comment '文章分类表';

create table article
(
    id               int unsigned auto_increment primary key comment '文章ID',
    title            varchar(32)         default ''                    not null comment '文章标题',
    status           tinyint(1)          default 1                     not null comment '状态，具体查看枚举',
    image            varchar(255)        default ''                    not null comment '文章展示图片',
    category_id      int unsigned        default 0                     not null comment '分类ID',
    tags             varchar(64)         default ''                    not null comment '标签，使用英文逗号隔开',
    sequence         bigint unsigned     default 0                     not null comment '排序，默认更新时间截',
    description      varchar(128)        default ''                    not null comment '描述',
    create_time      datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time      datetime            default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete        tinyint(1) unsigned default 0                     not null comment '逻辑删除',
    word_count       int unsigned        default 0                     not null comment '字数统计',
    read_time        int unsigned        default 0                     not null comment '阅读时间，根据字数，单位分钟',
    view_count       int unsigned        default 0                     not null comment '阅读人数',
    comment_count    int unsigned        default 0                     not null comment '评论数量',
    type             tinyint unsigned    default 1                     not null comment '文章内容编写类型',
    content          text comment '文章内容，内容为html文件',
    original_content text comment '文章原始内容，内容为Markdown文档',
    constraint fk_article_category foreign key (category_id) references category (id)
) comment '文章';

create table page
(
    id               int unsigned auto_increment primary key comment '页面ID',
    title            varchar(32)         default ''                    not null comment '页面标题',
    image            varchar(255)        default ''                    not null comment '页面展示图片',
    status           tinyint(1)          default 0                     not null comment '状态，具体查看枚举',
    description      varchar(128)        default ''                    not null comment '描述',
    create_time      datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time      datetime            default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete        tinyint(1) unsigned default 0                     not null comment '逻辑删除',
    view_count       int unsigned        default 0                     not null comment '阅读人数',
    comment_count    int unsigned        default 0                     not null comment '评论数量',
    content          text comment '文章内容，内容为html文件',
    original_content text comment '文章原始内容，内容为Markdown文档'
) comment '页面';

create table notice
(
    id          int unsigned auto_increment primary key comment 'ID',
    title       varchar(64)         default ''                    not null comment '通知标题',
    content     varchar(255)        default ''                    not null comment '通知内容，html字符串',
    create_time datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime            default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete   tinyint(1) unsigned default 0                     not null comment '逻辑删除'
) comment '通知';

create table comment
(
    id              int unsigned auto_increment primary key comment 'ID',
    email           varchar(64)         default ''                    not null comment '电子邮箱',
    website         varchar(64)         default ''                    not null comment '个人网站',
    nickname        varchar(32)         default ''                    not null comment '昵称',
    create_time     datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    status          tinyint(1) unsigned default 1                     not null comment '是否展示，0：未审查，1：通过，2：不通过',
    browser         varchar(64)         default ''                    not null comment '浏览器版本',
    system_version  varchar(64)         default ''                    not null comment '系统',
    type            tinyint unsigned    default 1                     not null comment '评论类型；1：访客',
    article_id      int unsigned        default 0                     not null comment '文章ID',
    root_id         int unsigned        default 0                     not null comment '所属评论，顶级为0',
    target_id       int unsigned        default 0                     not null comment '回复对象ID，冗余',
    target_nickname varchar(32)         default ''                    not null comment '回复对象昵称，冗余',
    target_website  varchar(64)         default ''                    not null comment '回复对象个人网站，冗余',
    content         text                                              not null comment '评论内容'
) comment '评论表';

create table message
(
    id          int unsigned auto_increment primary key comment 'ID',
    create_time datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    is_delete   tinyint(1) unsigned default 0                     not null comment '逻辑删除',
    is_read     tinyint(1) unsigned default 0                     not null comment '是否阅读',
    type        tinyint unsigned    default 0                     not null comment '类型',
    name        varchar(32)         default ''                    not null comment '名称',
    email       varchar(48)         default ''                    not null comment '电子邮箱',
    url         varchar(128)        default ''                    not null comment '个人网站',
    content     text comment '内容'
) comment '留言板';

create table link
(
    id          int unsigned auto_increment primary key comment 'ID',
    create_time datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    is_delete   tinyint(1) unsigned default 0                     not null comment '逻辑删除',
    icon        varchar(128)        default ''                    not null comment '图标',
    name        varchar(32)         default ''                    not null comment '名称',
    email       varchar(48)         default ''                    not null comment '电子邮箱',
    url         varchar(128)        default ''                    not null comment '个人网站',
    description varchar(255)        default ''                    not null comment '描述',
    status      tinyint(1) unsigned default 1                     not null comment '状态',
    reason      varchar(255)        default ''                    not null comment '不通过原因'
) comment '友情链接';

create table menu
(
    id          int unsigned auto_increment primary key comment 'ID',
    icon        varchar(64)         default ''                    not null comment '图标',
    name        varchar(16)         default ''                    not null comment '名称',
    target      tinyint unsigned    default 0                     not null comment '打开方式：0：当前页面，1：新页面',
    href        varchar(128)        default ''                    not null comment '链接',
    p_id        int unsigned        default 0                     not null comment '父级菜单，顶级为0',
    create_time datetime            default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime            default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete   tinyint(1) unsigned default 0                     not null comment '逻辑删除'

) comment '菜单';

# 测试数据

insert into menu
    value (1, 'iconfont icon-home-fill', '首页', 0, '/', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (2, 'iconfont icon-archive-fill', '归档', 0, '/archive.html', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00',
           0);
insert into menu
    value (3, 'iconfont icon-category-fill', '分类', 0, '/category.html', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00',
           0);
insert into menu
    value (4, 'iconfont icon-user-fill', '关于', 0, '/about.html', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (5, 'iconfont icon-link-fill', '连接', 0, '/', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (6, '', '友链', 0, '/page/link.html', 5, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (7, '', '我的页面', 0, '/page/1.html', 5, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (8, 'iconfont icon-books', '文档', 0, '/', 0, '2021-11-17 00:00:00', '2021-11-17 00:00:00', 0);
insert into menu
    value (9, '', '安装主题', 1, 'https://hexo.fluid-dev.com/docs/start/', 8, '2021-11-17 00:00:00', '2021-11-17 00:00:00',
           0);
insert into menu
    value (10, '', '配置指南', 1, 'https://hexo.fluid-dev.com/docs/guide/', 8, '2021-11-17 00:00:00', '2021-11-17 00:00:00',
           0);
insert into menu
    value (11, '', '图标用法', 1, 'https://hexo.fluid-dev.com/docs/icon/', 8, '2021-11-17 00:00:00', '2021-11-17 00:00:00',
           0);

insert into category(id, name) value (1, '个人杂谈');

insert into article (id, title, image, category_id, tags, sequence, description, create_time,
                     update_time, is_delete,
                     word_count, read_time, view_count, comment_count, content, original_content)
values (1, '测试文章标题', 'https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png',
        1, '杂谈,随笔', 1,
        '测试文章', '2021-06-21 00:00:00', '2021-06-21 00:00:00', 0, 1315, 120, 0, 0,
        '测试内容', '测试内容');

insert into page (id, title, image, description, create_time,
                  update_time, is_delete, view_count, comment_count, content, original_content)
values (1, '测试页面标题', 'https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png',
        '测试文章', '2021-06-21 00:00:00', '2021-06-21 00:00:00', 0, 1315, 120,
        '测试内容<a href="https://esion.xyz" target="_blank">我的页面</a>', '测试内容[我的页面](https://esion.xyz)');