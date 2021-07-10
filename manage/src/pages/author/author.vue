<template>
    <div class="vue-box">
        <el-card class="box-card">
            <div slot="header">
                <span>作者管理</span>
            </div>
            <div>
                <el-form ref="form" :model="author" label-width="80px">
                    <el-form-item label="作者昵称">
                        <el-input v-model="author.name"></el-input>
                    </el-form-item>
                    <el-form-item label="作者描述">
                        <el-input v-model="author.description"
                                  type="textarea"
                                  :rows="2"
                                  placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="头像">
                        <el-upload
                            class="avatar-uploader"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                            <img v-if="author.avatar" :src="author.avatar" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="码云地址">
                        <el-input v-model="author.gitee"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary">修改</el-button>
                        <el-button @click="refresh">刷新</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script>
export default {
    name: "author",
    data() {
        return {
            author: {
                name: '',
                description: '',
                avatar: '',
                gitee: '',
                other: []
            }
        }
    },
    created() {
        this.sa.getJSON("https://esion.xyz/blog/api/global/author", (res) => {
            this.author = res.data.item;
        })
    },
    methods: {
        refresh(){
            this.sa.getJSON("https://esion.xyz/blog/api/global/author", (res) => {
                this.author = res.data.item;
            })
        },
        handleAvatarSuccess(res, file) {
            this.author.avatar = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
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
</style>