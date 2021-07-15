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
                            <img v-if="author.avatar" :src="author.avatar" alt="头像" class="avatar">
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
                        <div id="editor" ref="editor" style="text-align:left;width: 800px;"></div>
                    </el-form-item>
                    <el-form-item label="其他网站">
                        <el-button type="primary" @click="add_other = true;other_update_status = false;">新增</el-button>
                        <el-table
                            :data="author.other"
                            style="width: 100%">
                            <el-table-column
                                prop="name"
                                label="网站名称"
                                width="180">
                            </el-table-column>
                            <el-table-column
                                prop="icon"
                                label="网站图标">
                            </el-table-column>
                            <el-table-column
                                prop="href"
                                label="网站连接">
                            </el-table-column>
                            <el-table-column
                                label="操作"
                                width="280">
                                <template slot-scope="scope">
                                    <el-button type="primary" @click="other = scope.row;add_other = true;other_update_index = scope.$index;other_update_status = true;">修改</el-button>
                                    <el-button type="danger" @click="remove(scope.$index)">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="update">修改</el-button>
                        <el-button @click="refresh">刷新</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
        <el-dialog :title="other_update_status ? '修改其他网站' : '新增其他网站'" :visible.sync="add_other" width="50%" :modal-append-to-body='false'
            custom-class="index">
            <el-form>
                <el-form-item label="网站名称">
                    <el-input v-model="other.name" class="author-input"></el-input>
                </el-form-item>
                <el-form-item label="网站图标" style="margin-top: 10px;">
                    <div style="display: flex;">
                        <el-input v-model="other.icon" class="author-input"></el-input>
                        <el-upload
                            style="width: 70px;"
                            :action="image_api"
                            :headers="{token: token}"
                            :show-file-list="false"
                            :on-success="handleIconSuccess">
                            <el-button>上传</el-button>
                        </el-upload>
                    </div>
                </el-form-item>
                <el-form-item label="网站连接" style="margin-top: 10px;">
                    <el-input v-model="other.href" class="author-input"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="add">{{ other_update_status ? '修改' : '新增'}}</el-button>
                    <el-button @click="clear">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
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
            other: {
                name: '',
                icon: '',
                href: ''
            },
            avatar_input: false,
            image_api: this.sa.cfg.api_url + 'upload/image',
            token: sessionStorage.getItem('token'),
            add_other: false,
            other_update_index: -1,
            other_update_status: false
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
                    customInsert: function (insertImgFn, result) {
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
        handleIconSuccess(res) {
            this.other.icon = this.sa.cfg.web_url + 'image/' + res.data.item;
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
        add() {
            if (this.other_update_status){
                this.author.other[this.other_update_index] = this.other;
                this.other = {
                    name: '',
                    icon: '',
                    href: ''
                };
            }else {
                this.author.other.push(this.other)
                this.other = {
                    name: '',
                    icon: '',
                    href: ''
                };
            }
            this.other_update_index = -1;
            this.add_other = false;
        },
        clear() {
            this.other = {
                name: '',
                icon: '',
                href: ''
            };
        },
        remove(index) {
            this.$confirm('是否删除此网站?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                for (let i = index; i < this.author.other.length; i++) {
                    this.author.other[i] = this.author.other[i + 1];
                }
                this.author.other.pop()
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            })
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

.author-input {
    width: 500px;
}

.el-dialog {
    z-index: 10003 !important;
}

.el-dialog__wrapper {
    z-index: 10002 !important;
}

</style>