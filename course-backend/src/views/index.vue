<template>
  <div class="dashboard-container">
    <div class="dashboard-text" style="text-align: center">欢迎【{{ name }}】管理员</div>
    <ol>
      <li><router-link to="/teacher">讲师管理</router-link></li>
      <li><router-link to="/course">课程管理</router-link></li>
      <li><router-link to="/order">订单管理</router-link></li>
      <li><router-link to="/activity">营销管理</router-link></li>
      <li><router-link to="/wechat">公众号管理</router-link></li>
    </ol>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters(['name'])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

li {
  margin: 10px;
}
</style>
