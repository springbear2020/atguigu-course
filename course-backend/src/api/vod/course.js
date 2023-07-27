import request from '@/utils/request'

const BASE_URL = '/admin/vod/course'

export default {
  // 新增课程
  saveCourseInfo(courseInfo) {
    return request({
      url: `${BASE_URL}/save`,
      method: 'post',
      data: courseInfo
    })
  },
  // 删除课程
  removeById(id) {
    return request({
      url: `${BASE_URL}/remove/${id}`,
      method: 'delete'
    })
  },
  // 更新课程
  updateCourseInfoById(courseInfo) {
    return request({
      url: `${BASE_URL}/update`,
      method: 'put',
      data: courseInfo
    })
  },
  // 发布课程（修改课程状态为已发布）
  publishCourseById(id) {
    return request({
      url: `${BASE_URL}/update/${id}`,
      method: 'put'
    })
  },
  // 查询课程
  getCourseInfoById(id) {
    return request({
      url: `${BASE_URL}/get/${id}`,
      method: 'get'
    })
  },
  // 查询课程分页数据
  getCoursePageData(curPage, pageSize, conditions) {
    return request({
      url: `${BASE_URL}/page/${curPage}/${pageSize}`,
      method: 'get',
      params: conditions
    })
  },
  getAllCourse() {
    return request({
      url: `${BASE_URL}/list`,
      method: 'get'
    })
  }
}
