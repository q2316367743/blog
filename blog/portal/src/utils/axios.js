import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router/index'


const instance = axios.create({
    baseURL: 'http://localhost/api/blog/portal',
    timeout: 3000
})



// http response 拦截器
instance.interceptors.response.use(
    response => {
        if (response.data.code == 500) {
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