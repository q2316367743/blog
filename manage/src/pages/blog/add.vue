<template>
	<div class="box" id="article-main">
		<!-- 欢迎 -->
		<div class="content">
			<el-card shadow="nover" style="margin-top: 2em">
				<el-form ref="form">
					<el-row>
						<el-col :span="17"
							><el-input
								v-model="title"
								placeholder="请输入文章标题"
							></el-input
						></el-col>
						<el-col :span="6" :offset="1"
							><el-select
								v-model="categoryId"
								placeholder="请选择分类"
								style="width: 100%"
							>
								<el-option
									v-for="item in category"
									:key="item.id"
									:label="item.name"
									:value="item.id"
								>
								</el-option> </el-select
						></el-col>
					</el-row>
					<el-input
						v-model="description"
						placeholder="文章描述（用于展示在文章首页上）"
						style="margin-top: 20px"
					></el-input>
					<div class="tagOut article-tag">
						<el-tag
							:key="tag.id"
							v-for="tag in tags"
							closable
							:disable-transitions="false"
							@close="handleClose(tag)"
							style="margin-left: 15px"
						>
							{{ tag.name }}
						</el-tag>
						<input
							type="text"
							autocomplete="off"
							placeholder="(选填) 通过标签可以管理自己的文章分类,敲入回车确定添加标签"
							v-model="inputValue"
							@keyup.enter="handleInputConfirm"
							@blur="handleInputConfirm"
							class="tagClass article-tag"
						/>
					</div>
					<div class="opinion">
						<el-button @click="save">保存</el-button>
						<el-button @click="submit" type="primary"
							>发布</el-button
						>
						<el-button @click="beginEditor" type="success"
							>开始编写</el-button
						>
					</div>
					<div class="article-content">
						<el-button
							type="text"
							class="article-preview"
							@click="preview"
							>{{ inputVisible ? "预览" : "编辑" }}</el-button
						>
						<el-input
							type="textarea"
							:rows="27"
							placeholder="请输入Markdown内容"
							v-model="contentText"
							v-if="inputVisible"
						>
						</el-input>
						<div
							v-else
							v-html="contentHtml"
							v-highlight
							style="height: 573px; overflow-y: auto"
							class="el-textarea__inner typo"
						></div>
					</div>
				</el-form>
			</el-card>
		</div>
	</div>
</template>

<script>
// markdown转换
import showdown from "showdown";
let converter = new showdown.Converter();
converter.setOption("tables", true); // 将表格显示出来

// 自定义样式
import "./typo.css";

// 语法高亮
import Vue from "vue";
import hljs from "highlight.js";
import $ from "jquery";
import "highlight.js/styles/solarized-light.css";

// 定义自定义指令 highlight 代码高亮
Vue.directive("highlight", function (el) {
	let blocks = el.querySelectorAll("pre code");
	blocks.forEach((block) => {
		hljs.highlightBlock(block);
	});
	blocks.forEach(function (block) {
		$(block).html(
			"<ol><li>" +
				$(block).html().replace(/\n/g, "\n</li><li>") +
				"\n</li></ol>"
		);
	});
});

import ajax from "@/api/article";

export default {
	data() {
		return {
			title: "",
			categoryId: "",
			category: [],
			description: "",
			tags: [],
			contentText: "",
			contentHtml: "",
			inputVisible: true,
			inputValue: "",
		};
	},
	watch: {
		contentText: function (val) {
			this.contentHtml = converter.makeHtml(val);
		},
	},
	created() {
		console.log(
			"开源说明：中文排版引用typo.css，语法高亮采用highlight.js，markdown转换采用showdown"
		);
		// 获取分类
		ajax.category((res) => {
			if (res.success) {
				this.category = res.data.items;
			}
		});
	},
	methods: {
		handleClose(tag) {
			this.tags = this.tags.filter((item) => {
				if (item.id == tag.id) {
					return false;
				}
				return true;
			});
		},

		handleInputConfirm() {
			if (this.inputValue) {
				ajax.tag(this.inputValue, (res) => {
					if (res.success) {
						this.tags.push(res.data.item);
					}
				});
			}
			this.inputValue = "";
		},
		preview() {
			this.inputVisible = !this.inputVisible;
		},
		save() {
			// 保存
		},
		submit() {
			// 发布
			let tags = new Array();
			this.tags.filter((item) => {
				tags.push(item.id);
			});
			let article = {
				title: this.title,
				categoryId: this.categoryId,
				description: this.description,
				tags: tags,
				content: this.contentHtml,
			};
            ajax.article(article, res=>{
                if(res.success){
                    this.layer.msg('发布成功');
                    // 重置
                    this.title = '';
                    this.categoryId = '',
                    this.description = '',
                    this.tags = [],
                    this.contentText = '',
                    this.contentHtml = ''
                }
            })
		},
		beginEditor() {
			$("#article-main")[0].scrollTop = 280;
		},
	},
};
</script>

<style>
.article-tag {
	-webkit-appearance: none;
	background-color: #fff;
	background-image: none;
	border-radius: 4px;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	color: #606266;
	display: inline-block;
	font-size: inherit;
	line-height: 38px;
	outline: 0;
	-webkit-transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
	transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
	width: 100%;
}
.tagOut {
	height: 40px;
	margin-top: 20px;
	position: relative;
	border: 1px solid #dcdfe6;
}
.tagClass {
	position: absolute;
	border: 0px solid #ffffff;
	height: 38px;
	padding: 0 15px;
	max-width: 440px;
}
.article-preview {
	position: absolute;
	right: 15px;
	top: 5px;
	z-index: 1;
	display: none;
}

.article-content {
	margin-top: 20px;
	position: relative;
	min-height: 40px;
}

.article-content:hover .article-preview {
	display: inherit;
}

@font-face {
	font-family: "JetBrainsMono";
	src: url("http://esion.xyz/assets/JetBrainsMono/web/eot/JetBrainsMono-Medium.eot");
}

.hljs {
	border: 0;
	font-size: 14px;
	display: block;
	padding: 1px;
	margin: 0;
	width: 100%;
	color: #333;
	white-space: pre-wrap;
	font-family: JetBrainsMono;
	letter-spacing: 1px;
}
.hljs ol {
	list-style: decimal;
	margin: 0px 0px 0 40px !important;
	padding: 0px;
}
.hljs ol li {
	list-style: decimal-leading-zero;
	border-left: 1px solid #ddd !important;
	padding: 5px !important;
	margin: 0 !important;
	white-space: pre;
}

.opinion {
	margin-top: 20px;
}
</style>