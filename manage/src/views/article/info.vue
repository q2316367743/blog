<template>
    <div class="el-card is-always-shadow" :class="is_full ? 'article-info-fixed' : 'article-info-abs'">
        <div class="el-card__header">
            <div class="article-info-header">
                <el-input v-model="article.title" style="padding-right: 10px" placeholder="请输入文章标题"></el-input>
                <el-select v-model="article.type" placeholder="编辑器类型" style="padding-right: 10px">
                    <el-option
                        label="markdown"
                        :value="1">
                    </el-option>
                    <el-option
                        label="富文本"
                        :value="2">
                    </el-option>
                    <el-option
                        label="html代码"
                        :value="3">
                    </el-option>
                </el-select>
                <el-button type="danger" @click="article.status = 0;publish_dialog = true">保存草稿</el-button>
                <el-button>预览</el-button>
                <el-button type="primary" @click="article.status = 1;publish_dialog = true">发布</el-button>
                <el-button @click="is_full = !is_full">全屏编辑</el-button>
            </div>
        </div>
        <div class="article-info-body">
            <editor ref="editor" :type="article.type" :original_content="article.original_content"></editor>
        </div>
        <el-drawer
            :title="article.status === 1 ? '发布' : '保存草稿'"
            :visible.sync="publish_dialog"
        >
            <el-form ref="form" :model="article" label-width="80px" style="padding: 15px">
                <div style="margin-left: 80px">
                    <el-upload
                        action="https://jsonplaceholder.typicode.com/posts/"
                        :show-file-list="false">
                        <el-image
                            :src="article.image">
                            <div slot="error" class="image-slot">
                                <el-image
                                    src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg"></el-image>
                            </div>
                        </el-image>
                    </el-upload>
                </div>
                <el-form-item label="图片">
                    <el-input v-model="article.image"></el-input>
                </el-form-item>
                <el-form-item label="文章标签">
                    <el-select
                        v-model="article.tags"
                        multiple
                        filterable
                        allow-create
                        default-first-option
                        placeholder="请选择文章标签">
                        <el-option
                            v-for="(tag, index) in tags"
                            :key="index"
                            :label="tag"
                            :value="tag">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="是否置顶">
                    <el-switch v-model="article.is_top"></el-switch>
                </el-form-item>
                <el-form-item label="文章描述">
                    <el-input type="textarea" v-model="article.description" :rows="5"
                              placeholder=""></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submit">立即{{ is_save ? '创建' : '修改' }}</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
    </div>
</template>

<script>
import editor from '@/components/editor'

// 引入api
import article_api from '@/api/article';

export default {
    name: "info",
    components: {
        editor
    },
    data: () => {
        return {
            id: '',
            is_save: true,
            is_full: false,
            article: {
                title: '',
                image: '',
                status: 1,
                category_id: 1,
                tags: [],
                is_top: false,
                description: '',
                type: 1,
                original_content: '',
                content: ''
            },
            publish_dialog: false,
            tags: ['java', '前端', '随笔']
        }
    },
    created() {
        if (this.$route.query.id) {
            this.id = this.$route.query.id;
            this.is_save = false;
            article_api.info(this.id, (res) => {
                this.article = res.data;
            }, () => {
                this.$message({
                    message: '文章ID错误',
                    type: 'error',
                    onClose: () => {
                        this.$router.push('/article/list');
                    }
                })
            })
        } else {
            this.article = {
                title: '',
                image: '',
                status: 1,
                category_id: 1,
                tags: [],
                is_top: false,
                description: '',
                type: 1,
                original_content: '',
                content: ''
            };
        }
    },
    methods: {
        submit() {
            // 根据类型赋值
            this.article.content = this.$refs.editor.get_content();
            this.article.original_content = this.$refs.editor.get_original_content();
            console.log(this.article)
            if (this.is_save) {
                article_api.save(this.article, (res) => {
                    this.$message.success('新建文章成功');
                    this.publish_dialog = false;
                    this.$router.push("/article/list");
                }, (e) => {
                    console.error(e);
                    this.$message.error('新建文章失败');
                    this.publish_dialog = false;
                })
            } else {
                article_api.update(this.id, this.article, (res) => {
                    this.$message.success('修改文章成功');
                    this.publish_dialog = false;
                    this.$router.push("/article/list");
                }, (e) => {
                    console.error(e);
                    this.$message.error('修改文章失败');
                    this.publish_dialog = false;
                })
            }
        }
    }
}
</script>

<style scoped>
.article-info-header {
    display: grid;
    grid-template-rows: 1fr;
    grid-template-columns: 1fr 250px 108px 80px 80px 108px;
}

.article-info-fixed {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    z-index: 1002;
}

.article-info-abs {
    position: absolute;
    top: 20px;
    left: 20px;
    bottom: 20px;
    right: 20px;
}

.article-info-body {
    position: absolute;
    top: 77px;
    left: 20px;
    bottom: 20px;
    right: 20px;
}
</style>
