<template>
    <div class="el-card is-always-shadow config-card">
        <div class="el-card__header">
            <span>网站配置</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="update">修改</el-button>
        </div>
        <div class="el-card__body">
            <h1>基础设置</h1>
            <el-form label-width="120px">
                <el-form-item label="标题">
                    <el-input v-model="config.name" style="width: 360px"></el-input>
                </el-form-item>
                <el-form-item label="域名">
                    <el-input v-model="config.href" style="width: 360px"></el-input>
                </el-form-item>
                <el-form-item label="网站关键字">
                    <el-tag
                        :key="keyword"
                        v-for="keyword in config.keywords"
                        closable
                        :disable-transitions="false"
                        @close="tag_close(keyword)"
                        style="margin-left: 15px"
                    >
                        {{ keyword }}
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
                    <el-button
                        style="margin-left: 15px" v-else class="button-new-tag" size="small" @click="show_tag">+ 新关键字
                    </el-button>
                </el-form-item>
                <el-form-item label="图标">
                    <el-input v-model="config.favicon" style="width: 360px"></el-input>
                </el-form-item>
                <el-form-item label="备案">
                    <el-input v-model="config.keep_on_record" style="width: 360px" placeholder="为空则不显示"></el-input>
                </el-form-item>
            </el-form>
            <h1>首页设置</h1>
            <el-form label-width="120px">
                <el-form-item label="打字机速度">
                    <el-input-number v-model="config.index.speed" controls-position="right"></el-input-number>
                </el-form-item>
                <el-form-item label="打字机文字">
                    <template
                        v-for="text in config.index.texts">
                        <el-tag
                            closable
                            :key="text"
                            :disable-transitions="false"
                            @close="text_close(text)"
                            type="success"
                        >
                            {{ text }}
                        </el-tag>
                        <br />
                    </template>
                    <el-input
                        class="input-new-text"
                        v-if="text_input"
                        v-model="text_value"
                        ref="save_text_input"
                        size="small"
                        @keyup.enter.native="text_add"
                        @blur="text_add"
                    >
                    </el-input>
                    <el-button v-else class="button-new-text" size="small" @click="show_text">+ 新关键字
                    </el-button>
                </el-form-item>
                <el-form-item label="打字机循环">
                    <el-switch v-model="config.index.loop"></el-switch>
                </el-form-item>
            </el-form>
            <h1>图片设置</h1>
            <el-form label-width="120px">
                <el-form-item label="首页背景图片">
                    <el-input v-model="config.background"></el-input>
                </el-form-item>
                <el-form-item label="友链背景图片">
                    <el-input v-model="config.link_background"></el-input>
                </el-form-item>
                <el-form-item label="关于背景图片">
                    <el-input v-model="config.about_background"></el-input>
                </el-form-item>
                <el-form-item label="分类背景图片">
                    <el-input v-model="config.category_background"></el-input>
                </el-form-item>
                <el-form-item label="归档背景图片">
                    <el-input v-model="config.archive_background"></el-input>
                </el-form-item>
                <el-form-item label="文章默认图片">
                    <el-input v-model="config.article_background"></el-input>
                </el-form-item>
                <el-form-item label="页面默认图片">
                    <el-input v-model="config.page_background"></el-input>
                </el-form-item>
            </el-form>
            <div style="position: relative">
                <h1>音乐设置</h1>
                <el-button type="primary" style="position: absolute;right: 0;top: 0" @click="music_dialog_open">新增歌曲</el-button>
            </div>
            <el-table :data="config.music">
                <el-table-column label="序号" type="index"></el-table-column>
                <el-table-column label="链接" prop="url"></el-table-column>
                <el-table-column label="封面" prop="cover"></el-table-column>
                <el-table-column label="名称" prop="name"></el-table-column>
                <el-table-column label="歌手" prop="artist"></el-table-column>
            </el-table>
        </div>
        <el-dialog :visible.sync="music_dialog" title="新增歌曲">
            <el-form label-width="80px">
                <el-form-item label="链接">
                    <el-input v-model="music_value.url"></el-input>
                </el-form-item>
                <el-form-item label="封面">
                    <el-input v-model="music_value.cover"></el-input>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="music_value.name"></el-input>
                </el-form-item>
                <el-form-item label="歌手">
                    <el-input v-model="music_value.artist"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="music_save">新增</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import config_api from '@/api/config'
import author from "@/views/setting/author";

export default {
    name: "config",
    data: () => {
        return {
            config: {
                keywords: [],
                favicon: "",
                index: {
                    speed: 0,
                    texts: [],
                    loop: true
                },
                link_background: "",
                about_background: "",
                keep_on_record: "",
                article_background: "",
                music: [],
                background: "",
                page_background: "",
                category_background: "",
                name: "",
                archive_background: "",
                href: "http://localhost:8990"
            },
            tag_input: false,
            tag_value: '',
            text_input: false,
            text_value: '',
            music_dialog: false,
            music_value: {
                url: '',
                cover: '',
                name: '',
                artist: ''
            }
        }
    },
    created() {
        this.init();
    },
    methods: {
        init(){
            config_api.info(res => {
                this.config = res.data;
            }, () => {
                this.$message.error('获取配置信息错误')
            })
        },
        tag_close(tag) {
            this.config.keywords.splice(this.config.keywords.indexOf(tag), 1);
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
                this.config.keywords.push(tag_value);
            }
            this.tag_input = false;
            this.tag_value = '';
        },
        text_close(text) {
            this.config.index.texts.splice(this.config.index.texts.indexOf(text), 1);
        },

        show_text() {
            this.text_input = true;
            this.$nextTick(_ => {
                this.$refs.save_text_input.$refs.input.focus();
            });
        },

        text_add() {
            let text_value = this.text_value;
            if (text_value) {
                this.config.keywords.push(text_value);
            }
            this.text_input = false;
            this.text_value = '';
        },
        music_dialog_open() {
            this.music_dialog = true;
            this.music_value = {
                url: '',
                cover: '',
                name: '',
                artist: ''
            };
        },
        music_save() {
            this.music_dialog = false;
            this.config.music.push({
                url: this.music_value.url,
                cover: this.music_value.cover,
                name: this.music_value.name,
                artist: this.music_value.artist
            })
        },
        update() {
            config_api.update(this.config, () => {
                this.$message.success('修改成功');
                this.init();
            }, () => {
                this.$message.error('修改失败，错误原因请查看控制台');
            })
        }
    }
}
</script>

<style scoped>
.config-card {
    position: absolute;
    top: 20px;
    left: 20px;
    bottom: 20px;
    right: 20px;
    padding-bottom: 40px;
}

.config-card .el-card__body {
    height: 100%;
    overflow-y: auto;
}

.el-tag + .el-tag {
    margin-left: 15px;
}

.button-new-tag {
    margin-left: 15px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}

.button-new-text {
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}

.input-new-tag {
    width: 90px;
    margin-left: 15px;
    vertical-align: bottom;
}

.input-new-text {
    width: 90px;
    vertical-align: bottom;
}

h1 {
    margin-left: 10px;
}
</style>
