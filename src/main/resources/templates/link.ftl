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
    <meta property="og:title" content="友链">
    <meta property="og:url" content="${config.href}/page/link.html">
    <meta property="og:site_name" content="${config.name}">
    <meta property="og:description" content="${author.description}">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:author" content="${author.name}">
    <meta property="article:tag" content="<#list config.keywords as keyword>${keyword}<#sep>,</#sep></#list>">
    <meta name="twitter:card" content="summary_large_image">
    <meta name="generator" content="Hexo 5.4.0">
    <title>友链 - ${config.name}</title>
    <#include "./layout/head.ftl" />

</head>


<body>
<header style="height: 60vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('${config.linkBackground}') no-repeat center center;
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
                        <div class="row links">
                            <div class="card col-lg-4 col-md-6 col-sm-12">
                                <a href="https://hexo.fluid-dev.com/" class="card-body hover-with-bg" target="_blank"
                                   rel="noopener external nofollow noreferrer">
                                    <div class="card-content">
                                        <div class="link-avatar my-auto">
                                            <img src="/img/favicon.png" srcset="/img/loading.gif" lazyload
                                                 alt="Fluid Blog"
                                                 onerror="this.onerror=null; this.src=this.srcset='/img/avatar.png'"/>
                                        </div>
                                        <div class="link-text">
                                            <div class="link-title">Fluid Blog</div>
                                            <div class="link-intro">主题博客</div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="card col-lg-4 col-md-6 col-sm-12">
                                <a href="https://hexo.fluid-dev.com/docs/" class="card-body hover-with-bg"
                                   target="_blank" rel="noopener external nofollow noreferrer">
                                    <div class="card-content">
                                        <div class="link-avatar my-auto">
                                            <img src="/img/favicon.png" srcset="/img/loading.gif" lazyload
                                                 alt="Fluid Docs"
                                                 onerror="this.onerror=null; this.src=this.srcset='/img/avatar.png'"/>
                                        </div>
                                        <div class="link-text">
                                            <div class="link-title">Fluid Docs</div>
                                            <div class="link-intro">主题使用指南</div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="card col-lg-4 col-md-6 col-sm-12">
                                <a href="https://github.com/fluid-dev/hexo-theme-fluid" class="card-body hover-with-bg"
                                   target="_blank" rel="noopener external nofollow noreferrer">
                                    <div class="card-content">
                                        <div class="link-avatar my-auto">
                                            <img src="/img/favicon.png" srcset="/img/loading.gif" lazyload
                                                 alt="Fluid Repo"
                                                 onerror="this.onerror=null; this.src=this.srcset='/img/avatar.png'"/>
                                        </div>
                                        <div class="link-text">
                                            <div class="link-title">Fluid Repo</div>
                                            <div class="link-intro">主题 GitHub 仓库</div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="card col-lg-4 col-md-6 col-sm-12">
                                <a href="https://rook1e.com/" class="card-body hover-with-bg" target="_blank"
                                   rel="noopener external nofollow noreferrer">
                                    <div class="card-content">
                                        <div class="link-avatar my-auto">
                                            <img src="/img/avatar.png" srcset="/img/loading.gif" lazyload alt="Rook1e"
                                                 onerror="this.onerror=null; this.src=this.srcset='/img/avatar.png'"/>
                                        </div>
                                        <div class="link-text">
                                            <div class="link-title">Rook1e</div>
                                            <div class="link-intro">Fluid 主题开发成员</div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="card col-lg-4 col-md-6 col-sm-12">
                                <a href="https://eatrice.top/" class="card-body hover-with-bg" target="_blank"
                                   rel="noopener external nofollow noreferrer">
                                    <div class="card-content">
                                        <div class="link-avatar my-auto">
                                            <img src="/img/avatar.png" srcset="/img/loading.gif" lazyload alt="EatRice"
                                                 onerror="this.onerror=null; this.src=this.srcset='/img/avatar.png'"/>
                                        </div>

                                        <div class="link-text">
                                            <div class="link-title">EatRice</div>
                                            <div class="link-intro">一个努力的考研狗</div>
                                        </div>
                                    </div>
                                </a>
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
            "友链"
        ],
        speed: 150,
        lifeLike: true,
        startDelay: 500,
        breakLines: false,
    }).go();
</script>
</html>