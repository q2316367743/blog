<div>
    <el-card shadow="hover" class="card" style="text-align: center">
        <div>
            <el-avatar :size="100" src="${author.avatar}"></el-avatar>
        </div>
        <div class="author-name">${author.name}</div>
        <div class="author-describe">${author.description}</div>
        <div class="author-web">
            <el-row type="flex" justify="space-between">
                <el-col :span="12">
                    <a href="/shijianzhou">
                        <div>文章</div>
                        <div style="margin-top: 5px">${website.articleCount}</div>
                    </a>
                </el-col>
                <el-col :span="12">
                    <a href="/category">
                        <div>分类</div>
                        <div style="margin-top: 5px">${website.categoryCount}</div>
                    </a>
                </el-col>
            </el-row>
        </div>
        <div class="author-gitee" @click="toTarget('${author.gitee}')">
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-mayun"></use>
            </svg>
            <span>&emsp;我的Gitee</span>
        </div>
        <div class="author-other">
            <#list author.other as other>
                <img
                        src="${other.icon}"
                        alt="${other.name}"
                        title="${other.name}"
                        @click="toTarget('${other.href}')"
                />
            </#list>
        </div>
    </el-card>
    <el-card shadow="hover" class="card">
        <div class="card-title">
            <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-gonggao"></use>
            </svg>
            <span>公告</span>
        </div>
        <div class="card-content">
            <#list notices as notice>
                <div @click="open_notice('${notice.content}')" style="cursor: pointer">
                    ${notice.title}
                    <el-popover
                            placement="top-start"
                            title="${notice.title}"
                            width="200"
                            trigger="hover"
                            content="${notice.content}"
                    ></el-popover>
                </div>
            </#list>
        </div>
    </el-card>
    <el-card shadow="hover" class="card">
        <div class="card-title">
            <i class="iconfont icon-biaoqian my-icon"></i>
            <span>分类</span>
        </div>
        <div class="card-content">
            <#list categories as category>
                <div class="category" @click="toA('/category/${category.id}')">
                    <div>${category.name}</div>
                    <div>${category.count}</div>
                </div>
            </#list>
        </div>
    </el-card>
    <el-card shadow="hover" class="card">
        <div class="card-title">
            <i class="my-icon el-icon-info"></i>
            <span>网站资讯</span>
        </div>
        <div class="card-content">
            <div class="info-item">
                <div>文章数目：</div>
                <div>${website.articleCount}</div>
            </div>
            <div class="info-item">
                <div>以运行时间：</div>
                <div>${website.runTime}&emsp;天</div>
            </div>
            <div class="info-item">
                <div>文章总字数：</div>
                <div>${website.wordCount}&emsp;K</div>
            </div>
            <div class="info-item">
                <div>本站总访问量：</div>
                <div>${website.accessCount}</div>
            </div>
            <div class="info-item">
                <div>最后更新时间：</div>
                <div>${website.lastUpdate?string('yyyy-MM-dd HH:mm:ss')}</div>
            </div>
        </div>
    </el-card>
</div>