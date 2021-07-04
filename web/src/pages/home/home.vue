<template>
	<div class="main">
		<div id="welcome">
			<div id="main">
				<div id="title">你好</div>
				<p id="subtitle"></p>
			</div>
			<div id="down">
				<i
					class="el-icon-arrow-down"
					@click="downMain"
				></i>
			</div>
		</div>
		<el-row style="margin-top: 20px">
			<el-col :span="5" :xs="2" :sm="3" :md="4" :lg="4" :xl="5" style="min-height:1px;">
			</el-col>
			<el-col :span="14" :xs="20" :sm="18" :md="16" :lg="16" :xl="14">
				<el-row :gutter="20">
					<el-col
						:xs="24"
						:sm="10"
						:md="10"
						:lg="7"
						:xl="6"
						:span="6"
					>
						<broadside></broadside>
					</el-col>
					<el-col
						:xs="24"
						:sm="14"
						:md="14"
						:lg="17"
						:xl="18"
						:span="18"
					>
						<el-card
							shadow="hover"
							class="item"
							v-if="articles.length === 0"
						>
							<div style="text-align: center;line-height: 242px;font-size: 36px;">
								没有文章
							</div>
						</el-card>
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
				</el-row>
			</el-col>
		</el-row>
	</div>
</template>

<script>
import $ from "jquery";
import TypeIt from "typeit";
import broadside from "@/components/broadside";
import article from "@/components/article";
import { findDimensions } from "@/utils/window";
import { getList } from "@/api/article";

export default {
	components: {
		broadside,
        "my-article": article
	},
	data() {
		return {
			winWidth: 0,
			winHeight: 0,
			page: 0,
			limit: 5,
			total: 0,
			articles: [],
		};
	},
	created() {
		this.getArticle();
	},
	mounted() {
		this.getWindow();
		window.onresize = this.getWindow;
		new TypeIt("#subtitle", {
			strings: [
				"真正的大师永远怀着一颗学徒的心。",
				"任何时候都不要被骄傲蒙蔽了双眼！",
				"绝对不要坐等胜利的到来。",
				"愤怒，带来冲动。",
			],
			speed: 150,
			loop: true,
			lifeLike: true,
			startDelay: 500,
			breakLines: false,
		}).go();
	},
	methods: {
		getWindow() {
			findDimensions((winWidth, winHeight) => {
				this.winWidth = winWidth;
				this.winHeight = winHeight;
			});
            let welcome = $("#welcome");
            welcome.css("width", this.winWidth);
            welcome.css("height", this.winHeight);
            let main = $("#main");
            main.css("width", this.winWidth);
            main.css("padding-top", this.winHeight / 2 - 92);
            let down = $("#down");
            down.css("width", this.winWidth);
            down.css("padding-top", this.winHeight / 2 - 115);

			this.$parent.isSm = this.winWidth < 768;
		},
		downMain() {
			// 下滑
			$("#app").animate({ scrollTop: this.winHeight + "px" }, 800);
		},
		getArticle() {
			getList(
				{
					page: this.page,
					limit: this.limit,
				},
				(res) => {
					if (res.success) {
						this.total = res.data.total;
						this.articles = res.data.items;
					}
				}
			);
		},
		currentChange(page) {
			this.page = page;
			this.getArticle();
		},
		toA(href) {
			this.$router.push(href);
		},
	},
};
</script>

<style scoped>
#main {
	text-align: center;
}
#title {
	font-size: 40px;
	font-weight: bold;
	color: #ffffff;
}
#subtitle {
	font-size: 30px;
	color: #ffffff;
	margin: 10px;
}
#down {
	color: #ffffff;
	font-size: 30px;
	text-align: center;
	animation: shadow 1.5s linear infinite;
}
@keyframes shadow {
	0%,
	100% {
		transform: scaleY(1);
	}
	50% {
		transform: scaleY(1.1);
	}
}

.item {
	margin-top: 20px;
    cursor: pointer;
}

.pagination {
	margin-top: 30px;
	text-align: center;
}
</style>