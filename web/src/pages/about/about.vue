<template>
	<el-col :span="24">
		<div id="about-main">
			<div id="about-title">
				<div>关于自己</div>
			</div>
		</div>
		<el-col :span="6" :xs="1" :sm="2" :md="3" :lg="4" :xl="6" style="min-height:1px;">
			</el-col>
		<el-col :span="12" :xs="22" :sm="20" :md="18" :lg="16" :xl="12">
			<el-card shadow="always">
				<div class="about-title">{{ about.name }}</div>
				<div class="about-content" v-html="about.base_info"></div>
			</el-card>
		</el-col>
	</el-col>
</template>

<script>
import { findDimensions } from "@/utils/window";
import $ from "jquery";

import { getAbout } from "@/api/global";

export default {
	data() {
		return {
			winWidth: 0,
			winHeight: 0,
            about: {
                name: '云落天都',
                base_info: ''
            }
		};
	},
    created() {
        getAbout(res=> {
            this.about = res.data.item;
        })
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
			let about_title = $("#about-title");
			let about_main = $("#about-main");
			about_title.css("width", this.winWidth);
			about_main.css("width", this.winWidth);
			let h = this.winHeight / 3;
			if (h > 152) {
				about_title.css("padding-top", h);
				about_main.css("height", h * 2 - 92);
			}

			this.$parent.isSm = this.winWidth < 768;
		},
	},
};
</script>

<style>
img{
	max-width: 100%;
}
h1{
	padding: 10px 0;
}
#about-main {
	text-align: center;
	font-size: 40px;
	color: #ffffff;
	font-weight: bold;
}
.about-title {
	color: #49b1f5;
	text-align: center;
	font-size: 30px;
	margin-top: 20px;
}
.about-content {
	font-size: 16px;
	padding-left: 10px;
}

</style>