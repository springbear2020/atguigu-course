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
    },
    // 查询订单信息
    getOrderInfo(orderId) {
        return request({
            url: `${BASE}/get/${orderId}`,
            method: 'get',
        })
    },
    // 查询订单信息
    payOrder(tradeNumber) {
        return request({
            url: `${BASE}/pay/${tradeNumber}`,
            method: 'post',
        })
    },
}
