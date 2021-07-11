<template>
    <div class="vue-box">
        <el-card class="box-card" style="margin: 1em;">
            <div slot="header">
                <span>作者管理</span>
            </div>
            <div>
                <el-form ref="form" :model="author" label-width="80px">
                    <el-form-item label="作者昵称">
                        <el-input v-model="author.name" class="author-input"></el-input>
                    </el-form-item>
                    <el-form-item label="作者描述">
                        <el-input v-model="author.description"
                                  type="textarea"
                                  :rows="2"
                                  placeholder="请输入内容"
                                  class="author-input"></el-input>
                    </el-form-item>
                    <el-form-item label="头像">
                        <el-upload
                            class="avatar-uploader"
                            :action="image_api"
                            :headers="{token: token}"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess">
                            <img v-if="author.avatar" :src="author.avatar" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                        <el-input v-model="author.avatar" :disabled="!avatar_input" class="author-input"></el-input>
                        <br>
                        <el-checkbox v-model="avatar_input">外部连接？</el-checkbox>
                    </el-form-item>
                    <el-form-item label="码云地址">
                        <el-input v-model="author.gitee" class="author-input"></el-input>
                    </el-form-item>
                    <el-form-item label="作者关于">
                        <div id="editor" ref="editor" style="text-align:left"></div>
                    </el-form-item>
                    <el-form-item label="其他网站" v-for="(item, index) in author.other" :key="index">
                        <el-form-item label="网站名称">
                            <el-input v-model="item.name" class="author-input"></el-input>
                        </el-form-item>
                        <el-form-item label="网站图标">
                            <el-input v-model="item.icon" class="author-input"></el-input>
                        </el-form-item>
                        <el-form-item label="网站连接">
                            <el-input v-model="item.href" class="author-input"></el-input>
                        </el-form-item>
                        <el-form-item v-if="author.other.length === index + 1">
                            <el-button type="primary" @click="add_other">新增</el-button>
                        </el-form-item>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="update">修改</el-button>
                        <el-button @click="refresh">刷新</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script>
import E from 'wangeditor';	// 富文本编辑器

export default {
    name: "author",
    data() {
        return {
            author: {
                name: '',
                description: '',
                avatar: '',
                gitee: '',
                other: [],
                base_info: ''
            },
            avatar_input: false,
            image_api: this.sa.cfg.api_url + 'upload/image',
            token: sessionStorage.getItem('token')
        }
    },
    mounted() {
        this.sa.getJSON("author", (res) => {
            if (res.success) {
                this.author = res.data.item;
                let editor = new E(this.$refs.editor);
                let image_web = this.sa.cfg.web_url + 'image/';
                editor.customConfig.menus = [	// 规定出现的菜单
                    'head', 'fontSize', 'fontName', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list',
                    'justify', 'quote', 'emoticon', 'image', 'table', 'code', 'undo', 'redo' // 重复
                ]
                editor.customConfig.uploadFileName = 'file'; // 图片流name
                editor.customConfig.withCredentials = true; // 跨域携带cookie
                editor.customConfig.onchange = (html) => {	// 创建监听，实时传入
                    this.author.base_info = html
                }
                // 配置图片上传
                editor.customConfig.uploadImgServer = this.image_api
                editor.customConfig.uploadFileName = 'file'
                editor.customConfig.uploadImgHeaders = {
                    token: sessionStorage.getItem('token')
                }
                editor.customConfig.uploadImgHooks = {
                    customInsert: function(insertImgFn, result) {
                        console.log('customInsert', result)

                        insertImgFn(image_web + result.data.item)
                    }
                }
                editor.create();		// 创建编辑器
                editor.txt.html(this.author.base_info);	// 为编辑器赋值
            }
        })
    },
    methods: {
        refresh() {
            this.sa.getJSON("author", (res) => {
                if (res.success) {
                    this.author = res.data.item;
                }
            })
        },
        handleAvatarSuccess(res) {
            this.author.avatar = this.sa.cfg.web_url + 'image/' + res.data.item;
        },
        update() {
            this.sa.put("author", this.author, res => {
                if (res.success) {
                    this.sa.ok2('修改成功');
                    this.sa.getJSON("author", (res) => {
                        if (res.success) {
                            this.author = res.data.item;
                        }
                    })
                }
            })
        },
        add_other(){
            console.log(this.author.other)
            this.author.other.push({name: '', icon: '', href: ''})
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.small-avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.author-input {
    width: 500px;
}
</style>