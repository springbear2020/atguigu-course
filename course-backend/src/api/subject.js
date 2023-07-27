import request from '@/utils/request'

const BASE_URL = '/admin/vod/subject'

export default {
  getSubjects(id) {
    return request({
      url: `${BASE_URL}/list/${id}`,
      method: 'get'
    })
  },
}
