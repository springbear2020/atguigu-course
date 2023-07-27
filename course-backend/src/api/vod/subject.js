import request from '@/utils/request'

const BASE_URL = '/admin/vod/subject'

export default {
  // 查询课程分类信息：一级分类下有多个二级分类
  getSubjects(id) {
    return request({
      url: `${BASE_URL}/list/${id}`,
      method: 'get'
    })
  },
}
