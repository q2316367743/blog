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
    <meta property="og:title" content="分类">
    <meta property="og:url" content="${config.href}/page/category.html">
    <meta property="og:site_name" content="${config.name}">
    <meta property="og:description" content="${author.description}">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:author" content="${author.name}">
    <meta property="article:tag" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta name="twitter:card" content="summary_large_image">
    <meta name="generator" content="Hexo 5.4.0">
    <title>分类 - ${config.name}</title>
    <#include "./layout/head.ftl" />

</head>


<body>
<header style="height: 60vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('${config.categoryBackground}') no-repeat center center;
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
                        <div class="category-list">
                            <div class="category row">
                                <a
                                        class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                        id="heading-a7f5f35426b927411fc9231b56382173" role="tab" data-toggle="collapse"
                                        href="#collapse-a7f5f35426b927411fc9231b56382173"
                                        aria-expanded="false"
                                >
                                    Python
                                    <i class="iconfont icon-arrowright"></i>
                                </a>
                                <a href="/categories/Python/" class="category-count col-2 col-md-1">
                                    <i class="iconfont icon-articles"></i>
                                    <span>22</span>
                                </a>
                                <div class="category-collapse collapse" id="collapse-a7f5f35426b927411fc9231b56382173"
                                     role="tabpanel" aria-labelledby="heading-a7f5f35426b927411fc9231b56382173">
                                    <div class="category-post-list">
                                        <a href="/posts/14690cf1/" class="list-group-item list-group-item-action">
                                            <span class="category-post">经典面试题：青蛙跳台阶</span>
                                        </a>
                                        <a href="/posts/1273f6f1/" class="list-group-item list-group-item-action">
                                            <span class="category-post">一道快速考察 Python 基础的面试题</span>
                                        </a>
                                        <a href="/posts/19c1cb9d/" class="list-group-item list-group-item-action">
                                            <span class="category-post">Python 2 与 3 共存了 11 年，新年就要和它道别</span>
                                        </a>
                                        <a href="/posts/1c05b194/" class="list-group-item list-group-item-action">
                                            <span class="category-post">30 年前的圣诞节，Python 序章被谱写</span>
                                        </a>
                                        <a href="/posts/d2d106a5/" class="list-group-item list-group-item-action">
                                            <span class="category-post">通过 Python 理解 Mixin 概念</span>
                                        </a>
                                        <a href="/posts/8d6f309/" class="list-group-item list-group-item-action">
                                            <span class="category-post">Python 3.8.0 正式版发布，新特性初体验</span>
                                        </a>
                                        <a href="/posts/b3467fe4/" class="list-group-item list-group-item-action">
                                            <span class="category-post">众咖云集的 PyCon 2019 上海站，大佬们都讲了啥</span>
                                        </a>
                                        <a href="/posts/2a0e96d6/" class="list-group-item list-group-item-action">
                                            <span class="category-post">又一家数据公司被查，爬虫到底做错了什么？</span>
                                        </a>
                                        <a href="/posts/d5ff5ff8/" class="list-group-item list-group-item-action">
                                            <span class="category-post">三步搞定 Android 逆向</span>
                                        </a>
                                        <a href="/posts/a46b4fd7/" class="list-group-item list-group-item-action">
                                            <span class="category-post">爬虫工程师常用的 Chrome 插件</span>
                                        </a>
                                        <a href="/categories/Python/" class="list-group-item list-group-item-action">
                                            <span class="category-post">More...</span>
                                        </a>

                                    </div>
                                </div>
                            </div>
                            <div class="category row">
                                <a
                                        class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                        id="heading-6311ae17c1ee52b36e68aaf4ad066387" role="tab" data-toggle="collapse"
                                        href="#collapse-6311ae17c1ee52b36e68aaf4ad066387"
                                        aria-expanded="false"
                                >
                                    Other
                                    <i class="iconfont icon-arrowright"></i>
                                </a>
                                <a href="/categories/Other/" class="category-count col-2 col-md-1">
                                    <i class="iconfont icon-articles"></i>
                                    <span>5</span>
                                </a>
                                <div class="category-collapse collapse" id="collapse-6311ae17c1ee52b36e68aaf4ad066387"
                                     role="tabpanel" aria-labelledby="heading-6311ae17c1ee52b36e68aaf4ad066387">
                                    <div class="category-post-list">
                                        <a href="/posts/e8ed6836/" class="list-group-item list-group-item-action">
                                            <span class="category-post">编写自己的 GitHub Action，体验自动化部署</span>
                                        </a>
                                        <a href="/posts/95ac9d73/" class="list-group-item list-group-item-action">
                                            <span class="category-post">文言文编程火了，可我完全学不懂</span>
                                        </a>
                                        <a href="/posts/7560a22f/" class="list-group-item list-group-item-action">
                                            <span class="category-post">GitHub 发布了官方 App，还打算冰封你的代码一千年</span>
                                        </a>
                                        <a href="/posts/4d09b294/" class="list-group-item list-group-item-action">
                                            <span class="category-post">解决 Mac OS 10.14 环境下 gem install ERROR</span>
                                        </a>
                                        <a href="/posts/21edb9fa/" class="list-group-item list-group-item-action">
                                            <span class="category-post">Markdown 直接转换公众号文章，不再为排版花时间</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="category row">
                                <a
                                        class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                        id="heading-eda14e187a768b38eda999457c9cca1e" role="tab" data-toggle="collapse"
                                        href="#collapse-eda14e187a768b38eda999457c9cca1e"
                                        aria-expanded="false"
                                >
                                    Golang
                                    <i class="iconfont icon-arrowright"></i>
                                </a>
                                <a href="/categories/Golang/" class="category-count col-2 col-md-1">
                                    <i class="iconfont icon-articles"></i>
                                    <span>4</span>
                                </a>
                                <div class="category-collapse collapse" id="collapse-eda14e187a768b38eda999457c9cca1e"
                                     role="tabpanel" aria-labelledby="heading-eda14e187a768b38eda999457c9cca1e">
                                    <div class="category-post-list">
                                        <a href="/posts/753ba7a9/" class="list-group-item list-group-item-action">
                                            <span class="category-post">Crawlab Lite 正式发布，更轻量的爬虫管理平台</span>
                                        </a>
                                        <a href="/posts/dc4bf29f/" class="list-group-item list-group-item-action">
                                            <span class="category-post">源码解读 Golang 中 sync.Map 的实现原理</span>
                                        </a>
                                        <a href="/posts/14690cf1/" class="list-group-item list-group-item-action">
                                            <span class="category-post">经典面试题：青蛙跳台阶</span>
                                        </a>
                                        <a href="/posts/f94fb1be/" class="list-group-item list-group-item-action">
                                            <span class="category-post">探究 Go 语言 defer 语句的三种机制</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="category row">
                                <a
                                        class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                        id="heading-686155af75a60a0f6e9d80c1f7edd3e9" role="tab" data-toggle="collapse"
                                        href="#collapse-686155af75a60a0f6e9d80c1f7edd3e9"
                                        aria-expanded="false"
                                >
                                    JavaScript
                                    <i class="iconfont icon-arrowright"></i>
                                </a>
                                <a href="/categories/JavaScript/" class="category-count col-2 col-md-1">
                                    <i class="iconfont icon-articles"></i>
                                    <span>1</span>
                                </a>
                                <div class="category-collapse collapse" id="collapse-686155af75a60a0f6e9d80c1f7edd3e9"
                                     role="tabpanel" aria-labelledby="heading-686155af75a60a0f6e9d80c1f7edd3e9">
                                    <div class="category-post-list">
                                        <a href="/posts/8f4616c6/" class="list-group-item list-group-item-action">
                                            <span class="category-post">Node.js 使用 express-jwt 解析 JWT</span>
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>

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
            "分类"
        ],
        speed: 150,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>