<template>
	<div class="app-container">
		<el-card shadow="never">
			<div slot="header">大分类管理</div>
			<div class="one-category">
				<div
					v-for="(category, index) in categories"
					:key="index"
					class="one-category-item"
					:class="
						category_id === category.id
							? 'one-category-item-choose'
							: ''
					"
					@click="one_click(category.id)"
				>
					{{ category.name }}
				</div>
				<div
					class="one-category-item one-category-plus"
					@click="save_one"
				>
					+
				</div>
			</div>
		</el-card>
		<div class="el-card" style="margin-top: 20px">
			<div class="el-card__header">
				<span>{{ current.name }}</span
				><el-button
					type="text"
					@click="update_one"
					style="margin: 0 10px;padding: 0;"
				><i class="el-icon-edit"></i></el-button
				><el-button
					style="float: right; padding: 3px 0"
					type="text"
					@click="save_two"
					>新增</el-button
				>
			</div>
			<div class="two-category">
				<template v-for="(item, index) in current.children">
					<div :key="index" style="display: flex">
						<div class="two-category-item">
							{{ item.name }}
						</div>
						<el-button
							type="text"
							icon="el-icon-edit"
							@click="update_two(item.id, item.name)"
							style="margin-left: 10px;padding: 0;"
						></el-button>
					</div>
					<div class="category-divider" :key="item.name"></div
				></template>
			</div>
		</div>
	</div>
</template>

<script>
import category_api from "@/api/category";

export default {
	name: "category",
	data: () => {
		return {
			categories: [],
			category_id: 0,
		};
	},
	created() {
		this.list();
	},
	computed: {
		current() {
			for (let item of this.categories) {
				if (item.id === this.category_id) {
					return item;
				}
			}
			return {
				name: "",
				children: [],
			};
		},
	},
	methods: {
		list() {
			category_api.list((res) => {
				this.categories = res.data;
				this.category_id = this.category_id == 0 ? res.data[0].id : this.category_id;
			});
		},
		one_click(id) {
			this.category_id = id;
		},
		save_one() {
			this.$prompt("请输入分类名称", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
                closeOnClickModal: false
			}).then(({ value }) => {
				if (value === "") {
					this.$message.error("请输入分类名称");
					return;
				}
				category_api.save(
					{
						name: value,
					},
					() => {
						this.$message.success("新增一级分类成功");
						this.list();
					}
				);
			});
		},
		save_two() {
			this.$prompt("请输入新分类名称", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
                closeOnClickModal: false
			}).then(({ value }) => {
				if (value === "") {
					this.$message.error("请输入新分类名称");
					return;
				}
				category_api.save(
					{
						name: value,
						pid: this.category_id,
					},
					() => {
						this.$message.success("新增一级分类成功");
						this.list();
					}
				);
			});
		},
		update_one() {
			this.update_two(this.category_id, this.current.name);
		},
		update_two(id, name) {
			this.$prompt("请输入新分类名称", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
                closeOnClickModal: false,
				inputValue: name,
			}).then(({ value }) => {
				if (value === "") {
					this.$message.error("请输入新分类名称");
					return;
				}
				category_api.update(
					id,
					{
						name: value,
					},
					() => {
						this.$message.success("修改分类分类成功");
						this.list();
					}
				);
			});
		},
	},
};
</script>

<style scoped>
.one-category {
	display: flex;
}
.one-category-item {
	margin: 10px;
	border: #000000 solid 1px;
	padding: 10px;
	cursor: pointer;
}
.one-category-plus {
	width: 50px;
	text-align: center;
}
.one-category-item-choose {
	color: #409eff;
	border: #409eff solid 1px;
	font-weight: bold;
}
.two-category {
	padding: 20px;
}
.two-category-item {
	padding: 0 15px;
	line-height: 30px;
}
.category-divider {
	display: block;
	height: 1px;
	width: 100%;
	background-color: #dcdfe6;
	position: relative;
	margin: 2px 0;
}
</style>
