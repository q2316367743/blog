<template>
    <div class="el-card is-always-shadow author-card">
        <div class="el-card__header">
            <span>作者信息</span>
            <el-button class="author-option" @click="update" type="text">修改</el-button>
        </div>
        <div class="el-card__body">
            <h1 class="h1">基础设置</h1>
            <el-form label-width="80px">
                <el-form-item label="头像">
                    <el-input v-model="author.avatar" style="width: 600px;"></el-input>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="author.name" style="width: 180px;"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="author.email" style="width: 600px;"></el-input>
                </el-form-item>
                <el-form-item label="标签">
                    <el-tag
                        :key="tag"
                        v-for="tag in author.tags"
                        closable
                        :disable-transitions="false"
                        @close="tag_close(tag)"
                        style="margin-right: 15px"
                    >
                        {{ tag }}
                    </el-tag>
                    <el-input
                        class="input-new-tag"
                        v-if="tag_input"
                        v-model="tag_value"
                        ref="save_tag_input"
                        size="small"
                        @keyup.enter.native="tag_add"
                        @blur="tag_add"
                    >
                    </el-input>
                    <el-button v-else class="button-new-tag" size="small" @click="show_tag">+ 新关键字</el-button>
                </el-form-item>
                <el-form-item label="简单描述">
                    <el-input type="textarea" :rows="5" v-model="author.description" style="width: 360px"></el-input>
                </el-form-item>
            </el-form>
            <div style="position: relative">
                <h1 class="h1">社交账号</h1>
                <el-button type="primary" class="item-option">新增</el-button>
            </div>
            <el-table :data="author.accounts">
                <el-table-column label="序号" type="index"></el-table-column>
                <el-table-column label="图标" prop="icon"></el-table-column>
                <el-table-column label="模式" prop="mode">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.mode === 1">链接</el-tag>
                        <el-tag v-else-if="scope.row.mode === 2">图片</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="名称" prop="name"></el-table-column>
                <el-table-column label="地址" prop="href"></el-table-column>
            </el-table>
            <div style="position: relative;margin-top: 1.67em">
                <h1 class="h1">个人介绍</h1>
                <el-button type="primary" class="item-option">新增</el-button>
                <el-select v-model="author.type" placeholder="编辑器类型" class="item-option">
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
            </div>
            <editor ref="editor" :type="author.type" :original_content="author.original_content" path="/image"></editor>
        </div>
    </div>
</template>

<script>
import editor from '@/components/editor'

import author_api from '@/api/author';

export default {
    name: "author",
    components: {
        editor
    },
    data: () => {
        return {
            author: {
                description: "",
                avatar: "",
                email: "",
                type: 1,
                content: "",
                tags: [],
                name: "",
                accounts: [],
                original_content: ""
            },
            tag_input: false,
            tag_value: '',
            account: {
                icon: "",
                mode: 1,
                name: "",
                href: ""
            },
        }
    },
    created() {
        this.info();
    },
    methods: {
        info() {
            author_api.info(res=> {
                this.author = res.data;
            }, () => {
                this.$message.error('获取作者信息错误');
            })
        },
        update() {
            this.author.content = this.$refs.editor.get_content();
            this.author.original_content = this.$refs.editor.get_original_content();
            author_api.update(this.author, () => {
                this.$message.success('修改作者信息成功')
            }, () => {
                this.$message.error('修改失败，错误原因请查看控制台');
            })
        },
        tag_close(tag) {
            this.author.tags.splice(this.author.tags.indexOf(tag), 1);
        },
        show_tag() {
            this.tag_input = true;
            this.$nextTick(_ => {
                this.$refs.save_tag_input.$refs.input.focus();
            });
        },
        tag_add() {
            let tag_value = this.tag_value;
            if (tag_value) {
                this.author.tags.push(tag_value);
            }
            this.tag_input = false;
            this.tag_value = '';
        },
    }
}
</script>

<style scoped>
.input-new-tag{
    width: 90px;
    margin-right: 15px;
    vertical-align: bottom;
}
.button-new-tag {
    margin-right: 15px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.author-card {
    position: absolute;
    top: 20px;
    left: 20px;
    bottom: 20px;
    right: 20px;
    padding-bottom: 40px;
}

.author-card .el-card__body {
    height: 100%;
    overflow-y: auto;
}

.author-card .author-option {
    float: right;
    padding: 3px 0;
}

.el-card__body .h1 {
    margin-left: 10px;
    margin-top: 0;
}

.item-option {
    position: absolute;right: 0;top: 0
}
</style>
