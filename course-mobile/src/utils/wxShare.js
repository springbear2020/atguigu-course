/**
 * 微信 js-sdk
 * 参考文档：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115
 */
const wxShare = {
    /**
     * 微信 Api 初始化
     * @param data 微信配置信息
     * @param option 分享的配置内容
     */
    wxRegister(data, option) {
        wx.config({
            debug: false,
            appId: data.appId,
            timestamp: data.timestamp,
            nonceStr: data.nonceStr,
            signature: data.signature,
            jsApiList: ['onMenuShareAppMessage']
        });

        wx.ready(function () {
            wx.onMenuShareAppMessage({
                title: option.title,
                desc: option.desc,
                link: option.link,
                imgUrl: option.imgUrl,
                success() {
                    console.log('ok');
                },
                cancel() {
                    console.log('cancel');
                }
            });
        });

        wx.error(function (res) {
            /*
             * config 信息验证失败会执行 error 函数，如签名过期导致验证失败
             * 具体错误信息可以打开 config 的 debug 模式查看，也可以在返回的 res 参数中查看
             * 对于 SPA（单页应用）可以在这里更新签名
             */
            alert('error:' + JSON.stringify(res));
        });
    }
}
export default wxShare
