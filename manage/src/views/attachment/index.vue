<template>
	<div class="resource-container">
		<div class="resource-option">
			<div class="resource-path">
				{{ path }}
			</div>
			<div class="resource-option-btn">
				<el-button type="text" @click="exec_command">当前文件夹下执行命令</el-button>
				<el-button type="text" @click="mkdir">新建文件夹</el-button>
				<el-button type="text" @click="ls">刷新</el-button>
				<el-upload
					action=""
					:show-file-list="false"
					:http-request="upload"
					style="margin-left: 10px"
				>
					<el-button type="text">上传</el-button></el-upload
				>
			</div>
		</div>
		<div class="resource-content" v-loading="loading">
			<div
				class="resource-item"
				@click="to_path(get_upper_path(path), true)"
				v-if="path !== '/'"
			>
				../
			</div>
			<div
				class="resource-item"
				v-for="(file, index) in file_list"
				:key="index"
				@click="to_path(file.path, file.is_directory)"
			>
				<div>
					<i class="el-icon-folder" v-if="file.is_directory"></i>
					<i class="el-icon-document" v-else></i>
					<span style="margin-left: 10px">{{ file.name }}</span>
				</div>
				<div>
					<el-button
						type="text"
						:disabled="file.is_directory"
						@click.stop="copy_link(file.path)"
						>复制连接</el-button
					>
					<el-button
						type="text"
						style="color: #f56c6c"
						@click.stop="remove(file.path, file.is_directory)"
						>删除</el-button
					>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="command_dialog">
			<div v-for="(line, index) in command_line" :key="index">
				{{ line }}
			</div>
		</el-dialog>
	</div>
</template>

<script>
import resource_api from "@/api/resource";
import { get_upper_path } from "@/utils/field";

export default {
	name: "index",
	data: () => {
		return {
			path: "/",
			file_list: [],
			loading: false,
			command_dialog: false,
			command_line: [],
		};
	},
	created() {
		this.ls();
	},
	methods: {
		get_upper_path,
		ls() {
			this.loading = true;
			resource_api.ls(this.path, (res) => {
				this.file_list = res.data;
				this.loading = false;
			});
		},
		to_path(path, flag) {
			if (!flag) {
				return;
			}
			this.path = path;
			this.ls();
		},
		copy_link(path) {
			resource_api.link(path, (res) => {
				if (res.data !== "") {
					this.$copyText(res.data)
						.then(() => {
							this.$message.success("已复制到剪切板");
						})
						.catch(() => {
							this.$message.error("复制失败");
						});
				}
			});
		},
		mkdir() {
			this.$prompt("请输入文件夹名称", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
			}).then(({ value }) => {
				resource_api.mkdir(
					{
						path: this.path,
						name: value,
					},
					() => {
						this.$message.success("创建成功");
						this.ls();
					},
					() => {
						this.$message.error("创建失败");
					}
				);
			});
		},
		remove(path, is_directory) {
			this.$confirm(
				`此操作将永久删除该${
					is_directory ? "文件夹" : "文件"
				}, 是否继续?`,
				"提示",
				{
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning",
				}
			).then(() => {
				resource_api.rm(
					path,
					() => {
						this.$message.success("删除成功!");
						this.ls();
					},
					() => {
						this.$message.error("删除失败");
					}
				);
			});
		},
		upload(param) {
			let file = param.file;
			let form = new FormData();
			form.append("file", file);
			form.append("path", this.path);
			resource_api.upload(
				form,
				() => {
					this.$message.success("上传成功");
					this.ls();
				},
				() => {
					this.$message.error("上传失败");
				}
			);
		},
		exec_command() {
			this.$prompt("请输入命令", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
			}).then(({ value }) => {
				resource_api.exec_command(
					this.path,
					value,
					(res) => {
						this.command_line = res.data;
						this.command_dialog = true;
					}
				);
			});
		},
	},
};
</script>

<style scoped>
.resource-container {
	position: absolute;
	top: 20px;
	left: 20px;
	right: 20px;
	bottom: 20px;
	background-color: #ffffff;
}
.resource-option {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	height: 50px;
	padding: 10px 20px;
	border-bottom: #ebeef5 solid 1px;
	display: flex;
	justify-content: space-between;
}
.resource-path {
	height: 30px;
	line-height: 30px;
}
.resource-option-btn {
	display: flex;
}
.resource-content {
	position: absolute;
	top: 50px;
	left: 0;
	right: 0;
	bottom: 0;
	padding: 10px;
    overflow-y: auto;
}
.resource-item {
	width: 100%;
	height: 48px;
	line-height: 32px;
	padding: 8px 16px;
	border-bottom: #eeeeee solid 1px;
	cursor: pointer;
	display: flex;
	justify-content: space-between;
}
.resource-item:hover {
	background-color: #f2f2f2;
}
</style>
