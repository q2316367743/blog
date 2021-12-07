import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/admin/login',
        method: 'GET',
        params: data
    })
}

export function info(success) {
    return request({
        url: '/admin/info',
        method: 'GET',
    }).then(res => {
        success(res);
    })
}

export function logout() {
    return request({
        url: '/admin/logout',
        method: 'GET'
    })
}