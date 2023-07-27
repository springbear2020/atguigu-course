import request from '@/utils/request'

const BASE = '/admin/vod/video'

export default {
  // 新增小节
  save(video) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: video
    })
  },
  // 查询小节
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },
  // 更新小节
  updateById(video) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: video
    })
  },
  // 删除小节
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  // 统计课程观看人数（统计每个课程的小节观看人数）
  findCount(courseId, startDate, endDate) {
    return request({
      url: `${BASE}/count/${courseId}/${startDate}/${endDate}`,
      method: 'get'
    })
  }
}
