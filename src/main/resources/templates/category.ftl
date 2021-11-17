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
                        分类列表
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