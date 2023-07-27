import request from '@/utils/request'

const BASE_URL = '/admin/vod/course'

export default {
  getCoursePageData(curPage, pageSize, conditions) {
    return request({
      url: `${BASE_URL}/page/${curPage}/${pageSize}`,
      method: 'get',
      params: conditions
    })
  },

  saveCourseInfo(courseInfo) {
    return request({
      url: `${BASE_URL}/save`,
      method: 'post',
      data: courseInfo
    })
  },
  getCourseInfoById(id) {
    return request({
      url: `${BASE_URL}/get/${id}`,
      method: 'get'
    })
  },
  updateCourseInfoById(courseInfo) {
    return request({
      url: `${BASE_URL}/update`,
      method: 'put',
      data: courseInfo
    })
  },
  // 发布课程
  publishCourseById(id) {
    return request({
      url: `${BASE_URL}/update/${id}`,
      method: 'put'
    })
  },
// 删除查看
  removeById(id) {
    return request({
      url: `${BASE_URL}/remove/${id}`,
      method: 'delete'
    })
  },
}
