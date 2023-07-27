<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="入驻时间">
        <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro"/>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" :rows="10" type="textarea"/>
      </el-form-item>
      <el-form-item label="讲师头像">
        <el-upload :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload" :on-error="handleAvatarError"
                   :action="contextPath+'/admin/vod/transfer/upload'" :show-file-list="false" class="avatar-uploader">
          <img v-if="teacher.avatar" :src="teacher.avatar" alt="讲师头像"/>
          <i v-else class="el-icon-plus avatar-uploader-icon"/>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate()" :disabled="saveBtnDisabled">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from '@/api/teacher'


export default {
  name: 'Form',
  data() {
    return {
      // TODO replace
      contextPath: 'http://localhost:8081',
      teacher: {
        sort: 0,
        level: 1
      },
      saveBtnDisabled: false
    }
  },
  created() {
    // 读取路由路径中的 id 值
    if (this.$route.params.id) {
      this.getTeacher(this.$route.params.id)
    }
  },
  methods: {
    saveOrUpdate() {
      this.saveBtnDisabled = true
      // teacher 存在 id，当前为更新操作
      if (this.teacher.id) {
        this.updateTeacher()
      } else {
        this.saveTeacher()
      }
    },
    saveTeacher() {
      api.saveTeacher(this.teacher).then(res => {
          this.$message.success(res.message)
          // 跳转到讲师列表页面
          this.$router.push('/vod/teacher/list')
        }
      )
    },
    getTeacher(tid) {
      api.getTeacher(tid).then(res => {
        this.teacher = res.data
      })
    },
    updateTeacher() {
      api.updateTeacher(this.teacher).then(res => {
        this.$message.success(res.message)
        // 跳转到讲师列表页面
        this.$router.push('/vod/teacher/list')
      })
    },
    // 头像上传成功
    handleAvatarSuccess(res) {
      if (res.data) {
        this.teacher.avatar = res.data
        // 强制重新渲染页面
        this.$forceUpdate()
      }
    },
    // 头像上传网络错误
    handleAvatarError() {
      this.$message.warning('上传讲师头像失败')
    },
    // 头像上传前校验
    beforeAvatarUpload(file) {
      const isMin = file.size / 1024 / 1024 < 10
      const isImg = file.type === 'image/jpeg' || file.type === 'image/png'

      if (!isImg) {
        this.$message.error('图片只能是 JPG 或 PNG 格式!')
      }
      if (!isMin) {
        this.$message.error('图片大小不能超过 10MB!')
      }
      return isImg && isMin
    }
  }
}
</script>

<style scoped>
.avatar-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar-uploader .avatar-uploader-icon:hover {
  border-color: #409EFF;
}

.avatar-uploader img {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
