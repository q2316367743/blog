import axios from 'axios'
import { Message } from 'element-ui'
import Vue from 'vue';

const instance = axios.create({
    baseURL: 'http://localhost/api/manage',
    timeout: 3000
})


// http request 拦截器
instance.interceptors.request.use(
    config => {
        if (window.sessionStorage.getItem('satoken')) {
            config.headers['satoken'] = window.sessionStorage.getItem('satoken');
        }
        return config
    },
    err => {
        return Promise.reject(err);
    })

// http response 拦截器
instance.interceptors.response.use(
    response => {
        //debugger
        if (response.data.code == 401) {
            Message({
                message: '账户未登录，请重新登录',
                type: 'error',
                duration: 5 * 1000
            })
            sessionStorage.removeItem('token');
            // 返回 错误代码-1 清除ticket信息并跳转到登录页面
            Vue.prototype.sa_admin.openLogin();
            return {
                success: false,
            };
        } else if (response.data.code == 402) {
            Message({
                message: '账户登录过期，请重新登录',
                type: 'error',
                duration: 5 * 1000
            })
            sessionStorage.removeItem('token');
            // 返回 错误代码-1 清除ticket信息并跳转到登录页面
            Vue.prototype.sa_admin.openLogin();
            return {
                success: false,
            };
        } else if (response.data.code == 500) {
            Message({
                message: '服务器错误，' + response.data.message,
                type: 'error',
                duration: 5 * 1000
            })
            return {
                success: false,
            };
        } else {
            return response;
        }
    },
    error => {
        return Promise.reject(error.response) // 返回接口返回的错误信息
    });

export default instance;