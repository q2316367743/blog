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
    <meta name="generator" content="blog 1.0">
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
                            <#list categories as category>
                                <div class="category row">
                                    <a
                                            class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                            id="heading-${category.id}" role="tab" data-toggle="collapse"
                                            href="#collapse-${category.id}"
                                            aria-expanded="false"
                                    >
                                        ${category.name}
                                        <i class="iconfont icon-arrowright"></i>
                                    </a>
                                    <a href="${config.href}/category/${category.id}.html"
                                       class="category-count col-2 col-md-1">
                                        <i class="iconfont icon-articles"></i>
                                        <span>${category.count}</span>
                                    </a>
                                    <div class="category-collapse collapse" id="collapse-${category.id}"
                                         role="tabpanel" aria-labelledby="heading-${category.id}">
                                        <div class="category-post-list">
                                            <#list category.articles as article>
                                                <a href="${config.href}/article/${article.id}.html"
                                                   class="list-group-item list-group-item-action">
                                                    <span class="category-post">${article.title}</span>
                                                </a>
                                            </#list>
                                            <#if category.count &gt; 10>
                                                <a href="${config.href}/category/${category.id}.html"
                                                   class="list-group-item list-group-item-action">
                                                    <span class="category-post">More...</span>
                                                </a>
                                            </#if>
                                        </div>
                                    </div>
                                </div>
                                <#list category.children as child>
                                    <div class="category row">
                                        <a
                                                class="category-item collapsed list-group-item category-item-action col-10 col-md-11"
                                                id="heading-${child.id}" role="tab" data-toggle="collapse"
                                                href="#collapse-${child.id}"
                                                aria-expanded="false"
                                        >
                                            ${child.name}
                                            <i class="iconfont icon-arrowright"></i>
                                        </a>
                                        <a href="${config.href}/category/${child.id}.html"
                                           class="category-count col-2 col-md-1">
                                            <i class="iconfont icon-articles"></i>
                                            <span>${child.count}</span>
                                        </a>
                                        <div class="category-collapse collapse" id="collapse-${child.id}"
                                             role="tabpanel" aria-labelledby="heading-${child.id}">
                                            <div class="category-post-list">
                                                <#list child.articles as article>
                                                    <a href="${config.href}/article/${article.id}.html"
                                                       class="list-group-item list-group-item-action">
                                                        <span class="category-post">${article.title}</span>
                                                    </a>
                                                </#list>
                                                <#if child.count &gt; 10>
                                                    <a href="${config.href}/category/${child.id}.html"
                                                       class="list-group-item list-group-item-action">
                                                        <span class="category-post">More...</span>
                                                    </a>
                                                </#if>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#list>
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