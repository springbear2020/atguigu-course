import request from '@/utils/request'

const BASE_URL = '/admin/vod/teacher'

export default {
  getTeachers(pageNum, pageSize, conditions) {
    return request({
      url: `${BASE_URL}/page/${pageNum}/${pageSize}`,
      method: 'get',
      params: conditions
    })
  },
  deleteTeacher(tid) {
    return request({
      url: `${BASE_URL}/remove/${tid}`,
      method: 'delete'
    })
  },
  saveTeacher(teacher) {
    return request({
      url: `${BASE_URL}/save`,
      method: 'post',
      data: teacher
    })
  },
  getTeacher(tid) {
    return request({
      url: `${BASE_URL}/get/${tid}`,
      method: 'get'
    })
  },
  updateTeacher(teacher) {
    return request({
      url: `${BASE_URL}/update`,
      method: 'put',
      data: teacher
    })
  },
  deleteTeachers(ids) {
    return request({
      url: `${BASE_URL}/remove`,
      method: 'delete',
      data: ids
    })
  },
  getAllTeachers() {
    return request({
      url: `${BASE_URL}/list`,
      method: 'get'
    })
  }
}
