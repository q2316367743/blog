<template>
	<div class="blog-editor" ref="editor">
		<div v-show="type === 1" id="vditor" style="height: 100%"></div>
		<div v-show="type === 2" id="editor" style="height: 100%"></div>
		<codemirror
			ref="codemirror"
			v-show="type === 3"
			v-model="content"
			:options="options"
		></codemirror>
	</div>
</template>

<script>
import Vditor from "vditor";
import "vditor/src/assets/scss/index.scss";
import Editor from "wangeditor";
import codemirror from "vue-codemirror/src/codemirror"; // 引入CodeMirror
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
		original_content: String,
		path: String,
	},
	data: () => {
		return {
			content: "",
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
			vditor: null,
			editor: null,
		};
	},
	components: {
		codemirror,
	},
	mounted() {
		let el_card_body = document.querySelector(".el-card__body");
		let height = 600;
		let upload_url = process.env.VUE_APP_BASE_API + "/resource/upload";
		if (el_card_body) {
			height = el_card_body.offsetHeight - 40;
		}
		let article_info_body = document.querySelector(".article-info-body");
		if (article_info_body) {
			height = article_info_body.offsetHeight - 20;
			this.$refs.editor.style.marginTop = "20px";
		}
		this.$refs.editor.style.height = height + "px";
		let vditor_option = {
			height: height + "px",
			value: "",
			upload: {
				url: upload_url,
				linkToImgUrl: upload_url,
				headers: {
					token: this.$store.getters.token,
				},
				extraData: {
					path: this.path,
				},
				fieldName: "file",
				multiple: false,
				success: (editorElement, responseText) => {
					this.vditor.insertValue(
						`![](${JSON.parse(responseText).data})`
					);
				},
			},
		};
		let editor_content = "";
		// 根据类型初始化
		if (this.type === 1) {
			vditor_option.value = this.original_content;
		} else if (this.type === 2) {
			editor_content = this.original_content;
		}
		this.content = this.original_content;
		this.vditor = new Vditor("vditor", vditor_option);
		this.$refs.codemirror.cminstance.on("cursorActivity", () => {
			this.$refs.codemirror.cminstance.showHint();
		});
		// wangEditor配置
		this.editor = new Editor("#editor");
		this.editor.config.height = height;
        // 配置上传图片
		this.editor.config.uploadImgServer = upload_url;
		this.editor.config.uploadImgMaxLength = 1;
		this.editor.config.uploadImgParams = {
			path: this.path,
		};
		this.editor.config.uploadFileName = "file";
		this.editor.config.uploadImgHeaders = {
			token: this.$store.getters.token,
		};
		this.editor.config.uploadImgHooks = {
			customInsert: function (insertImgFn, result) {
				insertImgFn(result.data);
			},
		};
        // 配置上传视频
		this.editor.config.uploadVideoServer = upload_url;
		this.editor.config.uploadVideoMaxLength = 1;
		this.editor.config.uploadVideoParams = {
			path: '/video',
		};
		this.editor.config.uploadVideoName = "file";
		this.editor.config.uploadVideoHeaders = {
			token: this.$store.getters.token,
		};
		this.editor.config.uploadVideoHooks = {
			customInsert: function (insertVideoFn, result) {
				insertVideoFn(result.data);
			},
		};
        // 创建
		this.editor.create();
		this.editor.txt.html(editor_content);
	},
	watch: {
		original_content(new_value, old_value) {
			this.content = new_value;
			this.editor.txt.html(new_value);
			this.vditor.setValue(new_value);
		},
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
				return this.editor.txt.html();
			} else if (this.type === 3) {
				return this.content;
			}
		},
	},
	destroyed() {
		this.vditor.destroy();
	},
};
</script>

<style scoped>
.blog-editor {
	height: 600px;
	padding-top: 15px;
}
</style>
