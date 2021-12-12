<template>
	<div class="el-card is-always-shadow author-card">
		<div>
			<el-tabs type="card" v-model="type">
				<el-tab-pane label="基础设置" name="1"></el-tab-pane>
				<el-tab-pane label="社交账号" name="2"></el-tab-pane>
				<el-tab-pane label="个人介绍" name="3"></el-tab-pane>
				<el-tab-pane label="账号设置" name="4"></el-tab-pane>
			</el-tabs>
			<el-dropdown
				v-if="type === '3'"
				class="editor-type"
				@command="update_editor_type"
			>
				<span class="el-dropdown-link">
					编辑器类型<i class="el-icon-arrow-down el-icon--right"></i>
				</span>
				<el-dropdown-menu slot="dropdown">
					<el-dropdown-item :disabled="author.type === 1" :command="1"
						>Markdown</el-dropdown-item
					>
					<el-dropdown-item :disabled="author.type === 2" :command="2"
						>富文本</el-dropdown-item
					>
					<el-dropdown-item :disabled="author.type === 3" :command="3"
						>源码</el-dropdown-item
					>
				</el-dropdown-menu>
			</el-dropdown>
			<el-button class="author-update" type="text" @click="update"
				>修改</el-button
			>
		</div>
		<div class="author-content">
			<el-form label-width="80px" v-show="type === '1'">
				<el-form-item label="头像">
					<el-input
						v-model="author.avatar"
						style="width: 600px"
					></el-input>
				</el-form-item>
				<el-form-item label="昵称">
					<el-input
						v-model="author.name"
						style="width: 240px"
					></el-input>
				</el-form-item>
				<el-form-item label="邮箱">
					<el-input
						v-model="author.email"
						style="width: 400px"
					></el-input>
				</el-form-item>
				<el-form-item label="标签">
					<el-tag
						:key="tag"
						v-for="tag in author.tags"
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
				<el-form-item label="简单描述">
					<el-input
						type="textarea"
						:rows="5"
						v-model="author.description"
						style="width: 400px"
					></el-input>
				</el-form-item>
			</el-form>
			<div v-show="type === '2'">
				<el-table :data="author.accounts">
					<el-table-column
						label="序号"
						type="index"
					></el-table-column>
					<el-table-column label="图标" prop="icon"></el-table-column>
					<el-table-column label="模式" prop="mode" width="100px">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.mode === 1">链接</el-tag>
							<el-tag v-else-if="scope.row.mode === 2"
								>图片</el-tag
							>
						</template>
					</el-table-column>
					<el-table-column label="名称" prop="name"></el-table-column>
					<el-table-column label="地址" prop="href"></el-table-column>
					<el-table-column label="操作" width="120px">
						<template slot-scope="scope">
							<el-button
								type="text"
								@click="account_update(scope.row)"
								>修改</el-button
							>
							<el-button
								type="text"
								@click="account_remove(scope.row)"
								style="color: #f56c6c"
								>删除</el-button
							>
						</template>
					</el-table-column>
				</el-table>
				<el-button
					type="primary"
					style="position: absolute; right: 20px; bottom: 20px"
					icon="el-icon-plus"
					circle
					@click="account_add"
				></el-button>
			</div>
			<div v-show="type === '3'">
				<editor
					ref="editor"
					:type="author.type"
					:original_content="author.original_content"
					path="/image"
				></editor>
			</div>
			<div v-show="type === '4'">
				<el-form label-width="120px">
					<el-form-item label="用户名">
						<el-input
							v-model="admin.username"
							style="width: 360px"
						></el-input>
					</el-form-item>
					<el-form-item label="密码">
						<el-input
							type="password"
							v-model="admin.password"
							style="width: 360px"
						></el-input>
					</el-form-item>
					<el-form-item label="再次输入密码">
						<el-input
							type="password"
							v-model="admin.password_again"
							style="width: 360px"
						></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="admin_submit">修改</el-button>
					</el-form-item>
				</el-form>
			</div>
		</div>
		<el-dialog
			:visible.sync="account_dialog"
			width="500px"
			:close-on-click-modal="false"
			:title="(account_data_save ? '新增' : '修改') + '社交账号'"
		>
			<el-form label-width="50px">
				<el-form-item label="图标">
					<el-input v-model="account_data.icon"></el-input>
				</el-form-item>
				<el-form-item label="名称">
					<el-input v-model="account_data.name"></el-input>
				</el-form-item>
				<el-form-item label="模式">
					<el-select v-model="account_data.mode">
						<el-option label="链接" :value="1"></el-option>
						<el-option label="图片" :value="2"></el-option>
					</el-select>
					<span
						style="margin-left: 20px"
						v-if="account_data.mode === 1"
						>鼠标点击图标跳转到链接</span
					>
					<span
						style="margin-left: 20px"
						v-else-if="account_data.mode === 2"
						>鼠标放到图标显示链接图片</span
					>
				</el-form-item>
				<el-form-item label="链接">
					<el-input v-model="account_data.href"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer">
				<el-button type="primary" @click="account_submit">{{
					account_data_save ? "新增" : "修改"
				}}</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import editor from "@/components/editor";

import author_api from "@/api/author";

import { update } from "@/api/user";
import { removeToken } from "@/utils/auth";

export default {
	name: "author",
	components: {
		editor,
	},
	data: () => {
		return {
			type: "1",
			author: {
				description: "",
				avatar: "",
				email: "",
				type: 1,
				content: "",
				tags: [],
				name: "",
				accounts: [],
				original_content: "",
			},
			tag_input: false,
			tag_value: "",
			account: {
				icon: "",
				mode: 1,
				name: "",
				href: "",
			},
			account_dialog: false,
			account_data_save: false,
			account_data: {
				icon: "",
				name: "",
				href: "",
				mode: 1,
			},
			admin: {
				username: "",
				password: "",
				password_again: "",
			},
		};
	},
	created() {
		this.info();
	},
	methods: {
		info() {
			author_api.info(
				(res) => {
					this.author = res.data;
				},
				() => {
					this.$message.error("获取作者信息错误");
				}
			);
		},
		update() {
			this.author.content = this.$refs.editor.get_content();
			this.author.original_content =
				this.$refs.editor.get_original_content();
			author_api.update(
				this.author,
				() => {
					this.$message.success("修改作者信息成功");
				},
				() => {
					this.$message.error("修改失败，错误原因请查看控制台");
				}
			);
		},
		tag_close(tag) {
			this.author.tags.splice(this.author.tags.indexOf(tag), 1);
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
				this.author.tags.push(tag_value);
			}
			this.tag_input = false;
			this.tag_value = "";
		},
		update_editor_type(type) {
			this.author.type = type;
		},
		account_add() {
			this.account_dialog = true;
			this.account_data = {
				icon: "",
				name: "",
				href: "",
				mode: 1,
			};
			this.account_data_save = true;
		},
		account_update(record) {
			this.account_dialog = true;
			this.account_data = record;
			this.account_data_save = false;
		},
		account_submit() {
			if (this.account_data_save) {
				this.author.accounts.push(this.account_data);
			}
			this.account_dialog = false;
		},
		account_remove(record) {
			this.author.accounts.splice(
				this.author.accounts.indexOf(record),
				1
			);
		},
		admin_submit() {
			if (this.admin.password !== this.admin.password_again) {
				this.$message.error("两次密码不一致，请重新输入");
				return;
			}
			update(
				{
					username: this.admin.username,
					password: this.admin.password,
				},
				(res) => {
					if (res.data) {
						this.$message.success("修改成功，请重新登录");
						this.$store.dispatch("user/RESET_STATE");
						removeToken();
                        this.$router.push('/login')
					}
				}
			);
		},
	},
};
</script>

<style scoped>
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
.author-card {
	position: absolute;
	top: 20px;
	left: 20px;
	bottom: 20px;
	right: 20px;
	padding-bottom: 40px;
}

.author-update {
	position: absolute;
	top: 0;
	right: 15px;
}

.editor-type {
	position: absolute;
	top: 12px;
	right: 60px;
	color: #409eff;
	cursor: pointer;
}

.author-content {
	position: absolute;
	top: 60px;
	left: 0;
	bottom: 0;
	right: 0;
	padding: 20px;
	overflow-y: auto;
}

.author-card .el-card__body {
	height: 100%;
	overflow-y: auto;
}

.author-card .author-option {
	float: right;
	padding: 3px 0;
}

.el-card__body .h1 {
	margin-left: 10px;
	margin-top: 0;
}

.item-option {
	position: absolute;
	right: 0;
	top: 0;
}
</style>
