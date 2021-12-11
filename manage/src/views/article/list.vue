<template>
	<div class="app-container">
		<div class="el-card is-always-shadow">
			<div class="el-card__body article-list-condition">
				<div>文章标题：</div>
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
			<el-table :data="articles">
				<el-table-column type="index" label="序号"></el-table-column>
				<el-table-column label="博客标题">
					<template slot-scope="scope">
						<i class="el-icon-top" v-if="scope.row.is_top"></i>
						<el-link
							:href="
								website + '/article/' + scope.row.id + '.html'
							"
							target="_blank"
							>{{ scope.row.title }}</el-link
						>
					</template>
				</el-table-column>
				<el-table-column
					prop="category_name"
					label="分类"
				></el-table-column>
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
				:model="article"
				label-width="80px"
				style="padding: 15px"
			>
				<div style="margin-left: 80px">
					<el-upload
						action="https://jsonplaceholder.typicode.com/posts/"
						:show-file-list="false"
					>
						<el-image :src="article.image">
							<div slot="error" class="image-slot">
								<el-image
									src="https://pc-index-skin.cdn.bcebos.com/hiphoto/66225335900.jpg?x-bce-process=image/crop,x_144,y_30,w_1680,h_1050"
								></el-image>
							</div>
						</el-image>
					</el-upload>
				</div>
				<el-form-item label="图片">
					<el-input v-model="article.image"></el-input>
				</el-form-item>
				<el-form-item label="分类">
					<el-cascader
						:options="categories"
						:props="props"
						clearable
						placeholder="单选"
						:value="category_ids"
					></el-cascader>
				</el-form-item>
				<el-form-item label="文章标签">
					<el-tag
						:key="tag"
						v-for="tag in article.tags"
						closable
						:disable-transitions="false"
						@close="tag_close(tag)"
						style="margin-right: 15px"
					>
						{{ tag }}
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
				<el-form-item label="是否置顶">
					<el-switch v-model="article.is_top"></el-switch>
				</el-form-item>
				<el-form-item label="状态">
					<el-select v-model="article.status">
						<el-option label="发布" :value="1"></el-option>
						<el-option label="草稿" :value="0"></el-option>
						<el-option label="回收站" :value="-1"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="文章描述">
					<el-input
						type="textarea"
						v-model="article.description"
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
import article_api from "@/api/article";
import category_api from "@/api/category";

export default {
	name: "articleList",
	data: () => {
		return {
			page_num: 1,
			page_size: 10,
			total: 1,
			condition: {
				title: "",
				status: 1,
				is_delete: false,
				is_release: true,
			},
			articles: [],
			categories: [],
			category_ids: [],
			props: {
				children: "children",
				label: "name",
				value: "id",
				checkStrictly: true,
			},
			article: {
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
			tag_input: false,
			tag_value: "",
			setting_dialog: false,
			website: "",
		};
	},
	created() {
		this.search();
		category_api.list((res) => {
			this.categories = res.data;
		});
		this.website = this.$store.getters.website;
	},
	methods: {
		search() {
			article_api.list(
				{
					page_num: this.page_num,
					page_size: this.page_size,
					title: this.condition.title,
					status: this.condition.status,
				},
				(res) => {
					if (res.code === 200) {
						this.articles = res.data.records;
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
			this.$router.push("/article/save");
		},
		update(id) {
			this.$router.push({
				path: "/article/update",
				query: {
					id: id,
				},
			});
		},
		recovery(id) {
			article_api.update(
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
			article_api.update(
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
			article_api.update(
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
			this.article = record;
			// 2. 对分类赋值
			for (let category of this.categories) {
				if (category.id === this.article.category_id) {
					this.category_ids.push(category.id);
					return;
				}
				if (category.children.length > 0) {
					for (let child of category.children) {
						if (child.id === this.article.category_id) {
							this.category_ids.push(category.id);
							this.category_ids.push(child.id);
							return;
						}
					}
				}
			}
		},
		submit() {
			let temp_len = this.category_ids.length;
			this.article.category_id = this.category_ids[temp_len - 1];
			article_api.update(
				this.article.id,
				this.article,
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
		tag_close(tag) {
			this.article.tags.splice(this.article.tags.indexOf(tag), 1);
		},
		show_tag() {
			this.tag_input = true;
			this.$nextTick((_) => {
				this.$refs.save_tag_input.$refs.input.focus();
			});
		},
		tag_add() {
			let tag_value = this.tag_value;
			if (tag_value) {
				this.article.tags.push(tag_value);
			}
			this.tag_input = false;
			this.tag_value = "";
		},
		to_page(page_num) {
			this.page_num = page_num;
			this.search();
		},
	},
};
</script>

<style scoped>
.article-list-condition {
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
