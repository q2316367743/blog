<template>
	<div
		class="el-card is-always-shadow"
		:class="is_full ? 'page-info-fixed' : 'page-info-abs'"
	>
		<div class="el-card__header">
			<div class="page-info-header">
				<el-input
					v-model="page.title"
					style="padding-right: 10px"
					placeholder="请输入文章标题"
				></el-input>
				<el-select
					v-model="page.type"
					placeholder="编辑器类型"
					style="padding-right: 10px"
				>
					<el-option label="markdown" :value="1"> </el-option>
					<el-option label="富文本" :value="2"> </el-option>
					<el-option label="html代码" :value="3"> </el-option>
				</el-select>
				<el-button
					type="danger"
					@click="
						page.status = 0;
						publish_dialog = true;
					"
					>保存草稿</el-button
				>
				<el-button>预览</el-button>
				<el-button
					type="primary"
					@click="
						page.status = 1;
						publish_dialog = true;
					"
					>发布</el-button
				>
				<el-button @click="is_full = !is_full">全屏编辑</el-button>
			</div>
		</div>
		<div class="page-info-body">
			<editor
				ref="editor"
				:type="page.type"
				:original_content="page.original_content"
				path="/page"
			></editor>
		</div>
		<el-drawer
			:title="page.status === 1 ? '发布' : '保存草稿'"
			:visible.sync="publish_dialog"
		>
			<el-form
				ref="form"
				:model="page"
				label-width="80px"
				style="padding: 15px"
			>
				<div style="margin-left: 80px">
					<el-upload
						action="https://jsonplaceholder.typicode.com/posts/"
						:show-file-list="false"
					>
						<el-image :src="page.image">
							<div slot="error" class="image-slot">
								<el-image
									src="https://pc-index-skin.cdn.bcebos.com/hiphoto/66225335900.jpg?x-bce-process=image/crop,x_144,y_30,w_1680,h_1050"
								></el-image>
							</div>
						</el-image>
					</el-upload>
				</div>
				<el-form-item label="图片">
					<el-input v-model="page.image"></el-input>
				</el-form-item>
				<el-form-item label="文章描述">
					<el-input
						type="textarea"
						v-model="page.description"
						:rows="5"
						placeholder=""
					></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submit"
						>立即{{ is_save ? "创建" : "修改" }}</el-button
					>
					<el-button>取消</el-button>
				</el-form-item>
			</el-form>
		</el-drawer>
	</div>
</template>

<script>
import editor from "@/components/editor";

// 引入api
import page_api from "@/api/page";

export default {
	name: "info",
	components: {
		editor,
	},
	data: () => {
		return {
			id: "",
			is_save: true,
			is_full: false,
			page: {
				title: "",
				image: "",
				status: 1,
				category_id: 1,
				tags: [],
				is_top: false,
				description: "",
				type: 1,
				original_content: "",
				content: "",
			},
			publish_dialog: false,
		};
	},
	created() {
		if (this.$route.query.id) {
			this.id = this.$route.query.id;
			this.is_save = false;
			page_api.info(
				this.id,
				(res) => {
					this.page = res.data;
				},
				() => {
					this.$message({
						message: "文章ID错误",
						type: "error",
						onClose: () => {
							this.$router.push("/page/list");
						},
					});
				}
			);
		} else {
			this.page = {
				title: "",
				image: "",
				status: 1,
				category_id: 1,
				tags: [],
				is_top: false,
				description: "",
				type: 1,
				original_content: "",
				content: "",
			};
		}
	},
	methods: {
		submit() {
			// 根据类型赋值
			this.page.content = this.$refs.editor.get_content();
			this.page.original_content =
				this.$refs.editor.get_original_content();
			if (this.page.image === "") {
				this.page.image =
					"https://pc-index-skin.cdn.bcebos.com/hiphoto/66225335900.jpg?x-bce-process=image/crop,x_144,y_30,w_1680,h_1050";
			}
			if (this.is_save) {
				page_api.save(
					this.page,
					(res) => {
						this.$message.success("新建页面成功");
						this.publish_dialog = false;
						this.$router.push("/page/list");
					},
					(e) => {
						console.error(e);
						this.$message.error("新建页面失败");
						this.publish_dialog = false;
					}
				);
			} else {
				page_api.update(
					this.id,
					this.page,
					(res) => {
						this.$message.success("修改页面成功");
						this.publish_dialog = false;
						this.$router.push("/page/list");
					},
					(e) => {
						console.error(e);
						this.$message.error("修改页面失败");
						this.publish_dialog = false;
					}
				);
			}
		},
	},
};
</script>

<style scoped>
.page-info-header {
	display: grid;
	grid-template-rows: 1fr;
	grid-template-columns: 1fr 250px 108px 80px 80px 108px;
}

.page-info-fixed {
	position: fixed;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	z-index: 1002;
}

.page-info-abs {
	position: absolute;
	top: 20px;
	left: 20px;
	bottom: 20px;
	right: 20px;
}

.page-info-body {
	position: absolute;
	top: 77px;
	left: 20px;
	bottom: 20px;
	right: 20px;
}
</style>
