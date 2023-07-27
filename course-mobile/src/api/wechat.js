import request from '../utils/request'

const BASE = '/api/wechat/message'

export default {
    // 推送用户订单支付消息
    pushPayMsg(tradeNumber) {
        return request({
            url: `${BASE}/push/${tradeNumber}`,
            method: 'get',
        })
    }
}
