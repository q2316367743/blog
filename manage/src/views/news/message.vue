<template>
	<div class="app-container">
		<div
			class="el-card"
			style="display: flex; justify-content: space-between; padding: 20px"
		>
			<div>
				<el-input
					v-model="condition.name"
					style="width: 240px"
					placeholder="昵称"
				></el-input>
				<el-select
					v-model="condition.type"
					clearable
					@change="search"
					style="margin-left: 20px"
					placeholder="类型"
				>
					<el-option
						v-for="(value, key) in types"
						:key="key"
						:value="key"
						:label="value"
					></el-option>
				</el-select>
				<el-select
					v-model="condition.is_read"
					clearable
					@change="search"
					style="margin-left: 20px"
					placeholder="状态"
				>
					<el-option :value="false" label="未读"></el-option>
					<el-option :value="true" label="已读"></el-option>
				</el-select>
			</div>
			<div>
				<el-button type="primary" @click="search">搜索</el-button>
			</div>
		</div>
		<div class="el-card" style="margin-top: 20px; padding: 20px">
			<el-table :data="messages" v-loading="table_load">
				<el-table-column
					label="ID"
					prop="id"
					width="60px"
				></el-table-column>
				<el-table-column label="类型" width="100px">
					<template slot-scope="scope">
						<el-tag type="success">{{
							types[scope.row.type]
						}}</el-tag>
					</template>
				</el-table-column>
				<el-table-column
					label="昵称"
					prop="name"
					width="150px"
				></el-table-column>
				<el-table-column label="邮箱" prop="email"></el-table-column>
				<el-table-column
					label="创建时间"
					prop="create_time"
				></el-table-column>
				<el-table-column label="内容">
					<template slot-scope="scope">
						<span>{{
							scope.row.content.length > 14
								? scope.row.content.substr(0, 14) + "..."
								: scope.row.content
						}}</span>
					</template>
				</el-table-column>
				<el-table-column label="操作" width="150px">
					<template slot-scope="scope">
						<el-button type="text" @click="open_info(scope.row)"
							>详情</el-button
						>
						<el-button
							type="text"
							style="color: #f56c6c"
							@click="read(scope.row.id, false)"
							v-if="scope.row.is_read"
							>转为未读
						</el-button>
						<el-button
							type="text"
							style="color: #67c23a"
							@click="read(scope.row.id, true)"
							v-else
							>已读
						</el-button>
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
			<div slot="title">意见反馈</div>
			<div class="desc-info-info">
				<div class="desc-info-label">类型</div>
				<div class="desc-info-content">
					<el-tag type="success" v-if="info_data.type === 1"
						>博客</el-tag
					>
					<el-tag type="primary" v-else-if="info_data.type === 2"
						>es-client</el-tag
					>
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">用户名</div>
				<div class="desc-info-content">{{ info_data.name }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">邮箱</div>
				<div class="desc-info-content">{{ info_data.email }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">个人网址</div>
				<div class="desc-info-content">{{ info_data.url }}</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">创建时间</div>
				<div class="desc-info-content">
					{{ info_data.create_time }}
				</div>
			</div>
			<div class="desc-info-info">
				<div class="desc-info-label">内容</div>
				<div class="desc-info-content">{{ info_data.content }}</div>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import message_api from "@/api/message";
import dict_api from "@/api/dict";

export default {
	name: "index",
	data: () => {
		return {
			condition: {
				name: "",
				type: "",
				is_read: false,
				page_num: 1,
				page_size: 10,
			},
			total: 1,
			messages: [],
			info_dialog: false,
			info_data: {
				id: 1,
				create_time: "",
				type: 1,
				name: "",
				email: "",
				url: "",
				content: "",
			},
			types: {},
			table_load: false
		};
	},
	created() {
		this.search();
		dict_api.map(1, (res) => {
			this.types = res.data;
		});
	},
	methods: {
		search() {
			this.table_load = true;
			message_api.list(this.condition, (res) => {
				this.messages = res.data.records;
				this.total = res.data.total;
				this.table_load = false;
			}, () => {
				this.table_load = false;
			});
		},
		open_info(info) {
			this.info_data = info;
			this.info_dialog = true;
		},
		read(id, is_read) {
			message_api.read(id, is_read, () => {
				this.search();
			});
		},
		to_page(page_num) {
			this.condition.page_num = page_num;
			this.search();
		},
	},
};
</script>

<style scoped>

</style>
