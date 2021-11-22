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
                <el-button type="danger">保存草稿</el-button>
                <el-button>预览</el-button>
                <el-button type="primary">发布</el-button>
                <el-button @click="is_full = !is_full">全屏编辑</el-button>
            </div>
        </div>
        <div class="article-info-body">
            <div id="vditor" style="height: 100%"></div>
        </div>
    </div>
</template>

<script>
import Vditor from 'vditor'
import "vditor/src/assets/scss/index.scss"

export default {
    name: "info",
    data: () => {
        return {
            id: '',
            is_save: true,
            is_full: false,
            article: {
                title: '',
                type: 1
            },
            vditor: null
        }
    },
    created() {
        if (this.$route.query.id) {
            this.id = this.$route.query.id;
            this.is_save = false;
        }
    },
    mounted() {
        this.vditor = new Vditor('vditor', {
            height: '100%'
        })
    }
}
</script>

<style scoped>
.article-info-header {
    display: grid;
    grid-template-rows: 1fr;
    grid-template-columns: 1fr 250px 108px 80px 80px 108px;
}
.article-info-fixed{
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
.article-info-body{
    position: absolute;
    top: 77px;
    left: 20px;
    bottom: 20px;
    right: 20px;
}
</style>
