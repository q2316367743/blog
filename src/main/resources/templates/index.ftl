<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8"/>
    <title>云落天都的博客</title>
    <meta name="keywords" content="个人博客,网络日志,云落天都">
    <meta name="description" content="云落天都的博客">
    <meta name="author" content="云落天都">
    <meta name="application-name" content="博客">
    <script src="https://cdn.bootcdn.net/ajax/libs/vue/2.6.11/vue.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/axios/0.21.1/axios.min.js"></script>
    <script src="https://unpkg.com/element-ui@2.15.6/lib/index.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/element-ui@2.15.6/lib/theme-chalk/index.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/typeit/7.0.4/typeit.min.js"></script>
    <script src="util/time.js"></script>
    <script src="util/window.js"></script>
    <link rel="stylesheet" href="css/main.css"/>
    <link rel="stylesheet" href="css/index.css">
    <script src="icon/iconfont.js"></script>
    <link rel="stylesheet" href="icon/iconfont.css"/>
    <link rel="stylesheet" href="css/article_list.css">
    <link rel="stylesheet" href="css/broadside.css">
    <style>
        #welcome {
            background: rgba(0, 0, 0, 0) url("${config.background}") repeat scroll 0% 0%;
        }
    </style>
</head>
<body>
<div id="app" @scroll="appScroll($event)">
    <div :class="{ topT: isT, topS: !isT }" v-show="showTop">
        <div
                style="
					display: flex;
					flex-flow: row nowrap;
					justify-content: space-between;
				"
        >
            <div>
                <div class="logo" :class="{ 'nav-item-a': scrollOver }">
                    <a href="/">云落天都的博客</a>
                </div>
            </div>
            <div v-show="!isSm">
                <ul class="nav" :class="{ 'nav-item-a': scrollOver }">
                    <li class="nav-item" @click="search_click">
                        <i class="el-icon-search" style="color: #1abc9c;font-weight: bold;font-size: 16px;"></i>
                        <span>搜索</span>
                    </li>
                    <li class="nav-item" to="/">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-zhuye"></use>
                        </svg>
                        <span>首页</span>
                    </li>
                    <li class="nav-item" to="/shijianzhou">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-shijianzhou"></use>
                        </svg>
                        <a href="javascript:void(0);"> 时间轴</a>
                    </li>
                    <li class="nav-item" to="/category">
                        <svg class="icon" aria-hidden="true">
                            <use
                                    xlink:href="#icon-leimupinleifenleileibie"
                            ></use>
                        </svg>
                        <a href="javascript:void(0);"> 分类</a>
                    </li>
                    <li class="nav-item" to="/about">
                        <svg
                                class="icon"
                                aria-hidden="true"
                                style="font-size: 18px"
                        >
                            <use xlink:href="#icon-mingpian-copy"></use>
                        </svg>
                        <a href="javascript:void(0);"> 关于作者</a>
                    </li>
                </ul>
            </div>
            <div v-show="isSm" style="padding: 0 20px;cursor: pointer">
                <i
                        class="el-icon-menu"
                        style="font-size: 20px;"
                        @click="openMenu"
                ></i>
            </div>
        </div>
    </div>
    <div class="main">
        <div id="welcome">
            <div id="main">
                <div id="title">你好</div>
                <p id="subtitle"></p>
            </div>
            <div id="down">
                <i
                        class="el-icon-arrow-down"
                        @click="downMain"
                ></i>
            </div>
        </div>
        <el-row style="margin-top: 20px">
            <el-col :span="5" :xs="2" :sm="3" :md="4" :lg="4" :xl="5" style="min-height:1px;">
            </el-col>
            <el-col :span="14" :xs="20" :sm="18" :md="16" :lg="16" :xl="14">
                <el-row :gutter="20">
                    <el-col
                            :xs="24"
                            :sm="10"
                            :md="10"
                            :lg="7"
                            :xl="6"
                            :span="6"
                    >
                        <#include "/components/broadside.ftl" />
                    </el-col>
                    <el-col
                            :xs="24"
                            :sm="14"
                            :md="14"
                            :lg="17"
                            :xl="18"
                            :span="18"
                    >
                        <#list page.records as item>
                            <#include "/components/article_list.ftl" />
                        <#else>
                            <el-card shadow="hover" class="item">
                                <div style="text-align: center;line-height: 242px;font-size: 36px;">
                                    没有文章
                                </div>
                            </el-card>
                        </#list>
                        <el-pagination
                                background
                                layout="prev, pager, next"
                                :total="total"
                                class="pagination"
                                @current-change="currentChange"
                        >
                        </el-pagination>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
    </div>

    <!-- 底部 -->
    <div id="fix-bar"></div>
    <el-col :span="24" class="footer">
        <div>©2020 - 2021 By Esion</div>
    </el-col>

    <!-- 回到顶部 -->
    <el-backtop
            target="#app"
            :bottom="40"
            :right="8"
            class="back-top"
    >
        <div class="back_top">
            <i class="el-icon-top"></i>
        </div>
    </el-backtop>
    <!-- 设置音乐显示 -->
    <el-backtop
            target="#app"
            :bottom="80"
            :right="8"
            class="back-top"
            @click="isShowMusic()">
        <div class="back_top">
            <span class="iconfont icon-yinle"></span>
        </div>
    </el-backtop>

    <!-- 侧边菜单 -->
    <el-drawer
            :visible.sync="menuShow"
            direction="rtl"
            :show-close="false"
            size="250"
    >
        <div style="width: 205px; text-align: left" v-if="menuShow">
            <div style="margin-top: 10%; margin-bottom: 100px">
                <ul class="nav" :class="{ 'nav-item-a': scrollOver }">
                    <li class="nav-item" @click="search_click">
                        <i class="el-icon-search" style="color: #1abc9c;font-weight: bold;font-size: 16px;"></i>
                        <span>搜索</span>
                    </li>
                    <br/>
                    <li class="nav-item" to="/">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-zhuye"></use>
                        </svg>
                        <span>首页</span>
                    </li>
                    <li class="nav-item" to="/shijianzhou">
                        <svg class="icon" aria-hidden="true">
                            <use xlink:href="#icon-shijianzhou"></use>
                        </svg>
                        <a href="javascript:void(0);"> 时间轴</a>
                    </li>
                    <li class="nav-item" to="/tag">
                        <svg
                                class="icon"
                                aria-hidden="true"
                                style="font-size: 16px"
                        >
                            <use xlink:href="#icon-biaoqian"></use>
                        </svg>
                        <a href="javascript:void(0);"> 标签</a>
                    </li>
                    <li class="nav-item" to="/category">
                        <svg class="icon" aria-hidden="true">
                            <use
                                    xlink:href="#icon-leimupinleifenleileibie"
                            ></use>
                        </svg>
                        <a href="javascript:void(0);"> 分类</a>
                    </li>
                    <li class="nav-item" to="/about">
                        <svg
                                class="icon"
                                aria-hidden="true"
                                style="font-size: 18px"
                        >
                            <use xlink:href="#icon-mingpian-copy"></use>
                        </svg>
                        <a href="javascript:void(0);"> 关于作者</a>
                    </li>
                </ul>
            </div>
        </div>
    </el-drawer>
    <div class="search-main" v-if="is_search">
        <div class="search-background" @click="is_search = false"></div>
        <div class="search-input">
            <el-input ref="search" style="max-width: 500px" placeholder="请输入文章标题" @keyup.enter.native="search"
                      v-model="title"></el-input>
        </div>
    </div>
</div>
</body>
<script>
    const vue = new Vue({
        el: "#app",
        data: {
            audio: null,
            scrollTop: 0,
            targetTop: true,
            isTop: true,
            isT: true,
            showTop: true,
            scrollOver: false,
            isSm: false,
            menuShow: false,
            baseInfo: {
                background: "",
                music: [],
            },
            showMusic: true,
            // 是否搜索
            is_search: false,
            title: '',
            winWidth: 0,
            winHeight: 0,
            page: 0,
            limit: 5,
            total: 0,
            articles: [],
        },
        mounted() {
            let that = this;
            // 增加事件
            $(".nav-item").on("click", function (e) {
                if ($(e.currentTarget).attr("to")) {
                    window.location.href = $(e.currentTarget).attr("to");
                }
            });
            this.getWindow();
            window.onresize = this.getWindow;
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
        },
        watch: {
            $route() {
                // 返回顶部
                $("#app").animate({scrollTop: "0px"}, 800);
            },
        },
        methods: {
            appScroll(e) {
                this.isTop = e.target.scrollTop === 0;
                this.targetTop = e.target.scrollTop - this.scrollTop < 0;
                this.scrollTop = e.target.scrollTop;
                this.scrollOver = e.target.scrollTop > 60;
                if (this.scrollOver) {
                    if (this.targetTop) {
                        if (!this.isTop) {
                            this.showTop = true;
                        }
                    } else {
                        this.showTop = false;
                    }
                    this.isT = false;
                } else {
                    this.showTop = true;
                    this.isT = true;
                }
            },
            openMenu() {
                this.menuShow = true;
                let that = this;
                setTimeout(() => {
                    $(".nav-item").on("click", function (e) {
                        that.menuShow = false;
                        if ($(e.currentTarget).attr("to")) {
                            window.location.href = $(e.currentTarget).attr("to");
                        }
                    });
                });
            },
            isShowMusic() {
                let fix_bar = $("#fix-bar");
                this.showMusic = !this.showMusic;
                this.showMusic ? fix_bar.show() : fix_bar.hide();
            },
            search_click() {
                this.is_search = true
                this.$nextTick(() => {
                    this.$refs.search.focus();
                })
            },
            search() {
                this.is_search = false;
                let title = this.title;
                this.title = '';
                window.location.href = '/search?title=' + title;
            },
            getWindow() {
                findDimensions((winWidth, winHeight) => {
                    this.winWidth = winWidth;
                    this.winHeight = winHeight;
                });
                let welcome = $("#welcome");
                welcome.css("width", this.winWidth);
                welcome.css("height", this.winHeight);
                let main = $("#main");
                main.css("width", this.winWidth);
                main.css("padding-top", this.winHeight / 2 - 92);
                let down = $("#down");
                down.css("width", this.winWidth);
                down.css("padding-top", this.winHeight / 2 - 115);

                this.isSm = this.winWidth < 768;
            },
            downMain() {
                // 下滑
                $("#app").animate({scrollTop: this.winHeight + "px"}, 800);
            },
            currentChange(page) {
                this.page = page;
                this.getArticle();
            },
            toA(href) {
                window.location.href = href;
            },
            toTarget(target) {
                window.open(target);
            },
            open_notice(content){
                this.$alert(content, '通知内容', {
                    dangerouslyUseHTMLString: true
                });
            }
        },
    })
</script>
</html>