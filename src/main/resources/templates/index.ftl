<!DOCTYPE html>
<html lang="zh-CN" data-default-color-scheme=auto>


<head>
    <title>${config.name}</title>
    <link rel="icon" href="${config.favicon}">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="theme-color" content="#2f4154">
    <meta name="author" content="${author.name}">
    <meta name="keywords" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta name="description" content="${author.description}">
    <meta property="og:type" content="website">
    <meta property="og:title" content="${config.name}">
    <meta property="og:url" content="${config.href}/index.html">
    <meta property="og:site_name" content="${config.name}">
    <meta property="og:description" content="${author.description}">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:author" content="${author.name}">
    <meta property="article:tag" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta name="twitter:card" content="summary_large_image">
    <#include "./layout/head.ftl" />
</head>
<body>
<header style="height: 100vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('${config.background}') center center / cover no-repeat; transform: translate3d(0px, 0px, 0px);">
        <div class="full-bg-img">
            <div class="mask flex-center" style="background-color: rgba(0, 0, 0, 0.3)">
                <div class="page-header text-center fade-in-up">
            <span class="h2" id="subtitle">
            </span>
                </div>
                <div class="scroll-down-bar">
                    <i class="iconfont icon-arrowdown"></i>
                </div>
            </div>
        </div>
    </div>
</header>
<main>

    <div class="container nopadding-x-md">
        <div class="py-5" id="board"
             style=margin-top:0>
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-10 m-auto">
                        <!-- 文章列表 -->
                        <#list page.records as article>
                            <div class="row mx-auto index-card">
                                <div class="col-12 col-md-4 m-auto index-img">
                                    <a href="/article/${article.identification}.html" target="_self">
                                        <img src="${article.image}" class="article-img" lazyload alt="${article.title}">
                                    </a>
                                </div>
                                <article class="col-12 col-md-8 mx-auto index-info">
                                    <h1 class="index-header">
                                        <#if article.sequence == 1>
                                            <i class="index-pin iconfont icon-top" title="Pin on top"></i>
                                        </#if>
                                        <a href="/article/${article.identification}.html" target="_self">
                                            ${article.title}
                                        </a>
                                    </h1>
                                    <p class="index-excerpt">
                                        <a href="/article/${article.identification}.html" target="_self">
                                            ${article.description}
                                        </a>
                                    </p>
                                    <div class="index-btm post-metas">
                                        <div class="post-meta mr-3">
                                            <i class="iconfont icon-date"></i>
                                            <time datetime="${article.updateTime?string('yyyy-MM-dd HH:mm:ss')}"
                                                  pubdate>
                                                ${article.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                                            </time>
                                        </div>
                                        <div class="post-meta mr-3">
                                            <i class="iconfont icon-category"></i>
                                            <a href="/category/${article.categoryId}.html">${article.categoryName}</a>
                                        </div>
                                        <div class="post-meta">
                                            <i class="iconfont icon-tags"></i>
                                            <#list article.tags as tag>
                                                <a href="javascript:search_tag('${tag}')">${tag}</a>
                                            </#list>
                                        </div>
                                    </div>
                                </article>
                            </div>
                        <#else>
                            <div>暂无文章</div>
                        </#list>
                        <!-- 分页 -->

                        <nav aria-label="navigation">
                            <span class="pagination" id="pagination">
                                <#if page.pageNum - 1 &gt; 0>
                                    <a class="extend prev" rel="prev" href="index.html?pageNum=${page.pageNum - 1}&pageSize=${page.pageSize}">
                                        <i class="iconfont icon-arrowleft"></i>
                                    </a>
                                </#if>
                                <#if page.pageNum - 2 &gt; 0>
                                    <a class="page-number"
                                       href="index.html?pageNum=${page.pageNum - 2}&pageSize=${page.pageSize}">${page.pageNum - 2}</a>
                                </#if>
                                <#if page.pageNum - 1 &gt; 0>
                                    <a class="page-number"
                                       href="index.html?pageNum=${page.pageNum - 1}&pageSize=${page.pageSize}">${page.pageNum - 1}</a>
                                </#if>
                                <span class="page-number current">${page.pageNum}</span>
                                <#if page.pageNum + 1 &lt; page.count>
                                    <a class="page-number"
                                       href="index.html?pageNum=${page.pageNum + 1}&pageSize=${page.pageSize}">${page.pageNum + 1}</a>
                                </#if>
                                <#if page.pageNum + 2 &lt; page.count>
                                    <a class="page-number"
                                       href="index.html?pageNum=${page.pageNum + 2}&pageSize=${page.pageSize}">${page.pageNum + 2}</a>
                                </#if>
                                <#if page.pageNum + 1 &lt; page.count>
                                    <a class="extend next" rel="next"
                                       href="index.html?pageNum=${page.pageNum + 1}&pageSize=${page.pageSize}">
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
            "真正的大师永远怀着一颗学徒的心。",
            "任何时候都不要被骄傲蒙蔽了双眼！",
            "绝对不要坐等胜利的到来。",
            "愤怒，带来冲动。",
        ],
        speed: 150,
        loop: true,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>