import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/login',
    method: 'GET',
    params: data
  })
}

export function logout() {
  return request({
    url: '/admin/logout',
    method: 'GET'
  })
}
