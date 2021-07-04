<template>
    <el-card shadow="hover" class="article">
        <el-row>
            <el-col :span="10" class="article-img">
                <el-image
                    style="width: 100%; height: 100%"
                    :src="data.image"
                    fit="fill"
                    @click="toA('/article/' + data.id)"
                ></el-image>
            </el-col>
            <el-col :span="14" class="article-content">
                <div
                    class="article-title article-link"
                    v-text="data.title"
                    @click="toA('/article/' + data.id)"
                ></div>
                <div class="article-extra">
                    <span v-if="data.stick">
                        <span style="color: #49b1f5">
                            <span class="iconfont icon-zhiding"></span>
                            <span>置顶</span>
                        </span>
                        <span> | </span>
                    </span>
                    <span>
                        <i class="el-icon-date"></i>发表于
					</span>
                    <span v-text="data.create_time"></span>
                    <span> | </span>
                    <span>
                        <i class="el-icon-refresh"></i>更新于
					</span>
                    <span v-text="data.update_time"></span>
                    <span v-if="data.categoryId !== ''">
                        <span> | </span>
                        <span class="iconfont icon-leimupinleifenleileibie"></span>
                        <span
                            class="article-link"
                            @click="
                                toA(
                                    '/category/' +
                                        data.category_id
                                )
                            "
                        >{{ data.category_name }}</span>
                    </span>
                    <span v-if="data.tags.length > 0">
                        <span> | </span>
                        <span class="iconfont icon-biaoqian"></span>
                        <span
                            v-for="(
                                tag, index
                            ) in data.tags.split(',')"
                            :key="index"
                        >
                            <span v-if="index !== 0">·</span>
                            <span class="article-link">{{ tag }}</span>
                        </span>
                    </span>
                </div>
                <div class="article-describe" v-text="data.description"></div>
            </el-col>
        </el-row>
    </el-card>
</template>
<script>
export default {
    name: 'my-article',
    props: {
        data: Object
    },
	created() {
		console.log(this.data)
	},
	methods: {
        toA(href) {
            this.$emit("click", href);
        }
    }
}
</script>
<style>
.article {
    cursor: pointer;
}

.article-img {
    transition: all 0.6s;
}

.article:hover .article-img {
    transform: scale(1.1);
}

.article-content {
    text-align: left;
    margin-top: 8%;
    padding-left: 40px;
    width: 50%;
}

.article-title {
    font-size: 26px;
}

.article-link:hover,
.article-link:focus,
.article-link:active {
    -webkit-transition: all 0.5s linear;
    -moz-transition: all 0.5s linear;
    -o-transition: all 0.5s linear;
    transition: all 0.5s linear;
    color: #49b1f5;
}

.article-extra {
    font-size: 14px;
    color: darkgray;
    padding-top: 10px;
}

.article-describe {
    font-size: 16px;
    padding-top: 10px;
}
</style>