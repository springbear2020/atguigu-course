<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-date-picker v-model="startDate" type="date" placeholder="选择开始日期" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="endDate" type="date" placeholder="选择截止日期" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-button :disabled="btnDisabled" type="primary" icon="el-icon-search" @click="countVisitors()">查询
      </el-button>
    </el-form>
    <div id="chart" class="chart" style="height:500px;">
      <!-- 图表 -->
    </div>
  </div>
</template>


<script>
import echarts from 'echarts'
import api from '@/api/vod/video'

export default {
  data() {
    return {
      courseId: '',
      startDate: '',
      endDate: '',
      btnDisabled: false,
      xData: [],
      yData: []
    }
  },
  created() {
    this.courseId = this.$route.params.id
    // 默认初始化最近十天数据
    let currentDate = new Date()
    this.startDate = this.dateFormat(new Date(currentDate.getTime() - 7 * 24 * 3600 * 1000))
    this.endDate = this.dateFormat(currentDate)
    this.countVisitors()
  },
  methods: {
    countVisitors() {
      api.findCount(this.courseId, this.startDate, this.endDate).then(response => {
        let visitors = response.data;
        if (visitors == null || visitors.length <= 0) {
          this.$message.info(response.message)
          return
        }
        this.xData = []
        this.yData = []
        visitors.forEach((item) => {
          this.xData.push(item.joinDate)
          this.yData.push(item.userCount)
        })
        this.setChartData()
      })
    },

    setChartData() {
      // 基于准备好的 dom，初始化 echarts 实例
      let myChart = echarts.init(document.getElementById('chart'))
      // 指定图表的配置项和数据
      let option = {
        title: {
          text: '观看课程人数统计'
        },
        xAxis: {
          data: this.xData
        },
        yAxis: {
          minInterval: 1
        },
        series: [{
          type: 'line',
          data: this.yData
        }]
      }
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option)
    },
    // 日期格式化
    dateFormat(date) {
      let fmt = 'YYYY-mm-dd'
      let ret
      const opt = {
        'Y+': date.getFullYear().toString(),        // 年
        'm+': (date.getMonth() + 1).toString(),     // 月
        'd+': date.getDate().toString(),            // 日
        'H+': date.getHours().toString(),           // 时
        'M+': date.getMinutes().toString(),         // 分
        'S+': date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
      }
      for (let k in opt) {
        ret = new RegExp('(' + k + ')').exec(fmt)
        if (ret) {
          fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, '0')))
        }
      }
      return fmt
    }
  }
}
</script>
