import axios from '@/utils/axios'

export function login(data, success) {
    axios({
        url: '/user/login',
        method: 'GET',
        params: data
    }).then(res => {
        success(res.data)
    })
}

export function info(success) {
    axios({
        url: '/user/info',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

export function logout(success) {
    axios({
        url: '/user/logout',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}