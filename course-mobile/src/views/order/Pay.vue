<template>
  <div>
    <van-image width="100%" height="200" :src="orderInfo.cover"/>
    <h1 class="van-ellipsis course_title">课程名称: {{ orderInfo.courseName }}</h1>
    <div class="course_teacher_price_box">
      <div class="course_price">订单号：{{ orderInfo.outTradeNo }}</div>
    </div>
    <div class="course_teacher_price_box">
      <div class="course_price">下单时间：{{ orderInfo.createTime }}</div>
    </div>
    <div class="course_teacher_price_box">
      <div class="course_price">支付状态：{{ orderInfo.orderStatus == 'PAID' ? '已支付' : '未支付' }}</div>
    </div>
    <div class="course_teacher_price_box" v-if="orderInfo.orderStatus === 'PAID'">
      <div class="course_price">支付时间：{{ orderInfo.payTime }}</div>
    </div>
    <van-divider/>
    <div class="course_teacher_price_box">
      <div class="course_price">订单金额：<span style="color: red">￥{{ orderInfo.originAmount }}</span></div>
    </div>
    <div class="course_teacher_price_box">
      <div class="course_price">优惠券金额：<span style="color: red">￥{{ orderInfo.couponReduce }}</span></div>
    </div>
    <div class="course_teacher_price_box">
      <div class="course_price">支付金额：<span style="color: red">￥{{ orderInfo.finalAmount }}</span></div>
    </div>

    <van-goods-action>
      <van-goods-action-button type="danger" text="支付" @click="pay" v-if="orderInfo.orderStatus !== 'PAID'"/>
      <van-goods-action-button type="warning" text="去观看" @click="see" v-else/>
    </van-goods-action>

    <van-loading vertical="true" v-show="loading">加载中...</van-loading>
  </div>
</template>

<script>
import orderApi from '../../api/order'
import wechatApi from '../../api/wechat'


export default {
  data() {
    return {
      loading: false,
      orderId: null,
      orderInfo: {},
      finalAmount: 0
    };
  },

  created() {
    this.orderId = this.$route.params.orderId;
    this.fetchData();
  },

  methods: {
    // 查询订单信息
    fetchData() {
      this.loading = true;
      orderApi.getOrderInfo(this.orderId).then(response => {
        this.orderInfo = response.data;
        this.finalAmount = parseFloat(this.orderInfo.finalAmount) * 100;
        this.loading = false;
      });
    },

    pay() {
      this.loading = true;
      orderApi.payOrder(this.orderInfo.outTradeNo).then(response => {
        this.loading = false;
        this.fetchData()
        alert(response.message)
        this.pushPayMsg(this.orderInfo.outTradeNo)
      })
    },

    // 推送支付消息
    pushPayMsg(tradeNumber) {
      wechatApi.pushPayMsg(tradeNumber).then(res => {})
    },

    see() {
      this.$router.push({path: '/course/detail/' + this.orderInfo.courseId})
    }
  }
};
</script>

<style lang="scss" scoped>
.gap {
  height: 10px;
}

::v-deep.van-image {
  display: block;
}

.course_count {
  background-color: #82848a;
  color: white;
  padding: 5px;
  text-align: center;
  border-right: 1px solid #939393;

  h1 {
    font-size: 14px;
    margin: 0;
  }

  p {
    margin: 0;
    font-size: 16px;
  }
}

.course_title {
  font-size: 20px;
  margin: 10px;
}

.course_teacher_price_box {
  margin: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .course_teacher_price {
    display: flex;
    font-size: 14px;
    align-items: center;

    .course_price_number {
      color: red;
      font-size: 18px;
      font-weight: bold;
    }
  }

  .course_teacher_box {
    display: flex;
    justify-content: center;
    align-items: center;

    .course_teacher {
      margin-right: 20px;
    }
  }
}

.course_contents {
  margin: 10px;

  .course_title_font {
    color: #68cb9b;
    font-weight: bold;
  }

  .course_content {
    margin-bottom: 20px;
  }
}

.course_chapter_list {
  display: flex;
  justify-content: space-between;
  align-items: center;

  h2 {
    font-size: 14px;
  }

  p {
    margin: 0;
  }
}
</style>
