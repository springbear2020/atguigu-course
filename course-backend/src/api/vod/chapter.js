import request from '@/utils/request'

const BASE = '/admin/vod/chapter'

export default {
  // 查询课程详情：一个课程有多个章节、一个章节有多个小节
  getNestedTreeList(courseId) {
    return request({
      url: `${BASE}/list/${courseId}`,
      method: 'get'
    })
  },
  // 删除章节
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },
  // 保存章节
  save(chapter) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: chapter
    })
  },
  // 查询章节
  getById(id) {
    return request({
      url: `${BASE}/get/${id}`,
      method: 'get'
    })
  },
  // 更新章节
  updateById(chapter) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: chapter
    })
  }
}
