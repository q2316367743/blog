<template>
    <div class="blog-editor" ref="editor">
        <div v-show="type === 1" id="vditor" style="height: 100%"></div>
        <div v-show="type === 2" id="editor" style="height: 100%"></div>
        <codemirror ref="codemirror" v-show="type === 3" v-model="content" :options="options"></codemirror>
    </div>
</template>

<script>
import Vditor from 'vditor'
import "vditor/src/assets/scss/index.scss"
import Editor from 'wangeditor'
import codemirror from 'vue-codemirror/src/codemirror'// 引入CodeMirror
import "codemirror/lib/codemirror.css";
// 引入主题后还需要在 options 中指定主题才会生效
import "codemirror/theme/idea.css";
// 语法高亮
import "codemirror/mode/xml/xml.js";
import "codemirror/addon/hint/show-hint";
import "codemirror/addon/hint/html-hint";

export default {
    name: "editor",
    props: {
        type: Number,
        original_content: String
    },
    data: () => {
        return {
            content: '',
            options: {
                tabSize: 4, // 缩进格式
                theme: "idea", // 主题，对应主题库 JS 需要提前引入
                lineNumbers: true, // 显示行号
                line: true,
                mode: "xml",
                smartIndent: true,
                styleActiveLine: true, // 高亮选中行
                showCursorWhenSelecting: true,
                hintOptions: {
                    completeSingle: true, // 当匹配只有一项的时候是否自动补全
                },
            },
        }
    },
    components: {
        codemirror
    },
    mounted() {
        let height = document.querySelector('.el-card__body').offsetHeight - 40;
        this.$refs.editor.style.height = height + 'px';
        let vditor_option = {
            height: height + 'px'
        };
        let editor_content = ''
        // 根据类型初始化
        if (this.type === 1) {
            vditor_option.value = this.original_content;
        }else if (this.type === 2) {
            editor_content = this.original_content
        }
        this.content = this.original_content
        this.vditor = new Vditor('vditor', vditor_option)
        this.editor = new Editor('#editor')
        this.editor.config.height = height
        this.editor.create();
        this.editor.txt.html(editor_content);
        this.$refs.codemirror.cminstance.on("cursorActivity", () => {
            this.$refs.codemirror.cminstance.showHint();
        });
    },
    methods: {
        get_content() {
            if (this.type === 1) {
                return this.vditor.getHTML();
            } else if (this.type === 2) {
                return this.editor.txt.html();
            } else if (this.type === 3) {
                return this.content;
            }
        },
        get_original_content() {
            if (this.type === 1) {
                return this.vditor.getValue();
            } else if (this.type === 2) {
                return this.editor.txt.text();
            } else if (this.type === 3) {
                return this.content;
            }
        }
    }
}
</script>

<style scoped>
.blog-editor {
    height: 600px;
}
</style>
