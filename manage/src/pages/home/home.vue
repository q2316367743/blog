<template>
	<div class="box">
		<!-- 欢迎 -->
		<div class="content">
			<el-row :gutter="20">
				<el-col
					:span="24"
					:xs="24"
					:sm="12"
					:lg="6"
					style="margin-top: 1em"
				>
					<el-card shadow="hover">
						<div slot="header" class="card-head">
							<div>访问量</div>
							<div>
								<el-tag type="info">周</el-tag>
							</div>
						</div>
						<div
							class="card-content"
							v-text="base.visit.count"
						></div>
						<div class="card-footer">
							<div>总计访问量</div>
							<div v-text="base.visit.sum"></div>
						</div>
					</el-card>
				</el-col>
				<el-col
					:span="24"
					:xs="24"
					:sm="12"
					:lg="6"
					style="margin-top: 1em"
				>
					<el-card shadow="hover">
						<div slot="header" class="card-head">
							<div>活跃用户</div>
							<div>
								<el-tag>月</el-tag>
							</div>
						</div>
						<div
							class="card-content"
							v-text="base.user.count"
						></div>
						<div class="card-footer">
							<div>总计活跃人数</div>
							<div v-text="base.user.sum"></div>
						</div>
					</el-card>
				</el-col>
				<el-col
					:span="24"
					:xs="24"
					:sm="12"
					:lg="6"
					style="margin-top: 1em"
				>
					<el-card shadow="hover">
						<div slot="header" class="card-head">
							<div>运行项目</div>
							<div>
								<el-tag type="success">个</el-tag>
							</div>
						</div>
						<div
							class="card-content"
							v-text="base.project.count"
						></div>
						<div class="card-footer">
							<div>总计部署项目</div>
							<div v-text="base.project.sum"></div>
						</div>
					</el-card>
				</el-col>
				<el-col
					:span="24"
					:xs="24"
					:sm="12"
					:lg="6"
					style="margin-top: 1em"
				>
					<el-card shadow="hover">
						<div slot="header" class="card-head">
							<div>部署服务</div>
							<div>
								<el-tag type="warning">个</el-tag>
							</div>
						</div>
						<div
							class="card-content"
							v-text="base.service.count"
						></div>
						<div class="card-footer">
							<div>总计部署服务</div>
							<div v-text="base.service.sum"></div>
						</div>
					</el-card>
				</el-col>
			</el-row>
			<el-card shadow="hover" style="margin-top: 20px">
				<div slot="header">访问量</div>
				<el-row>
					<el-col :span="16">
						<div
							ref="chart"
							style="width: 100%; height: 280px"
						></div>
					</el-col>
					<el-col :span="8">
						<div class="card-list">
							<p class="card-normal-font">月访问人数</p>
							<span>同比增长</span>
							<el-progress
								:text-inside="true"
								:stroke-width="20"
								:percentage="30"
								style="margin-rop: 10px"
							></el-progress>
						</div>
						<div class="card-list">
							<p class="card-normal-font">月留言人数</p>
							<span>同比增长</span>
							<el-progress
								:text-inside="true"
								:stroke-width="20"
								:percentage="10"
								style="margin-rop: 10px"
							></el-progress>
						</div>
					</el-col>
				</el-row>
			</el-card>
			<el-row :gutter="20">
				<el-col :span="24" :md="8" style="margin-top: 20px">
					<el-card shadow="hover">
						<div slot="header">用户留言</div>
						<div>
							<div
								class="card-item"
								v-for="(item, index) in comments"
								:key="index"
							>
								<h3 v-text="item.name"></h3>
								<p v-text="item.content"></p>
								<span v-text="item.createTime"></span>
								<el-button
									type="text"
									class="card-item-btn"
									@click="reply(item)"
									>回复</el-button
								>
							</div>
						</div>
					</el-card>
				</el-col>
				<el-col :span="24" :md="16" style="margin-top: 20px">
					<el-card shadow="hover">
						<div slot="header">用户全国分布</div>
						<el-row>
							<el-col :span="8">
								<el-table :data="userData" height="310" border>
									<el-table-column type="index" label="排名">
									</el-table-column>
									<el-table-column prop="name" label="省份">
									</el-table-column>
									<el-table-column prop="value" label="人数">
									</el-table-column>
								</el-table>
							</el-col>
							<el-col :span="15">
								<div
									ref="map"
									style="width: 100%; height: 310px"
								></div>
							</el-col>
						</el-row>
					</el-card>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
import * as echarts from "echarts";
import { mapGetters } from "vuex";
import home from "@/api/home";

export default {
	data() {
		return {
			base: {
				visit: {
					count: 0,
					sum: 0,
				},
				user: {
					count: 0,
					sum: 0,
				},
				project: {
					count: 0,
					sum: 0,
				},
				service: {
					count: 0,
					sum: 0,
				},
			},
			comments: [],
			total: 0,
			page: 1,
			userData: [],
		};
	},
	methods: {
		generateVisit(visit, comment) {
			let option = {
				tooltip: {
					trigger: "axis",
				},
				legend: {
					data: ["访问量", "评论量"],
				},
				grid: {
					left: "3%",
					right: "4%",
					bottom: "3%",
					containLabel: true,
				},
				toolbox: {
					feature: {
						saveAsImage: {},
					},
				},
				xAxis: {
					type: "category",
					boundaryGap: false,
					data: [
						"一月",
						"二月",
						"三月",
						"四月",
						"五月",
						"六月",
						"七月",
						"八月",
						"九月",
						"十月",
						"十一月",
						"十二月",
					],
				},
				yAxis: {
					type: "value",
				},
				series: [
					{
						name: "访问量",
						type: "line",
						stack: "总量",
						data: visit,
					},
					{
						name: "评论量",
						type: "line",
						stack: "总量",
						data: comment,
					},
				],
			};
			let chart = this.$refs.chart;
			let myChart = echarts.init(chart);
			myChart.setOption(option);
			window.addEventListener("resize", function () {
				myChart.resize();
			});
			this.$on("hook:destroyed", () => {
				window.removeEventListener("resize", function () {
					myChart.resize();
				});
			});
		},
		generateMap(data) {
			let map = this.$refs.map;
			let myChart = echarts.init(map);
			myChart.showLoading();
			let option = {
				title: {
					text: "全国用户分布图",
					subtext: "不完全统计",
					left: "left",
				},
				tooltip: {
					trigger: "item",
					showDelay: 0,
					transitionDuration: 0.2,
					formatter: function (params) {
						let value = (params.value + "").split(".");
						value = value[0].replace(
							/(\d{1,3})(?=(?:\d{3})+(?!\d))/g,
							"$1,"
						);
						if (value != "NaN") {
							return (
								"全国用户分布图<br/>" +
								params.name +
								": " +
								value
							);
						} else {
							return "全国用户分布图<br/>" + params.name + ": 0";
						}
					},
				},
				visualMap: {
					left: "right",
					min: 1,
					max: 100,
					inRange: {
						color: [
							"#313695",
							"#4575b4",
							"#74add1",
							"#abd9e9",
							"#e0f3f8",
							"#ffffbf",
							"#fee090",
							"#fdae61",
							"#f46d43",
							"#d73027",
							"#a50026",
						],
					},
					text: ["High", "Low"], // 文本，默认为数值文本
					calculable: true,
				},
				toolbox: {
					show: true,
					//orient: 'vertical',
					left: "right",
					top: "top",
					feature: {
						dataView: { readOnly: false },
						restore: {},
						saveAsImage: {},
					},
				},
				series: [
					{
						name: "全国用户分布图",
						type: "map",
						roam: true,
						map: "China",
						emphasis: {
							label: {
								show: true,
							},
						},
						// 文本位置修正
						textFixed: {
							Alaska: [20, -20],
						},
						data: data,
					},
				],
			};
			this.$.get("./China.json", function (usaJson) {
				myChart.hideLoading();
				echarts.registerMap("China", usaJson);
				myChart.setOption(option);
			});

			// 排序，生成图标
			this.userData = data
				.sort(function (a, b) {
					return b.value - a.value;
				})
				.slice(0, 5);
		},
		reply(item) {
			let that = this;
			this.layer.prompt(
				{
					formType: 2,
					title: "回复" + item.name,
					area: ["350px", "120px"], //自定义文本域宽高
				},
				function (value, index) {
					that.layer.msg(item.id + "---" + value); //得到value
					that.layer.close(index);
				}
			);
		},
		loadData() {
			//登录后才可
			home.base((res) => {
				if (res.success) {
					this.base = res.data.item;
				}
			});
			home.comment((res) => {
				if (res.success) {
					this.comments = res.data.items;
					this.total = res.data.total;
				}
			});
			// 获取数据
			home.visit((res) => {
				if (res.success) {
					this.generateVisit(
						res.data.item.visit,
						res.data.item.comment
					);
				}
			});
			home.user((res) => {
				if (res.success) {
					this.generateMap(res.data.items);
				}
			});
		},
	},
	computed: {
		...mapGetters(["isLogin"]),
	},
	watch: {
		isLogin: function () {
			if (this.isLogin) {
				this.loadData();
			}
		},
	},
	mounted() {
		if (this.isLogin) {
			this.loadData();
		}
	},
};
</script>

<style scoped>
.card-head {
	display: flex;
	justify-content: space-between;
	line-height: 32px;
}
.card-content {
	font-size: 36px;
	color: #666;
	line-height: 36px;
	padding-bottom: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	word-break: break-all;
	white-space: nowrap;
}
.card-footer {
	display: flex;
	justify-content: space-between;
	padding-top: 5px;
}

.card-list {
	padding: 15px;
}
.card-normal-font {
	padding-bottom: 10px;
	font-size: 20px;
	color: #666;
	line-height: 24px;
}
.card-item {
	position: relative;
	padding: 10px 0;
	border-bottom: 1px solid #eee;
}
.card-item > h3 {
	padding-bottom: 5px;
	font-weight: 700;
}
.card-item > p {
	padding-bottom: 10px;
	letter-spacing: 1px;
}
.card-item > span {
	color: #999999;
}
.card-item:hover .card-item-btn {
	display: inline;
}
.card-item-btn {
	position: absolute;
	right: 5px;
	bottom: 0px;
	display: none;
}
</style>