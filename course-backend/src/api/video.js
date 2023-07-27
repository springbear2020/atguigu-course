import request from '@/utils/request'

const BASE = '/admin/vod/video'

export default {

  save(video) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: video
    })
  },

  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },

  updateById(video) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: video
    })
  },

  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  findCount(courseId, startDate, endDate) {
    return request({
      url: `${BASE}/visitor/count/${courseId}/${startDate}/${endDate}`,
      method: 'get'
    })
  }
}
