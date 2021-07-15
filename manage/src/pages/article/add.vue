<template>
	<el-form class="a">
		<el-form-item class="b" label="文章标题">
			<el-input v-model="article.title"></el-input>
		</el-form-item>
		<el-form-item label="文章内容">
			<div id="vditor"></div>
		</el-form-item>
	</el-form>
</template>

<script>
export default {
	data() {
		return {
			article: {
				title: "",
				tag: [],
				categoryId: "",
				content: "",
			},
			contentEditor: "",
		};
	},
	mounted() {},
};
</script>

<style lang="scss">
.a {
    color: aqua;
    .b {
        background-color: #442255;
    }
}
</style>
