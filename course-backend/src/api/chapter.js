import request from '@/utils/request'

const BASE = '/admin/vod/chapter'

export default {
  getNestedTreeList(courseId) {
    return request({
      url: `${BASE}/list/${courseId}`,
      method: 'get'
    })
  },

  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },

  save(chapter) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: chapter
    })
  },

  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },

  updateById(chapter) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: chapter
    })
  }
}
