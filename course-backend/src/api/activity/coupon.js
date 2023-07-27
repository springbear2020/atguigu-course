import request from '@/utils/request'

const BASE = '/admin/activity/coupon'

export default {
  save(role) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: role
    })
  },
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  removeRows(idList) {
    return request({
      url: `${BASE}/remove`,
      method: 'delete',
      data: idList
    })
  },
  updateById(role) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: role
    })
  },
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },
  getPageList(page, limit) {
    return request({
      url: `${BASE}/page/${page}/${limit}`,
      method: 'get'
    })
  },
  getPageCouponUseList(page, limit, searchObj) {
    return request({
      url: `${BASE}/page/used/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  }
}
