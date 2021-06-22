<template>
	<el-col :span="24">
		<div id="about-main">
			<div id="about-title">
				<div>关于自己</div>
			</div>
		</div>
		<el-col :span="6" :xs="1" :sm="2" :md="3" :lg="4" :xl="5" style="min-height:1px;">
			</el-col>
		<el-col :span="12" :xs="22" :sm="20" :md="18" :lg="16" :xl="12">
			<el-card shadow="always">
				<div class="title">云落天都</div>
				<div class="content">
					<h1>基本信息</h1>
					<p>👨基本情况：大四在读，计算机科学与技术专业。</p>
					<p>
						🎫昵称：云落天都。云落天都，月出九皋。
					</p>
					<p>🌍地址：目前在湖北孝感。</p>
					<p>
						📧邮箱：<a href="mailto:m17762618644@163.com"
							>m17762618644@163.com</a
						>
					</p>
					<p>🔨技术方向：主Java，会考虑学一点前端和大数据。</p>
					<p>💓兴趣爱好：游戏，编程。</p>
					<p>📝写作风格：一般以学习笔记为主，少量的心得感悟。</p>
				</div>
			</el-card>
		</el-col>
	</el-col>
</template>

<script>
import { findDimensions } from "@/utils/window";
import $ from "jquery";
export default {
	data() {
		return {
			winWidth: 0,
			winHeight: 0,
		};
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
			$("#about-title").css("width", this.winWidth);
			$("#about-main").css("width", this.winWidth);
			let h = this.winHeight / 3;
			if (h > 152) {
				$("#about-title").css("padding-top", h);
				$("#about-main").css("height", h * 2 - 92);
			}

			this.$parent.isSm = this.winWidth < 768;
		},
	},
};
</script>

<style scoped>
img{
	max-width: 100%;
}
h1{
	padding: 10px 0px;
}
#about-main {
	text-align: center;
	font-size: 40px;
	color: #ffffff;
	font-weight: bold;
}
.title {
	color: #49b1f5;
	text-align: center;
	font-size: 30px;
	margin-top: 20px;
}
.content {
	font-size: 16px;
	padding-left: 10px;
}

</style>