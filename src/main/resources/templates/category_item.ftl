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
    <meta name="generator" content="blog 1.0">
    <title>归档 - ${config.name}</title>
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
                        <div class="list-group">
                            <p class="h4">共计 ${page.total} 篇文章</p>
                            <hr>
                            <#list page.records as view>
                                <p class="h5">${view.year}</p>
                                <#list view.items as article>
                                    <a href="${config.href}/article/${article.id}.html" class="list-group-item list-group-item-action">
                                        <span class="archive-post-title">${article.title}</span>
                                        <time style="float: right;">${article.month}-${article.day}</time>
                                    </a>
                                </#list>
                            </#list>
                        </div>
                        <nav aria-label="navigation">
                            <span class="pagination" id="pagination">
                                <#if page.pageNum - 1 &gt; 0>
                                    <a class="extend prev" rel="prev" href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum - 1}&pageSize=${page.pageSize}">
                                        <i class="iconfont icon-arrowleft"></i>
                                    </a>
                                </#if>
                                <#if page.pageNum - 2 &gt; 0>
                                    <a class="page-number"
                                       href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum - 2}&pageSize=${page.pageSize}">${page.pageNum - 2}</a>
                                </#if>
                                <#if page.pageNum - 1 &gt; 0>
                                    <a class="page-number"
                                       href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum - 1}&pageSize=${page.pageSize}">${page.pageNum - 1}</a>
                                </#if>
                                <span class="page-number current">${page.pageNum}</span>
                                <#if page.pageNum + 1 &lt; page.count>
                                    <a class="page-number"
                                       href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum + 1}&pageSize=${page.pageSize}">${page.pageNum + 1}</a>
                                </#if>
                                <#if page.pageNum + 2 &lt; page.count>
                                    <a class="page-number"
                                       href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum + 2}&pageSize=${page.pageSize}">${page.pageNum + 2}</a>
                                </#if>
                                <#if page.pageNum + 1 &lt; page.count>
                                    <a class="extend next" rel="next"
                                       href="${config.href}/category/${category.id}.html?pageNum=${page.pageNum + 1}&pageSize=${page.pageSize}">
                                        <i class="iconfont icon-arrowright"></i>
                                    </a>
                                </#if>
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
            "分类 - ${category.getName()}"
        ],
        speed: 150,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>