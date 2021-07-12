<template>
    <div class="vue-box">
        <el-card class="box-card" style="margin: 1em;">
            <div slot="header">
                <span>网站设置</span>
            </div>
            <div>
                <el-form ref="form" :model="config" label-width="80px">
                    <el-form-item label="背景图片">
                        <el-upload
                            class="background-uploader"
                            :action="image_api"
                            :headers="{token: token}"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess">
                            <img v-if="config.background" :src="config.background" class="background">
                            <i v-else class="el-icon-plus background-uploader-icon"></i>
                        </el-upload>
                        <el-input v-model="config.background" :disabled="!background_input" class="config-input"></el-input>
                        <br>
                        <el-checkbox v-model="background_input">外部连接？</el-checkbox>
                    </el-form-item>
                    <el-form-item label="音乐" v-for="(item, index) in config.music" :key="index">
                        <el-form-item label="音乐名称">
                            <el-input v-model="item.name" class="config-input"></el-input>
                        </el-form-item>
                        <el-form-item label="音乐封面" style="margin-top: 10px;">
                            <el-input v-model="item.cover" class="config-input"></el-input>
                        </el-form-item>
                        <el-form-item label="音乐链接" style="margin-top: 10px;">
                            <el-input v-model="item.url" class="config-input"></el-input>
                        </el-form-item>
                        <el-form-item label="音乐歌手" style="margin-top: 10px;">
                            <el-input v-model="item.artist" class="config-input"></el-input>
                        </el-form-item>
                        <el-form-item v-if="config.music.length === index + 1" style="margin-top: 10px;">
                            <el-button type="primary" @click="add_music">新增</el-button>
                        </el-form-item>
                    </el-form-item>
                    <el-form-item v-if="config.music.length === 0">
                        <el-form-item>
                            <el-button type="primary" @click="add_music">新增</el-button>
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
export default {
    name: "config",
    data() {
        return {
            config: {
                background: '',
                music: []
            },
            background_input: false,
            image_api: this.sa.cfg.api_url + 'upload/image',
            token: sessionStorage.getItem('token')
        }
    },
    created() {
        this.sa.getJSON("config", res=>{
            if (res.success){
                this.config = res.data.item;
            }
        })
    },
    methods: {
        handleAvatarSuccess(){

        },
        add_music(){
            this.config.music.push({
                name: '',
                cover: '',
                url: '',
                artist: ''
            })
        },
        update(){

        },
        refresh() {
            this.sa.getJSON("config", res=>{
                if (res.success){
                    this.config = res.data.item;
                }
            })
        }
    }
}
</script>

<style scoped>
.background-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.background-uploader .el-upload:hover {
    border-color: #409EFF;
}

.background-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.background {
    height: 178px;
    display: block;
}


.config-input {
    width: 500px;
}
</style>