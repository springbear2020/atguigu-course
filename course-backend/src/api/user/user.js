import request from '@/utils/request'

const BASE = 'admin/user'

export function login(data) {
  return request({
    url: `${BASE}/login`,
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: `${BASE}/info`,
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: `${BASE}/logout`,
    method: 'post'
  })
}
