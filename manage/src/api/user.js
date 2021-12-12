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

export function update(admin, success, error) {
    request({
        url: 'admin/update',
        method: 'PUT',
        data: admin
    }).then((res) => {
        success(res);
    }).catch((e) => {
        if (error) {
            error(e);
        } else {
            console.error(e);
        }
    })
}