<template>
    <div class="app-container">
        <div class="el-card" style="display: flex;justify-content: space-between;padding: 20px;">
            <div>
                <el-input v-model="condition.name" style="width: 240px" placeholder="昵称"></el-input>
                <el-select v-model="condition.type" clearable @change="search" style="margin-left: 20px;" placeholder="类型">
                    <el-option :value="1" label="博客"></el-option>
                    <el-option :value="2" label="es-client"></el-option>
                </el-select>
            </div>
            <div>
                <el-button type="primary" @click="search">搜索</el-button>
            </div>
        </div>
        <div class="el-card" style="margin-top: 20px;padding: 20px;">
            <el-table :data="messages">
                <el-table-column label="ID" prop="id" width="60px"></el-table-column>
                <el-table-column label="类型" width="100px">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.type === 1">博客</el-tag>
                        <el-tag type="primary" v-else-if="scope.row.type === 2">es-client</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="昵称" prop="name" width="150px"></el-table-column>
                <el-table-column label="邮箱" prop="email"></el-table-column>
                <el-table-column label="创建时间" prop="create_time"></el-table-column>
                <el-table-column label="内容">
                    <template slot-scope="scope">
                        <span>{{ scope.row.content.length > 14 ? scope.row.content.substr(0, 14) + "..." : scope.row.content }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="text" @click="open_info(scope.row)">详情</el-button>
                        <el-button type="text">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog :visible.sync="info_dialog">
            <div slot="title">意见反馈</div>
            <div class="message-info-info">
                <div class="message-info-label">类型</div>
                <div class="message-info-content">
                    <el-tag type="success" v-if="info_data.type === 1">博客</el-tag>
                    <el-tag type="primary" v-else-if="info_data.type === 2">es-client</el-tag>
                </div>
            </div>
            <div class="message-info-info">
                <div class="message-info-label">用户名</div>
                <div class="message-info-content">{{ info_data.name }}</div>
            </div>
            <div class="message-info-info">
                <div class="message-info-label">邮箱</div>
                <div class="message-info-content">{{ info_data.email }}</div>
            </div>
            <div class="message-info-info">
                <div class="message-info-label">个人网址</div>
                <div class="message-info-content">{{ info_data.url }}</div>
            </div>
            <div class="message-info-info">
                <div class="message-info-label">创建时间</div>
                <div class="message-info-content">{{ info_data.create_time }}</div>
            </div>
            <div class="message-info-info">
                <div class="message-info-label">内容</div>
                <div class="message-info-content">{{ info_data.content }}</div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import message_api from '@/api/message';

export default {
    name: "index",
    data: () => {
        return {
            condition: {
                name: '',
                type: 1,
                page_num: 1,
                page_size: 10,
            },
            total: 1,
            messages: [],
            info_dialog: false,
            info_data: {
                id: 1,
                create_time: "",
                type: 1,
                name: "",
                email: "",
                url: "",
                content: ""
            }
        }
    },
    created() {
        this.search();
    },
    methods: {
        search() {
            message_api.list(this.condition, res => {
                this.messages = res.data.records;
                this.total = res.data.total;
            })
        },
        open_info(info) {
            this.info_data = info;
            this.info_dialog = true
        }
    }
}
</script>

<style scoped>
.message-info-info {
    padding-left: 20px;
    padding-top: 10px;
    display: flex;
}

.message-info-label {
    line-height: 32px;
    height: 32px;
    width: 70px;
    text-align: right;
}

.message-info-content {
    line-height: 32px;
    height: 32px;
    padding-left: 20px;
}
</style>
