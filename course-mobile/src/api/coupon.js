import request from '../utils/request'

const BASE = '/api/activity/coupon'

export default {
    // 查询优惠券信息
    getCouponInfo() {
        return request({
            url: `${BASE}/list`,
            method: 'get',
        })
    },
    // 兑换优惠券
    exchangeCoupon(code) {
        return request({
            url: `${BASE}/exchange`,
            method: 'get',
            params: {
                'code': code
            }
        })
    }
}
