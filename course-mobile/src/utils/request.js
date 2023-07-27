import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
    // TODO base url
    baseURL: 'http://course.5gzvip.91tunnel.com',
    timeout: 30000
})

// http request 拦截器
service.interceptors.request.use(config => {
        // 获取 localStorage 里的 token 值
        let token = window.localStorage.getItem('token') || '';
        if (token !== '') {
            // 将 token 值条件到请求头中
            config.headers['token'] = token;
        }
        return config
    },
    err => {
        return Promise.reject(err);
    })

// http response 拦截器
service.interceptors.response.use(response => {
        if (response.data.code === 20000) {
            return response.data
        } else {
            alert(response.data.message || 'error')
            return Promise.reject(response)
        }
    },
    error => {
        // 返回接口返回的错误信息
        return Promise.reject(error.response)
    });

export default service
