<template>
	<div class="el-card container">
		<el-tabs type="card" v-model="condition.source_type" @tab-click="list">
			<el-tab-pane label="文章" name="1"></el-tab-pane>
			<el-tab-pane label="页面" name="2"></el-tab-pane>
		</el-tabs>
		<div class="comment-status">
			<el-select
				v-model="condition.status"
				size="small"
				@change="list"
				clearable
			>
				<el-option label="未审核" :value="1"></el-option>
				<el-option label="通过" :value="2"></el-option>
				<el-option label="拒绝" :value="3"></el-option>
			</el-select>
		</div>
		<div class="comment-content">
			<el-table :data="comments" v-loading="loading">
				<el-table-column label="序号" type="index"></el-table-column>
				<el-table-column label="用户" width="200px">
					<template slot-scope="scope">
						<el-link :href="scope.row.website" target="_blank">{{
							scope.row.nickname
						}}</el-link>
					</template>
				</el-table-column>
				<el-table-column label="状态">
					<template slot-scope="scope">
						<el-tag type="primary" v-if="scope.row.status === 1"
							>审核中</el-tag
						>
						<el-tag
							type="success"
							v-else-if="scope.row.status === 2"
							>通过</el-tag
						>
						<el-tag type="danger" v-else-if="scope.row.status === 3"
							>拒绝</el-tag
						>
					</template>
				</el-table-column>
				<el-table-column label="内容">
					<template slot-scope="scope">
						{{
							scope.row.content.length > 14
								? scope.row.content.substr(0, 14) + "..."
								: scope.row.content
						}}
					</template>
				</el-table-column>
				<el-table-column
					:label="condition.source_type === '1' ? '文章' : '页面'"
					width="200px"
				>
					<template slot-scope="scope">
						<el-link
							:href="
								website +
								'/' +
								(condition.source_type == 1
									? 'article'
									: 'page') +
								'/' +
								scope.row.source_id +
								'.html'
							"
							target="_blank"
							>{{ scope.row.source_title }}</el-link
						>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="200px">
					<template slot-scope="scope">
						<el-button type="text" @click="open_info(scope.row)"
							>详情</el-button
						>
						<el-button type="text" @click="open_comment(scope.row)"
							>回复</el-button
						>
						<el-button
							type="text"
							v-if="
								scope.row.status == 1 || scope.row.status == 3
							"
							style="color: #67c23a"
							@click="update_status(scope.row.id, 2)"
							>通过</el-button
						>
						<el-button
							type="text"
							v-else-if="
								scope.row.status == 1 || scope.row.status == 2
							"
							@click="update_status(scope.row.id, 3)"
							style="color: #f56c6c"
							>转为拒绝</el-button
						>
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
		</div>
		<el-dialog :visible.sync="info_dialog">
			<div slot="title">评论详情</div>
			<div class="desc-info-info">
				<div class="desc-info-label">用户名</div>
				<div class="desc-info-content">{{ info_data.nickname }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">邮箱</div>
				<div class="desc-info-content">{{ info_data.email }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">个人网址</div>
				<div class="desc-info-content">{{ info_data.website }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">创建时间</div>
				<div class="desc-info-content">
					{{ info_data.create_time }}
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">状态</div>
				<div class="desc-info-content">
					<el-tag type="primary" v-if="info_data.status === 1"
						>审核中</el-tag
					>
					<el-tag type="success" v-else-if="info_data.status === 2"
						>通过</el-tag
					>
					<el-tag type="primary" v-else-if="info_data.status === 3"
						>拒绝</el-tag
					>
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">类型</div>
				<div class="desc-info-content">
					<el-tag type="info" v-if="info_data.type === 1"
						>访客</el-tag
					>
					<el-tag type="success" v-else-if="info_data.type === 2"
						>小伙伴</el-tag
					>
					<el-tag type="primary" v-else-if="info_data.type === 3"
						>作者自己</el-tag
					>
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">
					评论{{ condition.source_type === "1" ? "文章" : "页面" }}
				</div>
				<div class="desc-info-content">
					<el-link
						:href="
							website +
							'/' +
							(condition.source_type == 1 ? 'article' : 'page') +
							'/' +
							info_data.source_id +
							'.html'
						"
						target="_blank"
						>{{ info_data.source_title }}</el-link
					>
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">内容</div>
				<div class="desc-info-content">{{ info_data.content }}</div>
			</div>
			<div slot="footer">
				<el-button type="primary" @click="comment_dialog = true"
					>回复</el-button
				>
			</div>
		</el-dialog>
		<el-dialog
			:visible.sync="comment_dialog"
			:close-on-click-modal="false"
			width="500px"
			top="20vh"
		>
			<div slot="title">回复 {{ info_data.nickname }}</div>
			<el-input
				:rows="7"
				v-model="comment_content"
				type="textarea"
			></el-input>
			<div slot="footer">
				<el-button type="primary" @click="submit_comment"
					>回复</el-button
				>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import comment_api from "@/api/comment";

export default {
	name: "comment",
	data: () => {
		return {
			condition: {
				source_type: "1",
				status: 1,
				page_num: 1,
				page_size: 10,
			},
			total: 1,
			comments: [],
			loading: false,
			website: "",
			info_dialog: false,
			info_data: {
				id: 0,
				email: "",
				website: "",
				nickname: "",
				create_time: "",
				status: 0,
				type: 0,
				source_id: 0,
				source_title: "",
				root_id: 0,
				content: "",
			},
			comment_dialog: false,
			comment_content: "",
		};
	},
	created() {
		this.list();
		this.website = this.$store.getters.website;
	},
	methods: {
		list() {
			this.loading = true;
			comment_api.list(
				this.condition,
				(res) => {
					this.comments = res.data.records;
					this.total = res.data.total;
					this.loading = false;
				},
				() => {
					this.loading = false;
				}
			);
		},
		to_page(page_num) {
			this.condition.page_num = page_num;
			this.list();
		},
		update_status(id, status) {
			comment_api.update(
				id,
				{
					status: status,
				},
				() => {
					this.$message.success(
						"已" + (status == 2 ? "通过" : "拒绝")
					);
					this.list();
				}
			);
		},
		open_info(record) {
			this.info_data = record;
			this.info_dialog = true;
		},
		open_comment(record) {
			this.info_data = record;
			this.comment_content = "";
			this.comment_dialog = true;
		},
		submit_comment() {
			comment_api.save(
				{
					source_id: this.info_data.source_id,
					source_type: this.condition.source_type,
					root_id: this.info_data.id,
					target_id: this.info_data.id,
					target_nickname: this.info_data.nickname,
					target_website: this.info_data.website,
					content: this.comment_content,
				},
				() => {
					this.comment_dialog = false;
					this.info_dialog = false;
					this.list();
					this.$message.success("回复成功");
				}
			);
		},
	},
};
</script>

<style scoped>
.container {
	position: absolute;
	top: 20px;
	left: 20px;
	right: 20px;
	bottom: 20px;
}
.comment-status {
	position: absolute;
	top: 4px;
	right: 4px;
}
.comment-content {
	position: absolute;
	top: 50px;
	left: 0;
	right: 0;
	bottom: 0;
	overflow-y: auto;
}
</style>
