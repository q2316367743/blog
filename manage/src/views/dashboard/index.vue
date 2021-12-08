<template>
	<div class="app-container">
		<div class="one-row">
			<el-card>
				<div slot="header">系统信息</div>
				<el-descriptions :column="1">
					<el-descriptions-item label="系统名称：">{{
						base.os.family
					}}</el-descriptions-item>
					<el-descriptions-item label="系统版本：">{{
						base.os.version.codeName
					}}</el-descriptions-item>
					<el-descriptions-item label="系统版本号：">{{
						base.os.version.version
					}}</el-descriptions-item>
					<el-descriptions-item label="内部版本号：">{{
						base.os.version.buildNumber
					}}</el-descriptions-item>
					<el-descriptions-item label="系统供应商：">{{
						base.os.manufacturer
					}}</el-descriptions-item>
					<el-descriptions-item label="系统位数："
						>{{ base.os.bitness }}天</el-descriptions-item
					>
				</el-descriptions>
			</el-card>
			<div></div>
			<el-card>
				<div slot="header">
					<span>状态</span>
				</div>
				<div class="home-card">
					<div>
						<div>CPU使用率</div>
						<el-tooltip placement="top" effect="dark">
							<el-progress
								type="circle"
								:percentage="cpu_value"
								class="home-progress"
							></el-progress>
							<span slot="content">
								<div>
									{{ dynamic.cpu.cpuModel }} *
									{{ dynamic.cpu.cpuNum }}
								</div>
								<div>已使用：{{ dynamic.cpu.used }} %</div>
								<div>系统：{{ dynamic.cpu.sys }} %</div>
								<div>空闲：{{ dynamic.cpu.free }} %</div>
							</span>
						</el-tooltip>
						<div>{{ dynamic.cpu.cpuNum }} 核心</div>
					</div>
					<div>
						<div>内存使用率</div>
						<el-tooltip placement="top" effect="dark">
							<el-progress
								type="circle"
								:percentage="member_value"
								class="home-progress"
							></el-progress>
							<span slot="content">
								<div>
									总内存：{{ format(dynamic.memory.total) }}
								</div>
								<div>
									使用中：{{
										format(
											dynamic.memory.total -
												dynamic.memory.available
										)
									}}
								</div>
								<div>
									空闲：{{ format(dynamic.memory.available) }}
								</div>
							</span>
						</el-tooltip>
						<div>
							{{ format(dynamic.memory.available) }} /
							{{ format(dynamic.memory.total) }}
						</div>
					</div>
					<div>
						<div>JVM使用率</div>
						<el-tooltip placement="top" effect="dark">
							<el-progress
								type="circle"
								:percentage="jvm_value"
								class="home-progress"
							></el-progress>
							<span slot="content">
								<div>
									总内存：{{ format(dynamic.jvm.total) }}
								</div>
								<div>使用中：{{ format(dynamic.jvm.use) }}</div>
								<div>
									空闲：{{
										format(
											dynamic.jvm.total - dynamic.jvm.use
										)
									}}
								</div>
							</span>
						</el-tooltip>
						<div>
							{{ format(dynamic.jvm.use) }} /
							{{ format(dynamic.jvm.total) }}
						</div>
					</div>
				</div>
			</el-card>
		</div>
		<div class="two-row">
			<el-card>
				<div slot="header">文章数量</div>
				<div></div>
			</el-card>
			<div></div>
			<el-card>
				<div slot="header">网站数据</div>
				<el-descriptions :column="1">
					<el-descriptions-item label="访问数量">{{
						base.website.accessCount
					}}</el-descriptions-item>
					<el-descriptions-item label="文字数量">{{
						base.website.wordCount
					}}</el-descriptions-item>
					<el-descriptions-item label="文章数量">{{
						base.website.articleCount
					}}</el-descriptions-item>
					<el-descriptions-item label="分类数量">{{
						base.website.categoryCount
					}}</el-descriptions-item>
					<el-descriptions-item label="随后更新时间">{{
						base.website.lastUpdate
					}}</el-descriptions-item>
					<el-descriptions-item label="运行时间"
						>{{ base.website.runTime }}天</el-descriptions-item
					>
				</el-descriptions>
			</el-card>
		</div>
	</div>
</template>

<script>
import statistics_api from "@/api/statistics";
import { format } from "@/utils/field";

export default {
	name: "Dashboard",
	data: () => {
		return {
			interval: -1,
			base: {
				os: {
					bitness: 0,
					version: {
						version: "",
						buildNumber: "",
						codeName: "",
					},
					manufacturer: "",
					family: "",
				},
				website: {
					accessCount: 0,
					wordCount: 0,
					articleCount: 0,
					categoryCount: 0,
					lastUpdate: "",
					runTime: 0,
				},
			},
			dynamic: {
				memory: {
					available: 0,
					total: 1,
				},
				cpu: {
					wait: 0,
					cpuModel: "",
					used: 0.0,
					sys: 0.0,
					cpuNum: 0,
					toTal: 0,
					free: 0.0,
				},
				jvm: {
					use: 0,
					total: 1,
				},
			},
		};
	},
	computed: {
		cpu_value() {
			return parseFloat(
				(this.dynamic.cpu.used + this.dynamic.cpu.sys).toFixed(2)
			);
		},
		member_value() {
			return parseFloat(
				(
					100 -
					(this.dynamic.memory.available /
						this.dynamic.memory.total) *
						100
				).toFixed(2)
			);
		},
		jvm_value() {
			return parseFloat(
				((this.dynamic.jvm.use / this.dynamic.jvm.total) * 100).toFixed(
					2
				)
			);
		},
	},
	created() {
		this.get_base();
		this.interval = setInterval(() => {
			this.get_dynamic();
		}, 1500);
	},
	methods: {
		format,
		get_base() {
			statistics_api.base((res) => {
				this.base = res.data;
			});
		},
		get_dynamic() {
			statistics_api.dynamic(
				(res) => {
					this.dynamic = res.data;
				},
				() => {
					// 报错去除
					clearInterval(this.interval);
					this.interval = setInterval(() => {
						this.get_dynamic();
					}, 1500);
				}
			);
		},
	},
	beforeDestroy() {
		clearInterval(this.interval);
	},
};
</script>

<style scoped>
.home-card {
	display: grid;
	grid-template-columns: 1fr 1fr 1fr;
	grid-template-rows: 1fr;
}

.home-card > div {
	text-align: center;
}

.home-progress {
	margin-top: 10px;
	font-size: 24px;
	margin-bottom: 10px;
	cursor: pointer;
}

.home-tip {
	font-size: 12px;
}

.home-items {
	display: flex;
}

.home-item {
	width: 200px;
	background-color: #212334;
	margin: 0px 12px;
}

.home-item-title {
	width: 100%;
	text-align: center;
	height: 50px;
	line-height: 50px;
	font-size: 15px;
}

.home-item-content {
	width: 100%;
	text-align: center;
	height: 50px;
	font-size: 26px;
}
.home-item-content > a {
	color: #ffffff;
}
.one-row {
	display: grid;
	grid-template-columns: 0.3fr 20px 0.7fr;
	grid-template-rows: 1fr;
}
.two-row {
	margin-top: 20px;
	display: grid;
	grid-template-columns: 0.7fr 20px 0.3fr;
	grid-template-rows: 1fr;
}
</style>
