<template>
  <div>
    <van-image width="100%" height="200" src="https://cdn.uviewui.com/uview/swiper/1.jpg"/>
  </div>
</template>

<script>
export default {
  name: "Home"
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
