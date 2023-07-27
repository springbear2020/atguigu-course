import request from '@/utils/request'

const BASE = '/admin/wechat/menu'

export default {
  save(menu) {
    return request({
      url: `${BASE}/save`,
      method: `post`,
      data: menu
    })
  },
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  updateById(menu) {
    return request({
      url: `${BASE}/update`,
      method: `put`,
      data: menu
    })
  },
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: `get`
    })
  },
  // 查询菜单详情，一个父菜单下包含多个子菜单
  listMenuDetails() {
    return request({
      url: `${BASE}/list`,
      method: `get`
    })
  },
  // 同步最新菜单到微信后台
  syncMenu() {
    return request({
      url: `${BASE}/sync`,
      method: `get`
    })
  }
}

