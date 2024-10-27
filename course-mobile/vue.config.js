module.exports = {
    publicPath: './',
    lintOnSave: false,
    devServer: {
        disableHostCheck: true,
        proxy: {
            '/atguigu-course-gateway-api': {
                target: 'http://localhost:8810',
                pathRewrite: {'^/atguigu-course-gateway-api': ''}
            },
        },
    }
}
