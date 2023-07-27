import request from '@/utils/request'

const BASE = '/admin/vod'
export default {
  // 删除视频
  removeVideoById(id) {
    return request({
      url: `${BASE}/remove/${id}`,
      method: 'delete'
    })
  }
}
