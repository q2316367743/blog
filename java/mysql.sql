# 博客

# CREATE DATABASE `blog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table t_category
(
    id int unsigned primary key auto_increment comment '分类ID',
    name varchar(32) default '' not null comment '分类标题',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除'
) comment '文章分类表';

create table t_article
(
    id int unsigned primary key auto_increment comment '文章ID',
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
    content text comment '文章内容，内容为Markdown文档',
    constraint fk_article_category foreign key(category_id) references t_category(id)
) comment '文章';

create table t_notice
(
    id int unsigned primary key auto_increment comment 'ID',
    title varchar(64) default '' not null comment '通知标题',
    content varchar(255) default '' not null comment '通知内容，html字符串',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    update_time datetime default '1998-08-06 00:00:00' not null comment '更新时间',
    is_delete tinyint(1) unsigned default 0 not null comment '逻辑删除'
) comment '通知';

create table t_comment
(
    id int unsigned primary key auto_increment comment 'ID',
    email varchar(64) default '' not null comment '电子邮箱',
    website varchar(64) default '' not null comment '个人网站',
    nickname varchar(32) default '' not null comment '昵称',
    create_time datetime default '1998-08-06 00:00:00' not null comment '创建时间',
    is_show tinyint(1) unsigned default 0 not null comment '是否展示',
    browser varchar(64) default '' not null comment '浏览器版本',
    system_version varchar(32) default '' not null comment '系统',
    type tinyint unsigned default 1 not null comment '评论类型；1：访客',
    article_id int unsigned default 0 not null comment '文章ID',
    p_id int unsigned default 0 not null comment '所在评论的ID',
    target_nickname varchar(32) default '' not null comment '回复对象昵称，冗余',
    target_website varchar(64) default '' not null comment '回复对象个人网站，冗余'
) comment '评论表';

create view v_article_list as
    select a.id, a.title, a.image, a.category_id, c.name category_name,
           a.tags, a.description, a.create_time, a.update_time, a.sequence
    from t_article a
    left join t_category c
    on a.category_id = c.id
    where a.is_delete = 0;

create view v_article_info as
    select a.id, a.title, a.category_id, c.name category_name,
           a.tags, a.create_time, a.update_time, a.word_count, a.read_time, a.view_count,
           a.comment_count, a.content
    from t_article a
    left join t_category c
    on a.category_id = c.id
    where a.is_delete = 0;

# 中间表
create view v_article_count as 
	select category_id as id, count(1) article_count
        from t_article
        group by category_id;

create view v_category_list as
    select c.id, c.name, ifnull(a.article_count, 0) as article_count
    from t_category c
    left join v_article_count a
    on a.id = c.id;

create view v_notice_list as
    select id, title, content
    from t_notice
    where is_delete = 0
    order by update_time desc
    limit 4;

# 测试数据

insert into t_category(id, name) value (1, '个人杂谈');

insert into t_article (title, image, category_id, tags, sequence, description, create_time, update_time, is_delete,
                       word_count, read_time, view_count, comment_count, content)
values ('学习计划及写作计划', 'https://img-blog.csdnimg.cn/img_convert/cce6857f9276c2ba78d3f4b9af3b036f.png', 1, '杂谈,随笔', 1,
        '建议读者可以看一下，里面有近期准备写的内容。', '2021-06-21 00:00:00', '2021-06-21 00:00:00', 0, 1315, 120, 0, 0,
        '# 介绍\n\n本篇文章是自己对于Java中权限框架的理解和探索\n\n# 常见的权限操作\n\n## 学习JavaWeb初期：session\n\n我们使用Session做权限，当登录后，将用户信息放到Session中，再写个过滤器拦截固定请求，在过滤器里面获取存入的session，如果存在放入的用户信息，就代表已经登录，如果不存在就可以重定向到登录页面。\n\n## 第一个权限框架：Shiro\n\n这时候会接触一个框架叫做`shiro`，这个框架的原理也是讲认证信息存入session，他会实现很多过滤器帮我们做权限，比如认证，授权，角色等等，shiro的核心流程：\n\n![](https://atts.w3cschool.cn/attachments/image/wk/shiro/2.png)\n\n他会通过核心类`SecurityManager`来控制权限，通过自定义`Realm`实现类来实现用户信息获取，`subject`代表着一个用户主体\n\nshiro本质上也是通过session进行权限控制，通过拦截器进行鉴权\n\n## token+redis\n\n在这之前，都是通过session进行鉴权，项目只能部署到同一个服务器，如果想实现高可用，就要通过分布式session的方式，将session存到一个公共的地方，才能实现多个服务器共用session\n\n当项目进入前后端分离的时代，静态网页的服务器可能就和后端服务器不在一起了，就没办法进行session的方式\n\n众所周知，http是一个无状态协议，服务端没办法判断不同的请求是否是同一个用户，session的原理就是服务端生成一个jssesionid，将这个id放入到cookie中，服务器会从请求中获取cookie的jssesionid来判断不同的请求是否是同一个用户\n\n这时，我们就自然而然的想到，在登陆的时候，如果服务端生成一个token，将这个令牌和用户信息存入到redis中，并且返回给用户，再写个过滤器，从每个请求都存在的请求头中获取token值，再在redis中查找，就代表登陆，没有查询到就代表没登录\n\n这个实现方式推荐一个框架：[sa-token](http://sa-token.dev33.cn/)\n\n这个框架是这个模式的一个最佳实践，后面会讲解怎么用\n\n## jwt\n\n上面的`token+redis`的模式解决了微服务场景和前后端分离场景下的认证问题，但仍然有个问题，就是token是存在服务端的，hu会占用服务器内容，这时候就要说jwt的认证模式\n\njwt生成的token是一段有意义的字符串，token生成会返回给用户，不会在服务端存储，这是一种去中心化的设计，不会占用服务器内容，token字符串也会存储信息，比如用户的基本信息，功能权限信息可以直接放在token中。用bit位表示用户所具有的功能权限\n\n但他也有有一个很大的缺点，就是服务端无法主动让token失效，无法实现一个账户同一时间只能一个设备登录，一旦生成token都无法控制\n\n## 总结\n\n一般如果是传统mvc模式的项目，或者一些去权限很严格的项目，都会使用session模式，不同权限返回的页面也不一样，\n\n如果是前后端分离项目一般推荐token+redis或jwt，这两个框架各有优缺点。\n\n# 学习权限框架\n\n首先，我们要理解，无论是哪种权限框架，他的本质就是过滤器或拦截器。在过滤器中进行权限判断\n\n在传统的session模式下，一旦访问我们的连接，服务器会自动在cookie中查找jssesionid，并进行session绑定，等到过滤器的时候，我们只需要判断session中是否有我们放入的数据，如果有，则代表登录。\n\n等到前后端分离后，我们无法在session中存放登录标记，所以到了前后端分离时，我们就要引入一个新的知识，叫做`ThreadLocal`，学过tomcat的都知道，tomcat是个多线程 服务器， 每次请求， 都是一个线程在， 而`ThreadLocal`就是线程中存放数据的，数据只在线程的生命周期有效，所以，我们可以在过滤器中判断token在redis中是否存在，如果存在，我们将redis中存放的用户信息放入到`ThreadLocal`中，再写个工具类，可以在controller中获取`ThreadLocal`中的数据，这样就实现了登录，鉴权功能\n\n```java\npublic class Start{\n\tpublic static void main(String[] args){\n\t\tSystem.out.println("Hello World");\n\t}\n}\n```');