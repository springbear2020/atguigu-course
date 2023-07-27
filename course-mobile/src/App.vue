<template>
  <div id="app">
    <div id="nav">
      <van-button round block type="info" @click="clearToken">clear token</van-button>
    </div>
    <router-view/>
  </div>
</template>


<script>
export default {
  created() {
    // 进入任何页面前都需要进行微信授权
    this.wechatAuthorization();
  },

  methods: {
    // 处理微信授权登录逻辑
    wechatAuthorization() {
      let token = this.getQueryString('token') || '';

      // 先判断本次请求的路径中是否含有 token 信息，若存在则存入本地 localStorage
      if (token !== '') {
        window.localStorage.setItem('token', token);
      }

      // 访问所有页面都必须登录，两次调整登录，这里与接口返回 208 状态码
      token = window.localStorage.getItem('token') || '';

      // 请求服务器获取 token 信息，token 获取成功重定向到到请求的页面 from
      if (token === '') {
        let from = window.location.href.replace('#', 'placeholder')
        window.location = 'http://course.5gzvip.91tunnel.com/api/user/wechat/auth?from=' + from
      }
    },

    // 获取地址栏请求路径中的参数值
    getQueryString(paramName) {
      if (window.location.href.indexOf('?') === -1) return '';

      let searchString = window.location.href.split('?')[1];
      let i, val, params = searchString.split("&");

      for (i = 0; i < params.length; i++) {
        val = params[i].split("=");
        if (val[0] === paramName) {
          return val[1];
        }
      }
      return '';
    },

    // 清除本地存储的用户信息
    clearToken() {
      window.localStorage.setItem('token', '');
    }
  }
};
</script>


<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
