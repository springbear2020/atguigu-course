import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
    baseURL: 'http://ggkt.vipgz1.91tunnel.com',
    // baseURL: 'http://glkt-api.atguigu.cn',
    timeout: 30000
})

// http request 拦截器
service.interceptors.request.use(config => {
        //获取 localStorage 里的 token 值
        let token = window.localStorage.getItem('token') || '';
        if (token !== '') {
            // 将 token 值条件到请求头中
            // 'eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY3MTQxMTbWUSotTi3yTAGKQZh-ibmpQB1KtQARkjypPAAAAA.B6dziXWxcc2mIYYaDQnXB1t0IHwQK-GwWNFsAQ0Z7CbCBVb11uoNjojWYotC8YEdlVW9Ahtq99LWtz1_Wbhhlw';
            // cookie.get('guli_token');
            config.headers['token'] = token;
        }
        return config
    },
    err => {
        return Promise.reject(err);
    })
// http response 拦截器
service.interceptors.response.use(response => {
        if (response.data.code === 208) {
            // 请求路由路径中的 #
            let url = window.location.href.replace('#', 'guiguketan')
            window.location = 'http://ggkt.vipgz1.91tunnel.com/api/user/wechat/authorize?returnUrl=' + url
        } else {
            if (response.data.code === 20000) {
                return response.data
            } else {
                console.log("response.data:" + JSON.stringify(response.data))
                alert(response.data.message || 'error')
                return Promise.reject(response)
            }
        }
    },
    error => {
        // 返回接口返回的错误信息
        return Promise.reject(error.response)
    });

export default service
