import request from '@/utils/request'

const BASE = '/admin/activity/coupon'

export default {
  // 新增优惠券
  save(role) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: role
    })
  },
  // 删除优惠券
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  // 更新优惠券
  updateById(role) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: role
    })
  },
  // 查询优惠券
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },
  // 查询优惠券分页数据
  getPageList(page, limit) {
    return request({
      url: `${BASE}/page/${page}/${limit}`,
      method: 'get'
    })
  },
  // 查询已使用的优惠券列表
  getPageCouponUseList(page, limit, searchObj) {
    return request({
      url: `${BASE}/page/used/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  }
}
