<template>
  <div>
    <van-image width="100%" height="200" :src="course.cover"/>
    <!-- 课程数据 -->
    <van-row>
      <van-col span="8">
        <div class="course_count">
          <h1>购买量</h1>
          <p>{{ course.buyCount }}</p>
        </div>
      </van-col>
      <van-col span="8">
        <div class="course_count">
          <h1>课时数</h1>
          <p>{{ course.lessonNum }}</p>
        </div>
      </van-col>
      <van-col span="8">
        <div class="course_count">
          <h1>浏览量</h1>
          <p>{{ course.viewCount }}</p>
        </div>
      </van-col>
    </van-row>

    <h1 class="van-ellipsis course_title">{{ course.title }}</h1>

    <!-- 购买课程 -->
    <div class="course_teacher_price_box">
      <div class="course_teacher_price">
        <div class="course_price">价格：</div>
        <div class="course_price_number">￥{{ course.price }}</div>
      </div>
      <div>
        <!-- course.price == '0' 不可替换为 === -->
        <van-button @click="buy()" v-show="course.price != '0.00'" plain type="warning" size="mini">购买课程</van-button>
      </div>
    </div>

    <!-- 讲师信息 -->
    <div class="course_teacher_price_box">
      <div class="course_teacher_box">
        <div class="course_teacher">主讲： {{ course.teacherName }}</div>
        <van-image :src="course.teacherAvatar" round width="50px" height="50px"/>
      </div>
    </div>

    <div class="course_contents">
      <div class="course_title_font">课程详情</div>
      <van-divider :style="{ margin: '5px 0 ' }"/>
      <div class="course_content" v-html="course.description">
      </div>

      <div class="course_title_font">课程大纲</div>
      <div class="gap"></div>
      <van-collapse v-model="activeNames">
        <van-collapse-item :title="item.title" :name="item.id" v-for="item in chapterVoList" :key="item.id">
          <ul class="course_chapter_list" v-for="child in item.videos" :key="child.id">
            <h2>{{ child.title }}</h2>
            <p v-if="child.isFree === 1">
              <van-button @click="play(child)" type="warning" size="mini" plain>免费观看</van-button>
            </p>
            <p v-else>
              <van-button @click="play(child)" type="warning" size="mini" plain>前往观看</van-button>
            </p>
          </ul>
        </van-collapse-item>
      </van-collapse>
    </div>

    <van-loading vertical="true" v-show="loading">加载中...</van-loading>
  </div>
</template>


<script>
import courseApi from '../../api/course'

export default {
  data() {
    return {
      loading: false,
      courseId: null,
      course: {},
      chapterVoList: [],
      isBuy: false,
      activeNames: ["1"]
    };
  },

  created() {
    this.courseId = this.$route.params.courseId;
    this.fetchData();
  },

  methods: {
    fetchData() {
      this.loading = true;
      courseApi.courseDetail(this.courseId).then(response => {
        this.course = response.data;
        this.chapterVoList = response.data.chapters;
        this.loading = false;
      });
    },
    play(video) {
      let videoId = video.id;
      this.$router.push({path: '/play/' + this.courseId + '/' + videoId})
    },
    buy() {
      this.$router.push({path: '/trade/' + this.courseId})
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

    .course_teacher {
      margin-left: 20px;
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
