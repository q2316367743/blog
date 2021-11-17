<!DOCTYPE html>
<html lang="zh-CN" data-default-color-scheme=auto>


<head>
    <title>云落天都的网络日志</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="theme-color" content="#2f4154">
    <meta name="description" content="An elegant Material-Design theme for Hexo">
    <meta name="author" content="Fluid">
    <meta name="keywords" content="hexo,theme,fluid,material,material-design,blog">
    <meta name="description" content="An elegant Material-Design theme for Hexo">
    <meta property="og:type" content="website">
    <meta property="og:title" content="Hexo Theme Fluid">
    <meta property="og:url" content="https://fluid-dev.cn/index.html">
    <meta property="og:site_name" content="Hexo Theme Fluid">
    <meta property="og:description" content="An elegant Material-Design theme for Hexo">
    <meta property="og:locale" content="zh_CN">
    <meta property="article:author" content="Fluid">
    <meta property="article:tag" content="hexo,theme,fluid,material,material-design,blog">
    <meta name="twitter:card" content="summary_large_image">
    <#include "./layout/head.ftl" />
</head>
<body>
<header style="height: 100vh;">
    <#include "./layout/menu.ftl" />
    <div class="banner" id="banner" parallax=true
         style="background: url('https://static.zkqiang.cn/images/20191231163321.jpg-slim') center center / cover no-repeat; transform: translate3d(0px, 0px, 0px);">
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
                        <div class="row mx-auto index-card">
                            <div class="col-12 col-md-4 m-auto index-img">
                                <a href="/posts/hello-fluid/" target="_self">
                                    <img src="https://rmt.dogedoge.com/fetch/fluid/storage/hello-fluid/cover.png?w=480&amp;fmt=webp"
                                         srcset="/img/loading.gif" lazyload alt="Hello Fluid">
                                </a>
                            </div>
                            <article class="col-12 col-md-8 mx-auto index-info">
                                <h1 class="index-header">

                                    <i class="index-pin iconfont icon-top" title="Pin on top"></i>

                                    <a href="/posts/hello-fluid/" target="_self">
                                        Hello Fluid
                                    </a>
                                </h1>
                                <p class="index-excerpt">
                                    <a href="/posts/hello-fluid/" target="_self">
                                        欢迎体验 Fluid ，这是一款 Material Design 风格的 Hexo 主题，以简约的设计帮助你专注于写作，本篇文章可预览主题的样式及功能。
                                    </a>
                                </p>
                                <div class="index-btm post-metas">
                                    <div class="post-meta mr-3">
                                        <i class="iconfont icon-date"></i>
                                        <time datetime="2020-04-22 22:22" pubdate>
                                            2020-04-22
                                        </time>
                                    </div>
                                    <div class="post-meta mr-3">
                                        <i class="iconfont icon-category"></i>
                                        <a href="/categories/%E4%B8%BB%E9%A2%98%E7%A4%BA%E4%BE%8B/">主题示例</a>
                                    </div>
                                    <div class="post-meta">
                                        <i class="iconfont icon-tags"></i>
                                        <a href="/tags/%E7%A4%BA%E4%BE%8B/">示例</a>
                                        <a href="/tags/Fluid/">Fluid</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                        <!-- 分页 -->
                        <nav aria-label="navigation">
                            <span class="pagination" id="pagination">
                              <span class="page-number current">1</span>
                                <a class="page-number" href="/page/2/#board">2</a>
                                <a class="extend next" rel="next" href="/page/2/#board">
                                    <i class="iconfont icon-arrowright"></i>
                                </a>
                            </span>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 返回顶部 -->
    <a id="scroll-top-button" aria-label="TOP" href="#" role="button">
        <i class="iconfont icon-arrowup" aria-hidden="true"></i>
    </a>

    <!-- 搜索菜单 -->
    <div class="modal fade" id="modalSearch" tabindex="-1" role="dialog" aria-labelledby="ModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">搜索</h4>
                    <button type="button" id="local-search-close" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <input type="text" id="local-search-input" class="form-control validate">
                        <label data-error="x" data-success="v"
                               for="local-search-input">关键词</label>
                    </div>
                    <div class="list-group" id="local-search-result"></div>
                </div>
            </div>
        </div>
    </div>
</main>
<#include "./layout/footer.ftl" />

</body>
<!-- SCRIPTS -->
<script src="https://cdn.jsdelivr.net/npm/nprogress@0/nprogress.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nprogress@0/nprogress.min.css"/>
<script>
    NProgress.configure({"showSpinner": false, "trickleSpeed": 100})
    NProgress.start()
    window.addEventListener('load', function () {
        NProgress.done();
    })
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/typeit/7.0.4/typeit.min.js"></script>
<script  src="/js/events.js" ></script>
<script  src="/js/plugins.js" ></script>
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