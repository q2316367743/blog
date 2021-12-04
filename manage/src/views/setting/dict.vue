<template>
	<div class="container">
		<div class="el-card dict-menu">
			<el-menu :default-active="type" @select="select">
				<el-menu-item index="1">
					<span slot="title">意见反馈类型</span>
				</el-menu-item>
				<el-menu-item index="2">
					<span slot="title">通知类型</span>
				</el-menu-item>
			</el-menu>
		</div>
		<div class="el-card dict-content">
			<el-table :data="dict_list" v-load="table_load">
				<el-table-column
					label="序号"
					type="index"
					width="100px"
					align="center"
				></el-table-column>
				<el-table-column label="键" prop="key"></el-table-column>
				<el-table-column label="值" prop="value"></el-table-column>
				<el-table-column label="操作">
					<template slot-scope="scope">
						<el-button type="text" @click="open_update(scope.row)"
							>修改</el-button
						>
						<el-button
							type="text"
							style="color: #f56c6c"
							@click="remove(scope.row.id)"
							>删除</el-button
						>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<div class="dict-option">
			<el-button
				type="primary"
				circle
				icon="el-icon-plus"
				@click="open_save"
			></el-button>
		</div>
		<el-dialog :visible.sync="info_dialog">
			<div slot="title">{{ info_data.id ? "修改" : "新增" }}字典</div>
			<el-form label-width="80px">
				<el-form-item label="键">
					<el-input v-model="info_data.key"></el-input>
				</el-form-item>
				<el-form-item label="值">
					<el-input v-model="info_data.value"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer">
				<el-button v-if="!info_data.id">清空</el-button>
				<el-button type="primary" @click="info_submit">{{
					info_data.id ? "修改" : "新增"
				}}</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import dict_api from "@/api/dict";

export default {
	data: () => {
		return {
			type: "1",
			dict_list: [],
			info_dialog: false,
			info_data: {
				key: "",
				value: "",
			},
			table_load: false,
		};
	},
	created() {
		this.get_dict_list();
	},
	methods: {
		get_dict_list() {
			this.table_load = true;
			dict_api.list(
				this.type,
				(res) => {
					this.dict_list = res.data;
					this.table_load = false;
				},
				() => {
					this.table_load = false;
				}
			);
		},
		select(type) {
			this.type = type;
			this.get_dict_list();
		},
		remove(id) {
			this.$confirm("此操作将永久删除, 是否继续?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			}).then(() => {
				dict_api.remove(id, () => {
					this.$message({
						type: "success",
						message: "删除成功!",
					});
					this.get_dict_list();
				});
			});
		},
		open_save() {
			this.info_data = {
				key: "",
				value: "",
			};
			this.info_dialog = true;
		},
		open_update(record) {
			this.info_data = record;
			this.info_dialog = true;
		},
		info_submit() {
			if (this.info_data.id) {
				// 修改
				dict_api.update(this.info_data.id, this.info_data, () => {
					this.$message.success("修改成功");
					this.info_dialog = false;
					this.get_dict_list();
				});
			} else {
				// 新增
				dict_api.save(this.type, this.info_data, () => {
					this.$message.success("新增成功");
					this.info_dialog = false;
					this.get_dict_list();
				});
			}
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
.dict-menu {
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	width: 200px;
}
.dict-content {
	position: absolute;
	top: 0;
	left: 220px;
	right: 0;
	bottom: 0;
}
.dict-option {
	position: absolute;
	bottom: 20px;
	right: 20px;
}
</style>