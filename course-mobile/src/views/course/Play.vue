<template>
  <div>
    <video id="player-container-id" preload="auto" width="600" height="400" playsinline webkit-playsinline x5-playsinline></video>
    <div class="course_teacher_price_box">
      <div class="course_teacher_price">
        <div class="course_price">课程：</div>
        <div class="course_teacher_price_box">{{ course.title }}</div>
        <div class="course_teacher">主讲： {{ course.teacherName }}</div>
      </div>
    </div>

    <div class="course_contents">
      <div class="course_title_font">课程详情</div>
      <div>{{ course.description }}</div>
    </div>

    <div class="course_contents">
      <div class="course_title_font">课程大纲</div>
      <div class="gap"></div>
      <van-collapse v-model="activeNames">
        <van-collapse-item :title="item.title" :name="item.id" v-for="item in chapterVoList" :key="item.id">
          <ul class="course_chapter_list" v-for="child in item.videos" :key="child.id">
            <h2 :style="activeVideoId === child.id ? 'color: blue' : ''">{{ child.title }}</h2>
            <p v-if="child.isFree === 1">
              <van-button @click="see(child)" type="warning" size="mini" plain>免费观看</van-button>
            </p>
            <p v-else>
              <van-button @click="see(child)" type="warning" size="mini" plain>前往观看</van-button>
            </p>
          </ul>
        </van-collapse-item>
      </van-collapse>
    </div>

    <van-loading :vertical="loading" v-show="loading">加载中...</van-loading>
  </div>
</template>

<script>
import courseApi from '../../api/course'
import vodApi from '../../api/vod'


export default {
  data() {
    return {
      loading: false,
      courseId: null,
      videoId: null,
      course: {},
      chapterVoList: [],
      isBuy: false,
      // 记录展开的章节 ID
      activeNames: ["1"],
      // 记录当前正在播放的视频
      activeVideoId: 0,
      player: null
    };
  },

  created() {
    this.courseId = this.$route.params.courseId;
    this.videoId = this.$route.params.videoId || '1';
    this.fetchData();
    this.getPlayAuth(this.videoId);
  },

  methods: {
    // 查询课程信息
    fetchData() {
      this.loading = true;
      courseApi.courseDetail(this.courseId).then(response => {
        this.course = response.data;
        this.chapterVoList = response.data.chapters
        this.loading = false;
      });
    },
    // 观看视频
    see(video) {
      let videoId = video.id;
      this.getPlayAuth(videoId);
    },
    getPlayAuth(videoId) {
      if (this.player != null) {
        this.player.dispose();
      }

      vodApi.getVideo(videoId).then(response => {
        // 展开章节
        this.activeNames = [response.data.chapterId]
        // 选中播放视频
        this.activeVideoId = response.data.videoId
        // 播放视频
        this.play(response.data);
      })
    },
    //视频播放
    play(data) {
      this.player = TCPlayer("player-container-id", {
        /* player-container-id 为播放器容器ID，必须与 html 中一致 */
        fileID: data.videoSourceId, /* 请传入需要播放的视频 fileID */
        appID: data.appId, /* 请传入点播账号的子应用 appID */
        psign: "" /*其他参数请在开发文档中查看 */
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

    .course_teacher {
      margin-left: 20px;
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
