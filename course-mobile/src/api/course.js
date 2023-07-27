import request from '../utils/request'

const BASE = '/api/vod/course'

export default {
    // 课程分页列表
    coursePageData(subjectParentId, pageNo, pageSize) {
        return request({
            url: `${BASE}/page/${pageNo}/${pageSize}/${subjectParentId}`,
            method: 'get',
        })
    },
    // 课程详情
    courseDetail(courseId) {
        return request({
            url: `${BASE}/get`,
            method: 'get',
            params: {
                id: courseId
            }
        })
    }
}
