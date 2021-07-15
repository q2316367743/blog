<template>
	<div class="vue-box">
		<div class="c-panel">
			<!-- 参数栏 -->
			<div class="c-title">检索参数</div>
			<el-form :inline="true" size="mini">
				<el-form-item label="标题：">
					<el-input
						v-model="search.title"
						placeholder="模糊查询"
					></el-input>
				</el-form-item>
				<el-form-item label="文章分类：">
					<el-select
						v-model="search.category"
						placeholder="请选择分类"
					>
						<el-option label="全部" :value="0"> </el-option>
						<el-option
							v-for="(item, index) in category"
							:key="index"
							:label="item.name"
							:value="item.id"
						>
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item style="min-width: 10px">
					<el-button type="primary" icon="el-icon-search"
						>查询</el-button
					>
					<el-button
						type="primary"
						icon="el-icon-plus"
						@click="article_info = true"
						>增加</el-button
					>
				</el-form-item>
				<br />
				<el-form-item label="综合排序：">
					<el-checkbox-group v-model="search.order_by_desc">
						<el-checkbox :label="1">创建时间</el-checkbox>
						<el-checkbox :label="2">更新时间</el-checkbox>
						<el-checkbox :label="3">浏览人数</el-checkbox>
						<el-checkbox :label="4">文章字数</el-checkbox>
						<el-checkbox :label="5">评论数</el-checkbox>
					</el-checkbox-group>
				</el-form-item>
			</el-form>
		</div>
		<el-dialog
			title="新增文章"
			:visible.sync="article_info"
			:modal-append-to-body="false"
			custom-class="index"
			fullscreen
		>
			<add></add>
		</el-dialog>
	</div>
</template>

<script>
import add from "./add.vue";

export default {
	name: "list",
	components: {
		add,
	},
	data() {
		return {
			search: {
				title: "",
				category: 0,
				order_by_desc: [],
			},
			category: [],
			article_info: false,
		};
	},
	created() {},
};
</script>

<style scoped>
</style>