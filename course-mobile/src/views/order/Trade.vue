<template>
  <div>
    <van-image width="100%" height="200" :src="courseVo.cover"/>
    <h1 class="van-ellipsis course_title">{{ courseVo.title }}</h1>
    <div class="course_teacher_price_box">
      <div class="course_teacher_price">
        <div class="course_price">价格：</div>
        <div class="course_price_number">￥{{ courseVo.price }}</div>
      </div>
    </div>
    <div class="course_teacher_price_box">
      <div class="course_teacher_box">
        <div class="course_teacher">主讲： {{ courseVo.teacherName }}</div>
        <van-image :src="courseVo.teacherAvatar" round width="50px" height="50px"/>
      </div>
    </div>

    <van-loading vertical="true" v-show="loading">加载中...</van-loading>

    <!-- 优惠券信息 -->
    <div style="position:fixed; left:0; bottom:50px; width:100%; height:50px; z-index:999;">
      <van-coupon-cell :coupons="coupons" :chosen-coupon="chosenCoupon" @click="showList = true"/>
      <van-popup v-model="showList" round position="bottom" style="height: 90%; padding-top: 4px;">
        <van-coupon-list
            :coupons="coupons"
            :chosen-coupon="chosenCoupon"
            :disabled-coupons="disabledCoupons"
            v-model="code"
            :show-close-button="false"
            @change="onChange"
            @exchange="exchange"/>
      </van-popup>
    </div>
    <van-goods-action>
      <van-submit-bar :price="finalAmount" button-text="确认下单" @submit="sureOrder"/>
    </van-goods-action>
  </div>
</template>

<script>
import courseApi from '../../api/course'
import orderApi from '../../api/order'
import couponApi from '../../api/coupon'

export default {
  data() {
    return {
      loading: false,
      courseId: null,
      courseVo: {},

      // 当前输入的兑换码
      code: '',
      // 是否展示优惠券列表
      showList: false,
      // 当前选中的优惠券索引
      chosenCoupon: -1,
      // 可用优惠券列表
      coupons: [],
      // 不可用优惠券列表
      disabledCoupons: [],

      orderId: null,
      couponId: null,
      couponUseId: null,
      couponReduce: 0,
      // 付款价格
      finalAmount: 0
    };
  },

  created() {
    this.courseId = this.$route.params.courseId;
    this.fetchData()
    this.getCouponInfo()
  },

  methods: {
    // 	优惠券切换回调
    onChange(index) {
      this.showList = false;
      this.chosenCoupon = index;

      this.couponId = this.coupons[index].id;
      this.couponUseId = this.coupons[index].couponUseId;
      this.couponReduce = this.coupons[index].value;
      this.finalAmount = parseFloat(this.courseVo.price) * 100 - parseFloat(this.couponReduce)
      if (this.finalAmount <= 0) {
        this.finalAmount = 0
      }
    },
    // 优惠券兑换事件回调
    exchange(code) {
      couponApi.exchangeCoupon(code).then(response => {
        let msg = response.message
        this.getCouponInfo()
        alert(msg)
      });
    },
    // 查询课程信息
    fetchData() {
      this.loading = true;
      courseApi.courseDetail(this.courseId).then(response => {
        this.courseVo = response.data;
        // 转换为分
        this.finalAmount = parseFloat(this.courseVo.price) * 100;
        this.loading = false;
      });
    },
    // 查询用户拥有的优惠券信息
    getCouponInfo() {
      couponApi.getCouponInfo().then(response => {
        this.coupons = response.data.availableCoupons
        this.disabledCoupons = response.data.disabledCoupons
      });
    },
    // 下单、支付
    sureOrder() {
      this.loading = true;
      let orderFormVo = {
        'courseId': this.courseId,
        'couponId': this.couponId,
        'couponUseId': this.couponUseId
      }
      orderApi.submitOrder(orderFormVo).then(response => {
        this.loading = false
        let orderId = response.data
        this.$router.push({path: '/pay/' + orderId})
      })
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
