<template>
  <div class="app-container">
    <!-- 课程信息表单 -->
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"/>
        </el-select>
      </el-form-item>
      <!-- 所属分类：级联下拉列表 -->
      <el-form-item label="课程类别">
        <!-- 一级分类 -->
        <el-select v-model="courseInfo.subjectParentId" placeholder="请选择" @change="subjectChanged">
          <el-option v-for="subject in subjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option v-for="subject in subjectLevelTwoList" :key="subject.id" :label="subject.title" :value="subject.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>
      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <el-input v-model="courseInfo.description" type="textarea" rows="5"/>
      </el-form-item>
      <!-- 课程封面 -->
      <el-form-item label="课程封面">
        <el-upload :show-file-list="false" :on-success="handleCoverSuccess" :before-upload="beforeCoverUpload" :on-error="handleCoverError" :action="url+'/admin/vod/transfer/upload'" class="cover-uploader">
          <img v-if="courseInfo.cover" :src="courseInfo.cover" alt="课程封面">
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为 0 元"/>
        元
      </el-form-item>
    </el-form>

    <div style="text-align:center">
      <el-button :disabled="saveBtnDisabled" type="primary" @click="saveAndNext()">保存并下一步</el-button>
    </div>
  </div>
</template>

<script>
import courseApi from '@/api/vod/course'
import teacherApi from '@/api/vod/teacher'
import subjectApi from '@/api/vod/subject'

import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo: {
        price: 0,
        lessonNum: 0,
        // 以下解决表单数据不全时 insert 语句非空校验
        teacherId: '',
        subjectId: '',
        subjectParentId: '',
        cover: '',
        description: ''
      },
      teacherList: [],
      subjectList: [],
      subjectLevelTwoList: []
    }
  },
  computed: {
    ...mapGetters(['url'])
  },
  created() {
    // 判断当前是否是编辑操作
    if (this.$parent.courseId) {
      this.fetchCourseInfoById(this.$parent.courseId)
    } else {
      // 初始化分类列表
      this.initSubjectList()
    }
    // 获取讲师列表
    this.initTeacherList()
  },
  methods: {
    // 获取讲师列表
    initTeacherList() {
      teacherApi.getAllTeachers().then(response => {
        this.teacherList = response.data
      })
    },

    // 初始化分类列表
    initSubjectList() {
      subjectApi.getSubjects(0).then(response => {
        this.subjectList = response.data
      })
    },

    // 选择一级分类，切换二级分类
    subjectChanged(value) {
      subjectApi.getSubjects(value).then(response => {
        this.courseInfo.subjectId = ''
        this.subjectLevelTwoList = response.data
      })
    },

    // 上传成功回调
    handleCoverSuccess(res) {
      if (res.data) {
        this.courseInfo.cover = res.data
      }
    },

    // 上传校验
    beforeCoverUpload(file) {
      const isMin = file.size / 1024 / 1024 < 10
      const isImg = file.type === 'image/jpeg' || file.type === 'image/png'

      if (!isImg) {
        this.$message.error('图片只能是 JPG 或 PNG 格式!')
      }
      if (!isMin) {
        this.$message.error('图片大小不能超过 10MB!')
      }
      return isImg && isMin
    },

    // 错误处理
    handleCoverError() {
      this.$message.error('文件上传失败')
    },

    // 保存并下一步
    saveAndNext() {
      this.saveBtnDisabled = true
      if (this.$parent.courseId) {
        this.updateData()
      } else {
        this.saveData()
      }
    },

    // 保存
    saveData() {
      courseApi.saveCourseInfo(this.courseInfo).then(response => {
        this.$message.success(response.message)
        // 获取 courseId
        this.$parent.courseId = response.data
        // 下一步
        this.$parent.active = 1
      })
    },

    // 更新
    updateData() {
      courseApi.updateCourseInfoById(this.courseInfo).then(response => {
        this.$message.success(response.message)
        // 获取 courseId
        this.$parent.courseId = response.data
        // 下一步
        this.$parent.active = 1
      })
    },

    // 获取课程信息
    fetchCourseInfoById(id) {
      courseApi.getCourseInfoById(id).then(response => {
        this.courseInfo = response.data
        // 初始化分类列表
        subjectApi.getSubjects(0).then(response => {
          this.subjectList = response.data
          // 填充二级菜单：遍历一级菜单列表
          this.subjectList.forEach(subject => {
            // 找到和 courseInfo.subjectParentId 一致的父类别记录
            if (subject.id === this.courseInfo.subjectParentId) {
              // 拿到当前类别下的子类别列表，将子类别列表填入二级下拉菜单列表
              subjectApi.getSubjects(subject.id).then(response => {
                this.subjectLevelTwoList = response.data
              })
            }
          })
        })
      })
    },
  }
}
</script>

<style scoped>
.tinymce-container {
  position: relative;
  line-height: normal;
}

.cover-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  font-size: 28px;
  color: #8c939d;
  width: 640px;
  height: 357px;
  line-height: 357px;
  text-align: center;
}

.cover-uploader .avatar-uploader-icon:hover {
  border-color: #409EFF;
}

.cover-uploader img {
  width: 640px;
  height: 357px;
  display: block;
}
</style>
