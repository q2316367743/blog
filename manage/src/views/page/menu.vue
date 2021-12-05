<template>
	<div class="container">
		<div class="menu-tree">
			<el-tree
				:data="menus"
				:props="props"
				@node-click="node_click"
				v-loading="menu_load"
			>
				<div class="menu-tree-item" slot-scope="{ node, data }">
					<div>{{ node.label }}</div>
					<div>
						<el-button
							type="text"
							size="mini"
							v-if="handle(data)"
							@click.stop="() => remove(data.id)"
						>
							删除当前节点
						</el-button>
						<el-button
							type="text"
							size="mini"
							@click.stop="() => open_save(data.id)"
						>
							新增子节点
						</el-button>
					</div>
				</div>
			</el-tree>
		</div>
		<div></div>
		<div class="menu-option">
			<el-form v-if="is_update" label-width="80px">
				<el-form-item label="图标">
					<el-input v-model="menu.icon"></el-input>
				</el-form-item>
				<el-form-item label="名称">
					<el-input v-model="menu.name"></el-input>
				</el-form-item>
				<el-form-item label="打开方式">
					<el-switch
						v-model="menu.target"
						:active-value="1"
						:inactive-value="0"
						active-text="打开新页面"
						inactive-text="当前页面打开"
					>
					</el-switch>
				</el-form-item>
				<el-form-item label="链接">
					<div class="href-tip">
						<el-input v-model="menu.href"></el-input>
						<div class="href-tip-text">
							<el-tooltip
								class="item"
								effect="light"
								content="建议填写绝对路径，相对路径相对于网站根目录"
								placement="bottom"
							>
								<el-button type="text">?</el-button>
							</el-tooltip>
						</div>
					</div>
				</el-form-item>
                <el-form-item label="顺序">
                    <el-input-number v-model="menu.sequence"></el-input-number>
                </el-form-item>
				<el-form-item>
					<el-button type="primary" @click="update">更新</el-button>
				</el-form-item>
			</el-form>
		</div>
		<el-dialog :visible.sync="save_dialog">
			<el-form label-width="80px">
				<el-form-item label="图标">
					<el-input v-model="sava_data.icon"></el-input>
				</el-form-item>
				<el-form-item label="名称">
					<el-input v-model="sava_data.name"></el-input>
				</el-form-item>
				<el-form-item label="打开方式">
					<el-switch
						v-model="sava_data.target"
						:active-value="1"
						:inactive-value="0"
						active-text="打开新页面"
						inactive-text="当前页面打开"
					>
					</el-switch>
				</el-form-item>
				<el-form-item label="链接">
					<div class="href-tip">
						<el-input v-model="sava_data.href"></el-input>
						<div class="href-tip-text">
							<el-tooltip
								class="item"
								effect="light"
								content="建议填写绝对路径，相对路径相对于网站根目录"
								placement="bottom"
							>
								<el-button type="text">?</el-button>
							</el-tooltip>
						</div>
					</div>
				</el-form-item>
                <el-form-item label="顺序">
                    <el-input-number v-model="sava_data.sequence"></el-input-number>
                </el-form-item>
				<el-form-item>
					<el-button type="primary" @click="save_submit"
						>新增</el-button
					>
				</el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
import menu_api from "@/api/menu";

export default {
	data: () => {
		return {
			menus: [],
			props: {
				children: "children",
				label: "name",
			},
			menu_load: false,
			is_update: false,
			menu: {
				id: 0,
				icon: "",
				name: "",
				target: 0,
				href: "",
                sequence: 0,
				create_time: "",
				children: [],
				pid: 0,
			},
			save_dialog: false,
			sava_data: {
				icon: "",
				name: "",
				target: 0,
				href: "",
                sequence: 0,
			},
		};
	},
	created() {
		this.list();
	},
	methods: {
		list() {
			this.menu_load = true;
			menu_api.list(
				(res) => {
					this.menus = res.data;
					this.menu_load = false;
				},
				() => {
					this.menu_load = false;
				}
			);
		},
		handle(data) {
			if (!data.children) {
				return true;
			}
			if (data.children.length == 0) {
				return true;
			}
			return false;
		},
		node_click(data) {
			this.is_update = true;
			// 使用深拷贝
			this.menu = Object.assign({}, data);
		},
		update() {
			if (this.is_update) {
				menu_api.update(this.menu.id, this.menu, () => {
					this.$message.success("更新成功");
					this.list();
				});
			}
		},
		remove(id) {
			this.$confirm("此操作将永久删除该菜单, 是否继续?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			}).then(() => {
				menu_api.remove(id, () => {
					this.$message.success("删除成功");
					this.list();
				});
			});
		},
		open_save(id) {
			this.sava_data = {
				icon: "",
				name: "",
				target: 0,
				href: "",
                sequence: 0,
				pid: id,
			};
			this.save_dialog = true;
		},
		save_submit() {
			menu_api.save(this.sava_data, () => {
				this.$message.success("新增成功");
				this.save_dialog = false;
				this.list();
			});
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
	display: grid;
	grid-template-rows: 1fr;
	grid-template-columns: 1fr 20px 1fr;
}
.menu-tree {
	background-color: #ffffff;
	padding: 20px;
}
.menu-tree-item {
	width: 100%;
	display: flex;
	justify-content: space-between;
}
.menu-option {
	background-color: #ffffff;
	padding: 20px;
}
.href-tip {
	display: grid;
	grid-template-rows: 1fr;
	grid-template-columns: 1fr 40px;
}
.href-tip-text {
	text-align: center;
	height: 40px;
}
</style>
