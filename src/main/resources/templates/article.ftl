<!DOCTYPE html>
<html lang="zh-CN" data-default-color-scheme=auto>


<head>
    <title>${article.title}</title>
    <link rel="icon" href="${config.favicon}">
    <meta charset="UTF-8">
    <link rel="apple-touch-icon" sizes="76x76" href="${config.favicon}">
    <meta property="og:site_name" content="${config.name}">
    <meta property="og:image" content="${config.favicon}">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta name="theme-color" content="#2f4154">
    <meta name="description" content="${author.description}">
    <meta name="author" content="${author.name}">
    <meta name="keywords" content="<#list article.tags as tag>${tag}<#sep>,</#sep></#list>">
    <meta name="description" content="${article.description}">
    <meta property="og:type" content="article">
    <meta property="og:title" content="${article.title}">
    <meta property="og:url" content="${config.href}/article/${article.id}.html">
    <meta property="og:description" content="${article.description}">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:published_time" content="${article.createTime?string('yyyy-MM-dd HH:mm:ss')}">
    <meta property="article:modified_time" content="${article.updateTime?string('yyyy-MM-dd HH:mm:ss')}">
    <meta property="article:author" content="${author.name}">
    <meta property="article:tag" content="<#list article.tags as tag>${tag}<#sep>,</#sep></#list>">
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:image" content="${article.image}">
    <#include "./layout/head.ftl" />
</head>
<body>
<header style="height: 70vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('<#if article.image?length == 0>${config.pageBackground}<#else>${article.image}</#if>') no-repeat center center;
                 background-size: cover;">
        <div class="full-bg-img">
            <div class="mask flex-center" style="background-color: rgba(0, 0, 0, 0.5)">
                <div class="page-header text-center fade-in-up">
                    <span class="h2" id="subtitle"></span>
                    <div class="mt-3">
                        <span class="post-meta">
                              <i class="iconfont icon-date-fill" aria-hidden="true"></i>
                              <time datetime="${article.createTime?string('yyyy-MM-dd HH:mm:ss')}" pubdate>
                                  ${article.createTime?string('yyyy年MM月dd日 HH时mm分')}
                              </time>
                        </span>
                    </div>
                    <div class="mt-1">
                        <span class="post-meta mr-2">
                            <i class="iconfont icon-chart"></i>
                            <span> ${article.wordCount}k 字</span>
                        </span>
                        <span class="post-meta mr-2">
                            <i class="iconfont icon-clock-fill"></i>
                            <span>${article.readTime} 分钟</span>
                        </span>
                        <!-- LeanCloud 统计文章PV -->
                        <span id="leancloud-page-views-container" class="post-meta" style="display: none">
                        <i class="iconfont icon-eye" aria-hidden="true"></i>
                            <span id="leancloud-page-views">${article.viewCount}</span> 次
                      </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<main>
    <div class="container-fluid nopadding-x">
        <div class="row nomargin-x">
            <div class="d-none d-lg-block col-lg-2"></div>
            <div class="col-lg-8 nopadding-x-md">
                <div class="container nopadding-x-md" id="board-ctn">
                    <div class="py-5" id="board">
                        <article class="post-content mx-auto">
                            <!-- SEO header -->
                            <h1 style="display: none">${article.title}</h1>
                            <div class="markdown-body" id="markdown-body">${article.content!'这篇文章没有内容'}</div>
                            <hr>
                            <div>
                                <div class="post-metas mb-3">
                                    <div class="post-meta mr-3">
                                        <i class="iconfont icon-category"></i>
                                        <a class="hover-with-bg"
                                           href="/category/${article.categoryId}.html">${article.categoryName}</a>
                                    </div>
                                    <#if article.tags?size &gt; 0>
                                        <div class="post-meta">
                                            <i class="iconfont icon-tags"></i>
                                            <#list article.tags as tag>
                                                <a class="hover-with-bg"
                                                   href="javascript:search_tag('${tag}')">${tag}</a>
                                            </#list>
                                        </div>
                                    </#if>
                                </div>
                                <p class="note note-warning">
                                    本博客所有文章除特别声明外，均采用 <a target="_blank"
                                                         href="https://creativecommons.org/licenses/by-sa/4.0/deed.zh"
                                                         rel="nofollow noopener noopener">CC BY-SA 4.0 协议</a> ，转载请注明出处！
                                </p>
                                <div class="post-prevnext">
                                    <article class="post-prev col-6">
                                        <#if before??>
                                            <a href="${config.href}/article/${before.id}.html">
                                                <i class="iconfont icon-arrowleft"></i>
                                                <span class="hidden-mobile">${before.title}</span>
                                                <span class="visible-mobile">上一篇</span>
                                            </a>
                                        </#if>
                                    </article>
                                    <article class="post-next col-6">
                                        <#if after??>
                                            <a href="${config.href}/article/${after.id}.html">
                                                <span class="hidden-mobile">${after.title}</span>
                                                <span class="visible-mobile">下一篇</span>
                                                <i class="iconfont icon-arrowright"></i>
                                            </a>
                                        </#if>
                                    </article>
                                </div>
                            </div>
                            <#assign id=article.id />
                            <#assign type=1 />
                            <#include "./layout/comment.ftl" />
                            </article>
                    </div>
                </div>
            </div>
            <div class="d-none d-lg-block col-lg-2 toc-container" id="toc-ctn" style="padding-top: 60px;">
                <div id="toc" style="visibility: visible;display: none">
                    <p class="toc-header"><i class="iconfont icon-list"></i>&nbsp;目录</p>
                    <div class="toc-body" id="toc-body">
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
            "${article.title}"
        ],
        speed: 150,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>