<el-card shadow="hover" class="article" @click="toA('/article/${item.id}')">
    <el-row>
        <el-col :span="10" class="article-img">
            <el-image
                    style="width: 100%; height: 100%"
                    src="${item.image}"
                    fit="fill"
                    @click="toA('/article/' + data.id)"
            ></el-image>
        </el-col>
        <el-col :span="14" class="article-content">
            <div
                    class="article-title article-link"
                    @click="toA('/article/' + data.id)"
            >${item.title}</div>
            <div class="article-extra">
                <#if item.sequence == 1>
                    <span style="color: #49b1f5">
                            <span class="iconfont icon-zhiding"></span>
                            <span>置顶</span>
                        </span>
                    <span> | </span>
                </#if>
                <span>
                        <i class="el-icon-date"></i>发表于
					</span>
                <span>${item.createTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                <span> | </span>
                <span>
                        <i class="el-icon-refresh"></i>更新于
					</span>
                <span>${item.updateTime?string('yyyy-MM-dd HH:mm:ss')}</span>
                <span> | </span>
                <span class="iconfont icon-leimupinleifenleileibie"></span>
                <span
                        class="article-link"
                        @click="
                                toA('/category/${item.categoryId}')
                            "
                >${item.categoryName}</span>
                <#list item.tags>
                    <span> | </span>
                    <span class="iconfont icon-biaoqian"></span>
                    <#items as tagItem>
                        <span class="article-link">${tagItem}</span>
                        <#sep><span>·</span></#sep>
                    </#items>
                </#list>
            </div>
            <div class="article-describe">${item.description}</div>
        </el-col>
    </el-row>
</el-card>