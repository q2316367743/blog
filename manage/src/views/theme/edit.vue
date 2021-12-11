<template>
	<div class="theme-editor-container">
		<div class="theme-editor-menu" @click="editor_type = 0">
			<el-tree :data="files" :props="props" @node-click="ls"></el-tree>
		</div>
		<div class="theme-editor-content">
			<div v-show="editor_type === -1" class="theme-editor-tip">
				文件类型暂不支持
			</div>
			<div v-show="editor_type === 0" class="theme-editor-tip">
				请选择文件
			</div>
			<editor
				:path="path"
				:suffix="suffix"
				v-show="editor_type === 1"
			></editor>
			<el-image :src="link" v-show="editor_type === 2"></el-image>
		</div>
	</div>
</template>

<script>
import theme_editor_api from "@/api/theme_editor";
import editor from "./editor.vue";

const text_type = ["ftl", "html", "js", "css"];
const image_type = ["png", "jpg", "jpeg", 'gif', 'ico'];

export default {
	components: {
		editor,
	},
	data: () => {
		return {
			files: [],
			props: {
				label: "name",
				children: "children",
			},
			content: "",
			editor_type: 0,
			path: "",
			suffix: "",
			link: "",
		};
	},
	created() {
		theme_editor_api.ls((res) => {
			this.files = res.data;
		});
	},
	methods: {
		ls(data) {
			if (!data.is_directory) {
				let items = data.name.split(".");
				let suffix = "";
				if (items.length === 2) {
					suffix = items[1];
				}
				if (text_type.indexOf(suffix) > -1) {
					(this.path = data.path), (this.suffix = suffix);
					this.editor_type = 1;
				} else if (image_type.indexOf(suffix) > -1) {
					this.editor_type = 2;
					this.link = `${process.env.VUE_APP_BASE_API}/theme/editor/download?path=${data.path}&token=${this.$store.getters.token}`;
				} else {
					this.editor_type = -1;
				}
			}
		},
	},
};
</script>

<style scoped>
.theme-editor-container {
	position: absolute;
	top: 20px;
	left: 20px;
	right: 20px;
	bottom: 20px;
}
.theme-editor-menu {
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	width: 250px;
	background-color: #ffffff;
	padding: 20px;
	overflow-y: auto;
	overflow-x: hidden;
}

.theme-editor-content {
	position: absolute;
	top: 0;
	left: 270px;
	right: 0;
	bottom: 0;
	background-color: #ffffff;
	overflow-y: auto;
	overflow-x: hidden;
}

.theme-editor-tip {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	text-align: center;
	padding: 25%;
}
</style>