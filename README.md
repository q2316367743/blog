# 个人博客

前端采用**Vue+Element-UI**，后台采用**Spring Boot**

## 网站设计

`/`：前台前端地址

`/api/*`：前台后端地址

`/manage`：后台前端地址

`/manage/api/*`：后台后端地址

## 项目安装

软件及推荐版本：

- JDK@1.8+
- maven@3.8.1
- mysql@5.7+ / MariaDB@10.0+
- node@14.17.0
- npm@6.14.13
- nginx@1.18

>  图片存储

采用springboot进行上传到nginx映射目录下，再采用nginx进行访问

所以首先安装nginx，假设安装目录是`/opt/nginx`，html文件位于`/opt/nginx/html`

```sh
git clone https://gitee.com/qiaoshengda/blog.git
cd blog
# 构建java
cd java
mvn package -Dmaven.test.skip=true -Dspring.profiles,active=pro
# 运行后台
nohad java -jar blog-0.0.1-SNAPSHOT.jar /opt/nginx/html/assets/image/blog > info.log 2&>1 &
# 构建前台
cd ../web
npm install
npm build
cp -r -f dist/* /opt/nginx/html
# 构建管理
cd ../manage
npm install
npm build
mkdir /opt/nginx/html/mamage
cp -r -f dist/* /opt/nginx/html/mamage
```

nginx配置文件

```conf
worker_processes  1;

events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;

    keepalive_timeout  65;

    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location /api/ {
            proxy_pass http://127.0.0.1:8990/api/
        }
        location /manage/api/ {
            proxy_pass http://127.0.0.1:8990/manage/
        }
        location / {
            root   html;
            index  index.html index.htm;
		}
    }
}
```

## 版本

### 1.0（开发中）

- 网站基本信息/配置信息
- 作者信息
- 文章列表
- 文章分类
- 文章搜索

### 2.0

- 增加访客（地点，IP，时间）统计
- 增加文章操作日志
- 增加标签定时任务，获取最多的标签数量，标签列表

## 开源框架

- [vditor](https://github.com/Vanessa219/vditor)：一款浏览器端的 Markdown 编辑器，支持所见即所得（富文本）、即时渲染（类似 Typora）和分屏预览模式。
- [sa-vue-admin](https://gitee.com/click33/sa-vue-admin)：一个多窗口后台模板，流畅、易上手、提高生产力。