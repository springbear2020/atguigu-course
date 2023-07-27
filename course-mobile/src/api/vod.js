import request from '../utils/request'

const BASE = '/api/vod'

export default {
    // 获取视频播放验证信息
    getVideo(videoId) {
        return request({
            url: `${BASE}/get/${videoId}`,
            method: 'get',
        })
    }
}
