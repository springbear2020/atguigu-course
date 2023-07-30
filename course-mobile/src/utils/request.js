import axios from 'axios'

// TODO replace the base request url when environment changed
export const BASE_REQUEST_URL = 'http://course.5gzvip.91tunnel.com'

// 创建 axios 实例
const service = axios.create({
    baseURL: BASE_REQUEST_URL,
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
