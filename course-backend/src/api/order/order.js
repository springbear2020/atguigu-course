import request from '@/utils/request'

const BASE = '/admin/order'

export default {
  // 查询订单分页数据
  getPageList(cur, size, conditions) {
    return request({
      url: `${BASE}/page/${cur}/${size}`,
      method: 'get',
      params: conditions
    })
  }
}
