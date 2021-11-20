<!DOCTYPE html>
<html lang="zh-CN" data-default-color-scheme=auto>
<head>
    <meta charset="UTF-8">
    <link rel="apple-touch-icon" sizes="76x76" href="${config.favicon}">
    <link rel="icon" href="${config.favicon}">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta name="theme-color" content="#2f4154">
    <meta name="description" content="${author.description}">
    <meta name="author" content="${author.name}">
    <meta name="keywords" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta property="og:type" content="website">
    <meta property="og:title" content="归档">
    <meta property="og:url" content="${config.href}/page/archive.html">
    <meta property="og:site_name" content="${config.name}">
    <meta property="og:description" content="${author.description}">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:author" content="${author.name}">
    <meta property="article:tag" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta name="twitter:card" content="summary_large_image">
    <meta name="generator" content="Hexo 5.4.0">
    <title>归档 - ${config.name}</title>
    <#include "./layout/head.ftl" />

</head>


<body>
<header style="height: 60vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('${config.archiveBackground}') no-repeat center center;
                 background-size: cover;">
        <div class="full-bg-img">
            <div class="mask flex-center" style="background-color: rgba(0, 0, 0, 0.3)">
                <div class="page-header text-center fade-in-up">
                    <span class="h2" id="subtitle">
                    </span>
                </div>
            </div>
        </div>
    </div>
</header>

<main>
    <div class="container nopadding-x-md">
        <div class="py-5" id="board">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-10 m-auto">
                        <div class="list-group">
                            <p class="h4">共计 31 篇文章</p>
                            <hr>
                            <p class="h5">2020</p>
                            <a href="/posts/753ba7a9/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">Crawlab Lite 正式发布，更轻量的爬虫管理平台</span>
                                <time style="float: right;">07-15</time>
                            </a>
                            <a href="/posts/dc4bf29f/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">源码解读 Golang 中 sync.Map 的实现原理</span>
                                <time style="float: right;">03-23</time>
                            </a>
                            <a href="/posts/14690cf1/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">经典面试题：青蛙跳台阶</span>
                                <time style="float: right;">03-09</time>
                            </a>
                            <a href="/posts/f94fb1be/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">探究 Go 语言 defer 语句的三种机制</span>
                                <time style="float: right;">03-01</time>
                            </a>
                            <a href="/posts/1273f6f1/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">一道快速考察 Python 基础的面试题</span>
                                <time style="float: right;">02-08</time>
                            </a>
                            <a href="/posts/e8ed6836/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">编写自己的 GitHub Action，体验自动化部署</span>
                                <time style="float: right;">01-20</time>
                            </a>
                            <p class="h5">2019</p>
                            <a href="/posts/19c1cb9d/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">Python 2 与 3 共存了 11 年，新年就要和它道别</span>
                                <time style="float: right;">12-31</time>
                            </a>
                            <a href="/posts/1c05b194/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">30 年前的圣诞节，Python 序章被谱写</span>
                                <time style="float: right;">12-24</time>
                            </a>
                            <a href="/posts/95ac9d73/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">文言文编程火了，可我完全学不懂</span>
                                <time style="float: right;">12-21</time>
                            </a>
                            <a href="/posts/d2d106a5/" class="list-group-item list-group-item-action">
                                <span class="archive-post-title">通过 Python 理解 Mixin 概念</span>
                                <time style="float: right;">12-07</time>
                            </a>
                        </div>
                        <nav aria-label="navigation">
    <span class="pagination" id="pagination">
      <span class="page-number current">1</span><a class="page-number" href="/archives/page/2/#board">2</a><a
                class="page-number" href="/archives/page/3/#board">3</a><a class="page-number"
                                                                           href="/archives/page/4/#board">4</a><a
                class="extend next" rel="next" href="/archives/page/2/#board"><i
                    class="iconfont icon-arrowright"></i></a>
    </span>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#include './layout/additional.ftl' />
    <#include './layout/search.ftl' />
</main>
<#include "./layout/footer.ftl" />
</body>
<#include './layout/script.ftl' />
<script>
    new TypeIt("#subtitle", {
        strings: [
            "归档"
        ],
        speed: 150,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>