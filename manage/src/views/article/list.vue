<template>
    <div class="app-container">
        <div class="el-card is-always-shadow">
            <div class="el-card__body article-list-condition">
                <div>文章标题：</div>
                <el-input style="width: 180px" v-model="condition.title"></el-input>
                <el-button @click="search" type="primary" style="margin-left: 10px">搜索</el-button>
                <el-button @click="clear" style="margin-left: 10px">重置</el-button>
            </div>
        </div>
        <el-card style="margin-top: 20px" shadow="always">
            <div>
                <el-button style="float: right" type="primary" @click="save">新增</el-button>
            </div>
            <el-table :data="articles">
                <el-table-column type="index" label="序号"></el-table-column>
                <el-table-column prop="title" label="博客标题"></el-table-column>
                <el-table-column prop="category_name" label="分类"></el-table-column>
                <el-table-column label="状态">
                    <template slot-scope="scope">
                        <el-tag type="info" v-if="scope.row.status === 0">草稿</el-tag>
                        <el-tag type="success" v-else-if="scope.row.status === 1">已发布</el-tag>
                        <el-tag type="danger" v-else-if="scope.row.status === -1">回收站</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="comment_count" label="评论数"></el-table-column>
                <el-table-column prop="view_count" label="浏览量"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="text" @click="update(scope.row.id)">编辑</el-button>
                        <el-button type="text">回收站</el-button>
                        <el-button type="text">设置</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
import article_api from '@/api/article';

export default {
    name: "articleList",
    data: () => {
        return {
            page_num: 1,
            page_size: 10,
            total: 1,
            condition: {
                title: '',
                is_delete: false,
                is_release: true
            },
            articles: []
        }
    },
    created() {
        this.search();
    },
    methods: {
        search() {
            article_api.list({
                page_num: this.page_num,
                page_size: this.page_size,
                title: this.condition.title
            }, res => {
                console.log(res)
                if (res.code === 200) {
                    this.articles = res.data.records;
                } else {
                    this.$message({
                        message: '获取文章失败',
                        type: "error"
                    })
                }
            })
        },
        clear() {
            this.page_num = 1;
            this.page_size = 10;
            this.condition.title = '';
        },
        save() {
            this.$router.push('/article/save');
        },
        update(id) {
            this.$router.push({
                path: '/article/update',
                query: {
                    id: id
                }
            });
        }
    }
}
</script>

<style scoped>
.article-list-condition {
    display: flex;
    line-height: 40px;
}
</style>
