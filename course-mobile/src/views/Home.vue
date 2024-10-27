<template>
  <div>
    <van-image width="100%" height="200" src="https://cdn.uviewui.com/uview/swiper/1.jpg" />

    <van-grid clickable :column-num="2">
      <van-grid-item icon="like-o" text="后端开发" to="/course/1" />
      <van-grid-item icon="star-o" text="前端开发" to="/course/3" />
      <van-grid-item icon="fire-o" text="大数据" to="/course/3" />
      <van-grid-item icon="cart-circle-o" text="课程直播" to="/play/1/3" />
      <van-grid-item icon="send-gift-o" text="购买课程" to="/trade/1" />
      <van-grid-item icon="gift-card-o" text="订单支付" to="/pay/1" />
    </van-grid>


  </div>
</template>

<script>
export default {
  name: "Home",

};
</script>

<style lang="scss" scoped>
.list {
  li {
    margin: 10px;
    padding-bottom: 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #e5e5e5;

    div {
      width: calc(100% - 60px);
      display: flex;
      flex-direction: column;

      h1 {
        font-size: 16px;
      }

      span {
        margin: 0;
        font-size: 12px;
        color: gray;
      }
    }

    p {
      text-align: center;
      color: orangered;
      font-size: 12px;
      width: 60px;
    }
  }
}
</style>
