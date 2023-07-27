import request from '@/utils/request'

const BASE_URL = '/admin/vod/teacher'

export default {
  // 查询讲师分页数据
  getTeachers(pageNum, pageSize, conditions) {
    return request({
      url: `${BASE_URL}/page/${pageNum}/${pageSize}`,
      method: 'get',
      params: conditions
    })
  },
  // 删除讲师
  deleteTeacher(tid) {
    return request({
      url: `${BASE_URL}/remove/${tid}`,
      method: 'delete'
    })
  },
  // 新增讲师
  saveTeacher(teacher) {
    return request({
      url: `${BASE_URL}/save`,
      method: 'post',
      data: teacher
    })
  },
  // 查询讲师
  getTeacher(tid) {
    return request({
      url: `${BASE_URL}/get/${tid}`,
      method: 'get'
    })
  },
  // 更新讲师
  updateTeacher(teacher) {
    return request({
      url: `${BASE_URL}/update`,
      method: 'put',
      data: teacher
    })
  },
  // 删除讲师
  deleteTeachers(ids) {
    return request({
      url: `${BASE_URL}/remove`,
      method: 'delete',
      data: ids
    })
  },
  // 查询所有讲师
  getAllTeachers() {
    return request({
      url: `${BASE_URL}/list`,
      method: 'get'
    })
  }
}
