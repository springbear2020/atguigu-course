import request from '@/utils/request'

const BASE = 'admin/user'

// 登录
export function login(data) {
  return request({
    url: `${BASE}/login`,
    method: 'post',
    data
  })
}

// 查询用户信息
export function getInfo(token) {
  return request({
    url: `${BASE}/info`,
    method: 'get',
    params: { token }
  })
}

// 注销登录
export function logout() {
  return request({
    url: `${BASE}/logout`,
    method: 'post'
  })
}
