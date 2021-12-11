<template>
	<div class="app-container">
		<div class="el-card is-always-shadow">
			<div class="el-card__body page-list-condition">
				<div>页面标题：</div>
				<el-input
					style="width: 180px"
					v-model="condition.title"
				></el-input>
				<div style="margin-left: 10px">状态：</div>
				<el-select
					style="width: 180px"
					v-model="condition.status"
					clearable
					@change="search"
					placeholder="状态"
				>
					<el-option label="发布" :value="1"></el-option>
					<el-option label="草稿" :value="0"></el-option>
					<el-option label="回收站" :value="-1"></el-option>
				</el-select>
				<el-button
					@click="search"
					type="primary"
					style="margin-left: 10px"
					>搜索</el-button
				>
				<el-button @click="clear" style="margin-left: 10px"
					>重置</el-button
				>
			</div>
		</div>
		<el-card style="margin-top: 20px" shadow="always">
			<div>
				<el-button style="float: right" type="primary" @click="save"
					>新增</el-button
				>
			</div>
			<el-table :data="pages">
				<el-table-column type="index" label="序号"></el-table-column>
				<el-table-column label="页面标题">
					<template slot-scope="scope">
						<i class="el-icon-top" v-if="scope.row.is_top"></i>
						<el-link
							:href="website + '/page/' + scope.row.id + '.html'"
							target="_blank"
							>{{ scope.row.title }}</el-link
						>
					</template>
				</el-table-column>
				<el-table-column label="状态">
					<template slot-scope="scope">
						<el-tag type="info" v-if="scope.row.status === 0"
							>草稿</el-tag
						>
						<el-tag
							type="success"
							v-else-if="scope.row.status === 1"
							>已发布</el-tag
						>
						<el-tag
							type="danger"
							v-else-if="scope.row.status === -1"
							>回收站</el-tag
						>
					</template>
				</el-table-column>
				<el-table-column
					prop="comment_count"
					label="评论数"
				></el-table-column>
				<el-table-column
					prop="view_count"
					label="浏览量"
				></el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button type="text" @click="update(scope.row.id)"
							>编辑</el-button
						>
						<el-dropdown>
							<el-button style="margin-left: 10px" type="text"
								>设置</el-button
							>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item
									@click.native="recovery(scope.row.id)"
									v-if="
										scope.row.status === 1 ||
										scope.row.status === 0
									"
									style="color: #f56c6c"
									>移到回收站</el-dropdown-item
								>
								<el-dropdown-item
									@click.native="draft(scope.row.id)"
									v-if="
										scope.row.status === 1 ||
										scope.row.status === -1
									"
									>转为草稿</el-dropdown-item
								>
								<el-dropdown-item
									@click.native="publish(scope.row.id)"
									v-if="
										scope.row.status === -1 ||
										scope.row.status === 0
									"
									>发布</el-dropdown-item
								>
								<el-dropdown-item
									@click.native="open_setting(scope.row)"
									>设置</el-dropdown-item
								>
							</el-dropdown-menu>
						</el-dropdown>
					</template>
				</el-table-column>
			</el-table>
			<div style="margin-top: 20px; width: 100%; text-align: center">
				<el-pagination
					background
					layout="prev, pager, next"
					:current-page="condition.page_num"
					:page-size="condition.page_size"
					:total="total"
					@current-change="to_page"
				>
				</el-pagination>
			</div>
		</el-card>
		<el-drawer title="发布" :visible.sync="setting_dialog">
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
				<el-form-item label="状态">
					<el-select v-model="page.status">
						<el-option label="发布" :value="1"></el-option>
						<el-option label="草稿" :value="0"></el-option>
						<el-option label="回收站" :value="-1"></el-option>
					</el-select>
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
						>立即修改</el-button
					>
					<el-button>取消</el-button>
				</el-form-item>
			</el-form>
		</el-drawer>
	</div>
</template>

<script>
import page_api from "@/api/page";

export default {
	name: "pageList",
	data: () => {
		return {
			page_num: 1,
			page_size: 10,
			total: 1,
			condition: {
				title: "",
				status: "",
				is_delete: false,
				is_release: true,
			},
			pages: [],
			page: {
				id: 1,
				title: "",
				image: "",
				status: 1,
				tags: [],
				is_top: false,
				description: "",
				type: 1,
				original_content: "",
				content: "",
			},
			setting_dialog: false,
			website: "",
		};
	},
	created() {
		this.search();
		this.website = this.$store.getters.website;
	},
	methods: {
		search() {
			page_api.list(
				{
					page_num: this.page_num,
					page_size: this.page_size,
					title: this.condition.title,
					status: this.condition.status,
				},
				(res) => {
					if (res.code === 200) {
						this.pages = res.data.records;
						this.total = res.data.total;
					} else {
						this.$message({
							message: "获取文章失败",
							type: "error",
						});
					}
				}
			);
		},
		clear() {
			this.page_num = 1;
			this.page_size = 10;
			this.condition.title = "";
		},
		save() {
			this.$router.push("/page/save");
		},
		update(id) {
			this.$router.push({
				path: "/page/update",
				query: {
					id: id,
				},
			});
		},
		recovery(id) {
			page_api.update(
				id,
				{
					status: -1,
				},
				(res) => {
					this.$message.success("已移入回收站");
					this.search();
				},
				(e) => {
					console.error(e);
					this.$message.error("移入回收站失败");
				}
			);
		},
		publish(id) {
			page_api.update(
				id,
				{
					status: 1,
				},
				(res) => {
					this.$message.success("已重新发布");
					this.search();
				},
				(e) => {
					console.error(e);
					this.$message.error("发布失败");
				}
			);
		},
		draft(id) {
			page_api.update(
				id,
				{
					status: 0,
				},
				(res) => {
					this.$message.success("已变为草稿");
					this.search();
				},
				(e) => {
					console.error(e);
					this.$message.error("变为草稿失败");
				}
			);
		},
		open_setting(record) {
			// 0. 打开
			this.setting_dialog = true;
			// 1. 对文章赋值
			this.page = record;
		},
		submit() {
			if (this.page.image === "") {
				this.page.image =
					"https://pc-index-skin.cdn.bcebos.com/hiphoto/66225335900.jpg?x-bce-process=image/crop,x_144,y_30,w_1680,h_1050";
			}
			page_api.update(
				this.page.id,
				this.page,
				(res) => {
					this.$message.success("修改文章成功");
					this.publish_dialog = false;
					this.search();
					this.setting_dialog = false;
				},
				(e) => {
					console.error(e);
					this.$message.error("修改文章失败");
					this.publish_dialog = false;
				}
			);
		},
		to_page(page_num) {
			this.page_num = page_num;
			this.search();
		},
	},
};
</script>

<style scoped>
.page-list-condition {
	display: flex;
	line-height: 40px;
}
.input-new-tag {
	width: 90px;
	margin-right: 15px;
	vertical-align: bottom;
}
.button-new-tag {
	margin-right: 15px;
	height: 32px;
	line-height: 30px;
	padding-top: 0;
	padding-bottom: 0;
}
</style>
