<template>
    <div class="app-container">
        <div class="el-card" style="display: flex;justify-content: space-between;padding: 20px;">
            <div>
                <el-input v-model="condition.name" style="width: 240px" placeholder="昵称"></el-input>
                <el-select v-model="condition.status" clearable @change="search" style="margin-left: 20px;"
                           placeholder="类型">
                    <el-option :value="1" label="审核中"></el-option>
                    <el-option :value="2" label="审批通过"></el-option>
                    <el-option :value="3" label="审批拒绝"></el-option>
                </el-select>
            </div>
            <div>
                <el-button type="primary" @click="search">搜索</el-button>
            </div>
        </div>
        <div class="el-card" style="margin-top: 20px;padding: 20px;">
                <el-button type="primary" @click="open_link()">新增</el-button>
            <el-table :data="links" style="margin-top: 10px">
                <el-table-column label="ID" prop="id" width="60px"></el-table-column>
                <el-table-column label="昵称" prop="name" width="150px"></el-table-column>
                <el-table-column label="邮箱" prop="email" width="220px"></el-table-column>
                <el-table-column label="网站" prop="url" width="220px"></el-table-column>
                <el-table-column label="创建时间" prop="create_time"></el-table-column>
                <el-table-column label="状态" width="150px" v-if="condition.status === ''" align="center">
                    <template slot-scope="scope">
                        <el-tag type="info" v-if="scope.row.status === 1">审核中</el-tag>
                        <el-tag type="success" v-else-if="scope.row.status === 2">审核通过</el-tag>
                        <el-tag type="danger" v-else-if="scope.row.status === 3">审核拒绝</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="200px">
                    <template slot-scope="scope">
                        <el-button type="text" @click="open_info(scope.row)">详情</el-button>
                        <el-button type="text" @click="open_link(scope.row)">修改</el-button>
                        <el-button type="text" style="color: #67C23A" @click="pass(scope.row.id)"
                                   v-if="scope.row.status === 1 || scope.row.status === 3">通过
                        </el-button>
                        <el-button type="text" style="color: #F56C6C" @click="refuse(scope.row.id)"
                                   v-else-if="scope.row.status === 1 || scope.row.status === 2">拒绝
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog :visible.sync="info_dialog">
            <div slot="title">友情链接 · 详情</div>
            <div class="link-info-info">
                <div class="link-info-label">创建时间</div>
                <div class="link-info-content">{{ info_data.create_time }}</div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">昵称</div>
                <div class="link-info-content">{{ info_data.name }}</div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">图标</div>
                <div class="link-info-content">
                    <el-image :src="info_data.icon" style="width: 32px;height: 32px;border-radius: 50%;"></el-image>
                </div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">邮箱</div>
                <div class="link-info-content">{{ info_data.email }}</div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">个人网址</div>
                <div class="link-info-content">
                    <el-link href="info_data.url" target="_blank">{{ info_data.url }}</el-link>
                </div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">描述</div>
                <div class="link-info-content">{{ info_data.description }}</div>
            </div>
            <div class="link-info-info">
                <div class="link-info-label">状态</div>
                <div class="link-info-content">
                    <el-tag type="info" v-if="info_data.status === 1">审核中</el-tag>
                    <el-tag type="success" v-else-if="info_data.status === 2">审核通过</el-tag>
                    <el-tag type="danger" v-else-if="info_data.status === 3">审核拒绝</el-tag>
                </div>
            </div>
            <div class="link-info-info" v-if="info_data.status === 3">
                <div class="link-info-label">内容</div>
                <div class="link-info-content">{{ info_data.reason }}</div>
            </div>
        </el-dialog>
        <el-dialog :visible.sync="link_dialog" width="500px">
            <div slot="title">友情链接 · {{link_data.id ? '修改' : '新增'}}</div>
            <el-form label-width="70px">
                <el-form-item label="昵称">
                    <el-input v-model="link_data.name" style="width: 180px"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱">
                    <el-input v-model="link_data.email"></el-input>
                </el-form-item>
                <el-form-item label="网站地址">
                    <el-input v-model="link_data.url"></el-input>
                </el-form-item>
                <el-form-item label="描述">
                    <el-input v-model="link_data.description" type="textarea" :rows="5"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button @click="clear_link" v-if="!link_data.id">清空</el-button>
                <el-button type="primary" @click="add_link">{{link_data.id ? '修改' : '新增'}}</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import link_api from '@/api/link';

export default {
    name: "index",
    data: () => {
        return {
            condition: {
                name: '',
                status: 1,
                page_num: 1,
                page_size: 10,
            },
            total: 1,
            links: [],
            info_dialog: false,
            info_data: {
                id: 1,
                create_time: "",
                name: '',
                email: '',
                url: '',
                description: '',
                status: 2,
                reason: ''
            },
            link_dialog: false,
            link_data: {
                name: '',
                email: '',
                url: '',
                description: '',
                status: 2
            }
        }
    },
    created() {
        this.search();
    },
    methods: {
        search() {
            link_api.list(this.condition, res => {
                this.links = res.data.records;
                this.total = res.data.total;
            })
        },
        open_info(info) {
            this.info_data = info;
            this.info_dialog = true;
        },
        open_link(link) {
            if (link) {
                this.link_data = link;
            }else {
                this.link_data = {
                    name: '',
                    email: '',
                    url: '',
                    description: '',
                    status: 2
                };
            }
            this.link_dialog = true;
        },
        add_link() {
            if (this.link_data.id) {
                link_api.update(this.link_data.id, this.link_data, () => {
                    this.search();
                    this.$message.success('修改友情链接成功');
                })
            }else {
                link_api.save(this.link_data, () => {
                    this.search();
                    this.$message.success('新增友情链接成功');
                })
            }
            this.link_dialog = false;
        },
        clear_link() {
            this.link_data = {
                name: '',
                email: '',
                url: '',
                description: '',
                status: 2
            };
        },
        pass(id) {
            link_api.update(id, {
                status: 2
            }, () => {
                this.search();
                this.$message.success('友情链接审批通过');
            })
        },
        refuse(id) {
            this.$prompt('请输入拒绝原因', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
            }).then(({value}) => {
                link_api.update(id, {
                    status: 3,
                    reason: value
                }, () => {
                    this.search();
                    this.$message.success('友情链接审批拒绝');
                })
            })
        }
    }
}
</script>

<style scoped>
.link-info-info {
    padding-left: 20px;
    padding-top: 10px;
    display: flex;
}

.link-info-label {
    line-height: 32px;
    height: 32px;
    width: 70px;
    text-align: right;
}

.link-info-content {
    line-height: 32px;
    height: 32px;
    padding-left: 20px;
}
</style>
