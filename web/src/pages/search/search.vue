<template>
	<el-col :span="24">
		<div id="search-main">
			<div id="search-title">
				<el-input v-model="title" placeholder="请输入文章标题" @keyup.enter.native="search" style="width: 500px;"></el-input>
			</div>
		</div>
		<el-col :span="6" :xs="1" :sm="2" :md="3" :lg="4" :xl="6" style="min-height:1px;margin-top: 50px;">
		</el-col>
		<el-col :span="12" :xs="22" :sm="20" :md="18" :lg="16" :xl="12" style="margin-top: 50px;">
			<my-article v-for="(article, index) in articles" :key="index" :data="article" @click="toA" style="margin-top: 20px;"></my-article>
			<el-pagination
				background
				layout="prev, pager, next"
				:total="total"
				class="pagination"
				@current-change="currentChange"
			>
			</el-pagination>
		</el-col>
	</el-col>
</template>

<script>
import {findDimensions} from "@/utils/window";
import $ from "jquery";
import article from "@/components/article";
import { getList } from "@/api/article";

export default {
	components: {
		"my-article": article
	},
	data() {
		return {
			winWidth: 0,
			winHeight: 0,
			title: '',
			page: 0,
			size: 5,
			total: 0,
			articles: []
		};
	},
	created() {
		this.title = this.$route.query.title;
		this.search()
	},
	mounted() {
		this.getWindow();
		window.onresize = this.getWindow;
	},
	methods: {
		getWindow() {
			findDimensions((winWidth, winHeight) => {
				this.winWidth = winWidth;
				this.winHeight = winHeight;
			});
			let search_title = $("#search-title");
			let search_main = $("#search-main");
			search_title.css("width", this.winWidth);
			search_main.css("width", this.winWidth);
			let h = this.winHeight / 6;
			if (h > 152) {
				search_title.css("padding-top", h);
				search_main.css("height", h * 2 - 92);
			}

			this.$parent.isSm = this.winWidth < 768;
		},
		toA(href) {
			this.$router.push(href);
		},
		currentChange(page){
			this.page = page;
			this.getArticle();
		},
		search() {
			getList(
				{
					page: this.page,
					size: this.size,
					title: this.title
				},
				(res) => {
					if (res.success) {
						this.total = res.data.total;
						this.articles = res.data.items;
					}
				}
			);
		}
	},
}
</script>

<style>
#search-main {
	text-align: center;
	font-size: 40px;
	color: #ffffff;
	font-weight: bold;
}
#search-title{
	text-align: center;
}
.pagination {
	margin-top: 30px;
	text-align: center;
}
</style>