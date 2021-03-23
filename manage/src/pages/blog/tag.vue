<template>
	<div class="box">
		<!-- 欢迎 -->
		<div class="content">
            <el-card shadow="nover" style="margin-top: 1em;">
                标签管理
                <el-button @click="open">打开404</el-button>
            </el-card>
        </div>
	</div>
</template>

<script>
export default {
    methods: {
        open(){
            this.sa_admin.open404();
        }
    }
};
</script>

<style>
</style>