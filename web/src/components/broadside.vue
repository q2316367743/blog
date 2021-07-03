<template>
	<div>
		<el-card shadow="hover" class="card" style="text-align: center">
			<div>
				<el-avatar :size="100" :src="admin.avatar"></el-avatar>
			</div>
			<div class="admin-name" v-text="admin.name"></div>
			<div class="admin-describe" v-text="admin.description"></div>
			<div class="admin-web">
				<el-row type="flex" justify="space-between">
					<el-col :span="12">
						<router-link tag="a" to="/shijianzhou">
							<div>文章</div>
							<div
								v-text="webInfo.article_count"
								style="margin-top: 5px"
							></div>
						</router-link>
					</el-col>
					<el-col :span="12">
						<router-link tag="a" to="/category">
							<div>分类</div>
							<div
								v-text="webInfo.category_count"
								style="margin-top: 5px"
							></div>
						</router-link>
					</el-col>
				</el-row>
			</div>
			<div class="admin-gitee" @click="toTarget(admin.gitee)">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-mayun"></use>
				</svg>
				<span>&emsp;我的Gitee</span>
			</div>
			<div class="admin-other">
				<img
					v-for="other in admin.other"
					:key="other.name"
					:src="other.icon"
					:alt="other.name"
					:title="other.name"
					@click="toTarget(other.href)"
				/>
			</div>
		</el-card>
		<el-card shadow="hover" class="card">
			<div class="card-title">
				<svg class="icon" aria-hidden="true">
					<use xlink:href="#icon-gonggao"></use>
				</svg>
				<span>公告</span>
			</div>
			<div class="card-content">
				<div
					v-for="(notice, index) in notices"
					:key="index"
                    v-text="notice.title"
                    @click="open_notice(notice.content)"
                    style="cursor: pointer"
				>
                    <el-popover
                        placement="top-start"
                        :title="notice.title"
                        width="200"
                        trigger="hover"
                        :content="notice.content">
                    </el-popover>
                </div>
			</div>
		</el-card>
		<el-card shadow="hover" class="card">
			<div class="card-title">
				<i class="iconfont icon-biaoqian my-icon"></i>
				<span>分类</span>
			</div>
            <div class="card-content">
				<div v-for="(item, index) in category" :key="index" class="category" @click="toA('/category/' + item.id)">
					<div v-text="item.name"></div>
					<div v-text="item.article_count"></div>
				</div>
			</div>
		</el-card>
		<el-card shadow="hover" class="card">
			<div class="card-title">
				<i class="my-icon el-icon-info"></i>
				<span>网站资讯</span>
			</div>
            <div class="card-content">
                <div class="info-item">
                    <div>文章数目：</div>
                    <div v-text="webInfo.article_count"></div>
                </div>
                <div class="info-item">
                    <div>以运行时间：</div>
                    <div>{{webInfo.run_time}}&emsp;天</div>
                </div>
                <div class="info-item">
                    <div>文章总字数：</div>
                    <div>{{webInfo.word_count}}&emsp;K</div>
                </div>
                <div class="info-item">
                    <div>本站总访问量：</div>
                    <div v-text="webInfo.access_count"></div>
                </div>
                <div class="info-item">
                    <div>最后更新时间：</div>
                    <div v-text="webInfo.last_update"></div>
                </div>
            </div>
		</el-card>
	</div>
</template>

<script>
import { getAuthor, getWebInfo, get_category_top } from "@/api/global";

export default {
	data() {
		return {
			admin: {
				avatar: "",
				name: "",
				description: "",
				gitee: "",
				other: [],
			},
			webInfo: {
				article_count: 0,
				tag_count: 0,
				category_count: 0,
				run_time: "1",
                person_count: 0,
				access_count: 0,
				last_update: "",
			},
			tags: [],
			category: [],
			notices: [],
		};
	},
	created() {
        getAuthor((res) => {
			if (res.success) {
				this.admin = res.data.item;
			}
		});
		getWebInfo((res) => {
			if (res.success) {
				this.webInfo = res.data.item;
				this.notices = res.data.items;
			}
		});
        get_category_top(res=>{
			if (res.success) {
				this.category = res.data.items;
			}
		})
	},
	methods: {
		toTarget(target) {
			window.open(target);
		},
		toA(href){
			this.$router.push(href)
		},
        open_notice(content){
            this.$alert(content, '通知内容', {
                dangerouslyUseHTMLString: true
            });
        }
	},
};
</script>

<style scoped>
a {
	color: #000000;
	text-decoration: none;
}
.card {
	margin-top: 20px;
}
.admin-name {
	margin-top: 20px;
	font-size: 20px;
	color: #409eff;
}
.admin-describe {
	font-size: 14px;
	margin-top: 5px;
}
.admin-web {
	margin-top: 20px;
}
.admin-gitee {
	margin-top: 20px;
	background-color: #52a6fa;
	color: #ffffff;
	line-height: 33px;
	height: 100%;
	font-size: 14px;
	cursor: pointer;
}
.admin-gitee:hover,
.admin-gitee:focus,
.admin-gitee:active {
	-webkit-transition: all 0.5s linear;
	-moz-transition: all 0.5s linear;
	-o-transition: all 0.5s linear;
	transition: all 0.5s linear;
	background-color: #303643;
}

.admin-other {
	margin-top: 20px;
	height: 14px;
	margin-bottom: 20px;
	cursor: pointer;
}
.admin-other img {
	border-radius: 16px;
}
.card-title {
	font-size: 20px;
}
.card-title svg {
	font-size: 25px;
	animation-name: scaleDraw; /*关键帧名称*/
	animation-timing-function: ease-in-out; /*动画的速度曲线*/
	animation-iteration-count: infinite; /*动画播放的次数*/
	animation-duration: 2s; /*动画所花费的时间*/
}
.card-title span {
	margin-left: 10px;
}

@keyframes scaleDraw {
	/*定义关键帧、scaleDrew是需要绑定到选择器的关键帧名称*/
	0% {
		transform: scale(1); /*开始为原始大小*/
	}
	25% {
		transform: scale(1.3); /*放大1.3倍*/
	}
	50% {
		transform: scale(1);
	}
	75% {
		transform: scale(1.3);
	}
}

.card-content {
	margin-top: 20px;
}
.card-content div {
	padding: 5px 0;
}

.my-icon {
	font-size: 20px;
}

.info-item{
    display: flex;
    flex-flow: row nowrap;
    justify-content: space-between;
}

.category{
	display: flex;
	justify-content: space-between;
    cursor: pointer;
}
.category>div{
	padding: 5px 5px;
}
.category:hover{
	background-color: rgba(0, 0, 0, 0.1);
}
</style>