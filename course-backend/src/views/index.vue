<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎 {{ name }} 使用在线课堂！</div>
    <ol>
      <li><router-link to="/teacher">讲师管理</router-link></li>
      <li><router-link to="/course">课程管理</router-link></li>
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
