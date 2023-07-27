import request from '@/utils/request'

const BASE = '/admin/wechat/menu'

export default {
  // 新增菜单
  save(menu) {
    return request({
      url: `${BASE}/save`,
      method: `post`,
      data: menu
    })
  },
  // 删除菜单
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  // 更新菜单
  updateById(menu) {
    return request({
      url: `${BASE}/update`,
      method: `put`,
      data: menu
    })
  },
  // 查询菜单
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: `get`
    })
  },
  // 查询菜单详情：一个父菜单下包含多个子菜单
  listMenuDetails() {
    return request({
      url: `${BASE}/list`,
      method: `get`
    })
  },
  // 同步最新菜单到微信公众号后台
  syncMenu() {
    return request({
      url: `${BASE}/sync`,
      method: `get`
    })
  }
}

