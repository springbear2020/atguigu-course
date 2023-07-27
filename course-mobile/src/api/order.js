import request from '../utils/request'

const BASE = '/api/order'

export default {
    // 下单
    submitOrder(orderFormVo) {
        return request({
            url: `${BASE}/save`,
            method: 'post',
            data: {
                'courseId': orderFormVo.courseId,
                'couponId': orderFormVo.couponId,
                'couponUseId': orderFormVo.couponUseId
            }
        })
    }
}
