<template>
	<div class="el-card is-always-shadow config-card">
		<div>
			<el-tabs type="card" v-model="type">
				<el-tab-pane label="基础设置" name="1"></el-tab-pane>
				<el-tab-pane label="首页设置" name="2"></el-tab-pane>
				<el-tab-pane label="图片设置" name="3"></el-tab-pane>
				<el-tab-pane label="音乐设置" name="4"></el-tab-pane>
			</el-tabs>
			<el-button class="config-update" type="text" @click="update"
				>修改</el-button
			>
		</div>
		<div class="config-content">
			<el-form label-width="120px" v-show="type === '1'">
				<el-form-item label="标题">
					<el-input
						v-model="config.name"
						style="width: 360px"
					></el-input>
				</el-form-item>
				<el-form-item label="域名">
					<el-input
						v-model="config.href"
						style="width: 360px"
					></el-input>
				</el-form-item>
				<el-form-item label="网站关键字">
					<el-tag
						:key="keyword"
						v-for="keyword in config.keywords"
						closable
						:disable-transitions="false"
						@close="tag_close(keyword)"
						style="margin-right: 15px"
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
						v-else
						class="button-new-tag"
						size="small"
						@click="show_tag"
						>+ 新关键字</el-button
					>
				</el-form-item>
				<el-form-item label="图标">
					<el-input
						v-model="config.favicon"
						style="width: 360px"
					></el-input>
				</el-form-item>
				<el-form-item label="备案">
					<el-input
						v-model="config.keep_on_record"
						style="width: 360px"
						placeholder="为空则不显示"
					></el-input>
				</el-form-item>
			</el-form>
			<el-form label-width="120px" v-show="type === '2'">
				<el-form-item label="名称">
					<el-input
						v-model="config.index.title"
						style="width: 360px"
					></el-input>
				</el-form-item>
				<el-form-item label="描述">
					<el-input
						v-model="config.index.description"
						style="width: 360px"
						type="textarea"
						:rows="5"
					></el-input>
				</el-form-item>
				<el-form-item label="打字机速度">
					<el-input-number
						v-model="config.index.speed"
						controls-position="right"
					></el-input-number>
				</el-form-item>
				<el-form-item label="打字机文字">
					<template v-for="(text, idx) in config.index.texts">
						<el-tag
							closable
							:key="idx"
							:disable-transitions="false"
							@close="text_close(text)"
							type="success"
						>
							{{ text }}
						</el-tag>
						<br :key="idx + text" />
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
					<el-button
						v-else
						class="button-new-text"
						size="small"
						@click="show_text"
						>+ 新关键字
					</el-button>
				</el-form-item>
				<el-form-item label="打字机循环">
					<el-switch v-model="config.index.loop"></el-switch>
				</el-form-item>
			</el-form>
			<el-form label-width="120px" v-show="type === '3'">
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
			<div v-show="type === '4'" style="padding: 20px">
				<el-table :data="config.music">
					<el-table-column
						label="序号"
						type="index"
					></el-table-column>
					<el-table-column
						label="音乐链接"
						prop="url"
					></el-table-column>
					<el-table-column
						label="封面"
						prop="cover"
					></el-table-column>
					<el-table-column label="名称" prop="name"></el-table-column>
					<el-table-column
						label="歌手"
						prop="artist"
					></el-table-column>
					<el-table-column
						label="歌词链接"
						prop="lyric"
					></el-table-column>
					<el-table-column label="操作">
						<template slot-scope="scope">
							<el-button
								type="text"
								@click="music_dialog_open(scope.row)"
								>修改</el-button
							>
							<el-button type="text" style="color: #f56c6c" @click="music_remove(scope.row)"
								>删除</el-button
							>
						</template>
					</el-table-column>
				</el-table>
				<el-button
					type="primary"
					style="position: absolute; right: 20px; bottom: 20px"
					@click="music_dialog_open()"
					icon="el-icon-plus"
					circle
				></el-button>
			</div>
		</div>
		<el-dialog
			:visible.sync="music_dialog"
			:title="(music_save_status ? '新增' : '修改') + '歌曲'"
		>
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
				<el-form-item label="歌词链接">
					<el-input v-model="music_value.lyric"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="music_save">{{
						music_save_status ? "新增" : "修改"
					}}</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
import config_api from "@/api/config";

export default {
	name: "config",
	data: () => {
		return {
			type: "1",
			config: {
				keywords: [],
				favicon: "",
				index: {
					title: "",
					description: "",
					speed: 0,
					texts: [],
					loop: true,
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
				href: "http://localhost:8990",
			},
			tag_input: false,
			tag_value: "",
			text_input: false,
			text_value: "",
			music_dialog: false,
			music_save_status: false,
			music_value: {
				url: "",
				cover: "",
				name: "",
				artist: "",
				lyric: "",
			},
		};
	},
	created() {
		this.init();
	},
	methods: {
		init() {
			config_api.info(
				(res) => {
					this.config = res.data;
				},
				() => {
					this.$message.error("获取配置信息错误");
				}
			);
		},
		tag_close(tag) {
			this.config.keywords.splice(this.config.keywords.indexOf(tag), 1);
		},

		show_tag() {
			this.tag_input = true;
			this.$nextTick((_) => {
				this.$refs.save_tag_input.focus();
			});
		},

		tag_add() {
			let tag_value = this.tag_value;
			if (tag_value) {
				this.config.keywords.push(tag_value);
			}
			this.tag_input = false;
			this.tag_value = "";
		},
		text_close(text) {
			this.config.index.texts.splice(
				this.config.index.texts.indexOf(text),
				1
			);
		},

		show_text() {
			this.text_input = true;
			this.$nextTick((_) => {
				this.$refs.save_text_input.focus();
			});
		},

		text_add() {
			let text_value = this.text_value;
			if (text_value) {
				this.config.index.texts.push(text_value);
			}
			this.text_input = false;
			this.text_value = "";
		},
		music_dialog_open(record) {
			this.music_dialog = true;
			if (record) {
				this.music_save_status = false;
				this.music_value = record;
			} else {
				this.music_save_status = true;
				this.music_value = {
					url: "",
					cover: "",
					name: "",
					artist: "",
				};
			}
		},
		music_save() {
			this.music_dialog = false;
			if (this.music_save_status) {
				this.config.music.push({
					url: this.music_value.url,
					cover: this.music_value.cover,
					name: this.music_value.name,
					artist: this.music_value.artist,
				});
			}
		},
        music_remove(item) {
            this.config.music.splice(this.config.music.indexOf(item), 1);
        },
		update() {
			config_api.update(
				this.config,
				() => {
					this.$message.success("修改成功");
					this.init();
				},
				() => {
					this.$message.error("修改失败，错误原因请查看控制台");
				}
			);
		},
	},
};
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

.config-content {
	position: absolute;
	top: 60px;
	left: 0;
	bottom: 0;
	right: 0;
    overflow-y: auto;
}

.config-update {
	position: absolute;
	top: 0;
	right: 15px;
}

.config-card .el-card__body {
	height: 100%;
	overflow-y: auto;
}

.button-new-tag {
	margin-right: 15px;
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
	margin-right: 15px;
	vertical-align: bottom;
}

.input-new-text {
	width: 90px;
	vertical-align: bottom;
}

.el-card__body .h1 {
	margin-left: 10px;
}
</style>
