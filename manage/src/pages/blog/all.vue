<template>
	<div class="box">
		<!-- 欢迎 -->
		<div class="content">
			<el-card shadow="nover" style="margin-top: 1em">
				<div
					style="
						font-size: 14px;
						font-weight: bold;
						line-height: 2em;
						margin-bottom: 3px;
					"
				>
					检索参数
				</div>
				<!-- 检索参数 -->
				<div style="margin-left: 20px; margin-top: 10px">
					<div style="display: flex">
						<div>
							<span>文章编号：</span>
							<el-input
								size="mini"
								v-model="search.id"
								style="width: 200px; margin-left: 20px"
							></el-input>
						</div>
						<div style="margin-left: 40px">
							<span>文章标题：</span>
							<el-input
								size="mini"
								v-model="search.title"
								style="width: 200px; margin-left: 20px"
							></el-input>
						</div>
						<div style="margin-left: 40px">
							<el-button
								size="mini"
								type="primary"
								@click="executeSearch"
								>搜索</el-button
							>
							<el-button size="mini" @click="clearSearch"
								>清空</el-button
							>
						</div>
					</div>
					<div style="margin-top: 20px">
						<span>综合排序：</span>

						<el-radio-group
							v-model="search.sort"
							@change="executeSearch"
							style="margin-left: 20px"
						>
							<el-radio label="0">发表时间</el-radio>
							<el-radio label="1">喜欢数</el-radio>
							<el-radio label="2">点击数</el-radio>
							<el-radio label="3">分享数</el-radio>
						</el-radio-group>
					</div>
					<el-table
						:data="articles"
						style="width: 100%; margin-top: 20px"
					>
						<el-table-column prop="id" label="编号" width="100">
						</el-table-column>
						<el-table-column prop="title" label="标题" width="180">
						</el-table-column>
						<el-table-column prop="createTime" label="创建日期">
						</el-table-column>
						<el-table-column label="状态"> 草稿 </el-table-column>
						<el-table-column label="操作">
							<template slot-scope="item">
								<el-button
									type="primary"
									size="mini"
									@click="update(item.row)"
									>修改</el-button
								>
								<el-button
									type="danger"
									size="mini"
									@click="delect(item.row)"
									>删除</el-button
								>
							</template>
						</el-table-column>
					</el-table>
					<el-pagination
						@current-change="executeSearch"
						:current-page.sync="page"
						:page-size="10"
						layout="total, prev, pager, next"
						:total="3"
						style="margin-top: 20px;"
					>
					</el-pagination>
				</div>
			</el-card>
		</div>
		<el-dialog :visible.sync="addShow" width="70%" top="0vh" :modal-append-to-body='false' fullscreen v-if="addShow">
			<add :id="id"></add>
		</el-dialog>
	</div>
</template>

<script>
import ajax from '@/api/article'
import add from './add'

export default {
	components:{
		add
	},
	data() {
		return {
			search: {
				id: "",
				title: "",
				sort: "0",
			},
			page: 1,
			total:3,
			articles: [
			],
			id: '',
			addShow: false
		};
	},
	created(){
		this.executeSearch();
	},
	methods: {
		// 更新文章
		update(article) {
			this.id = article.id;
			this.addShow = true;
		},
		// 更新文章删除文章
		delect(article) {
			this.$confirm("此操作将删除文章, 是否继续?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			}).then(() => {
				this.layer.alert("删除" + JSON.stringify(article), (index) => {
					this.$message({
						type: "success",
						message: "删除成功!",
					});
					this.layer.close(index);
				});
			});
		},
		/**
		 * 进行搜索
		 *
		 */
		executeSearch() {
			
			ajax.list({
				id: this.search.id,
				title: this.search.title,
				sort: this.search.sort,
				page: this.page,
				limit: 10

			}, res=>{
			if(res.success){
				this.articles = res.data.items;
			}
		})
		},
		clearSearch() {
			this.search = {
				id: "",
				title: "",
				sort: "0",
			};
		},
	},
};
</script>

<style>
</style>