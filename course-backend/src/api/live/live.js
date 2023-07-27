import request from '@/utils/request'

const BASE = '/admin/live/course'

export default {
  // 新增直播课程
  save(liveCourse) {
    return request({
      url: `${BASE}/save`,
      method: 'post',
      data: liveCourse
    })
  },
  // 删除直播课程
  removeById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  },

  updateById(liveCourse) {
    return request({
      url: `${BASE}/update`,
      method: 'put',
      data: liveCourse
    })
  },

  // 查询直播课程分页数据
  getPageList(page, limit) {
    return request({
      url: `${BASE}/page/${page}/${limit}`,
      method: 'get'
    })
  },

  // 通过 ID 查询课程信息
  getById(id) {
    return request({
      url: `${BASE}/get`,
      method: 'get',
      params: {
        id: id
      }
    })
  },

  // 查询直播课程账号信息
  getLiveCourseAccount(id) {
    return request({
      url: `${BASE}/account/get/${id}`,
      method: 'get'
    })
  },

  // 查询课程配置信息
  getCourseConfig(id) {
    return request({
      url: `${BASE}/config/get/${id}`,
      method: 'get'
    })
  },

  // 更新课程配置信息
  updateConfig(liveCourseConfigVo) {
    return request({
      url: `${BASE}/config/update`,
      method: 'put',
      data: liveCourseConfigVo
    })
  }
}
