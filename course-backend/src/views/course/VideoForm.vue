<template>
  <!-- 添加和修改课时表单 -->
  <el-dialog :visible="dialogVisible" title="添加课时" @close="close()">
    <el-form :model="video" label-width="120px">
      <el-form-item label="课时标题">
        <el-input v-model="video.title"/>
      </el-form-item>
      <el-form-item label="课时排序">
        <el-input-number v-model="video.sort" :min="0"/>
      </el-form-item>
      <el-form-item label="是否免费">
        <el-radio-group v-model="video.isFree">
          <el-radio :label="0">免费</el-radio>
          <el-radio :label="1">默认</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 上传视频 -->
      <el-form-item label="上传视频">
        <el-upload ref="upload" :auto-upload="false" :on-success="handleUploadSuccess" :on-error="handleUploadError" :on-exceed="handleUploadExceed" :file-list="fileList" :limit="1" :before-remove="handleBeforeRemove" :on-remove="handleOnRemove" :action="BASE_API+'/admin/vod/upload'">
          <el-button slot="trigger" size="small" type="primary">选择视频</el-button>
          <el-button :disabled="uploadBtnDisabled" style="margin-left: 10px;" size="small" type="success" @click="submitUpload()">上传</el-button>
        </el-upload>
      </el-form-item>
    </el-form>

    <!-- 取消与确定 -->
    <div slot="footer" class="dialog-footer">
      <el-button @click="close()">取 消</el-button>
      <el-button type="primary" @click="saveOrUpdate()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import videoApi from '@/api/video'
import vodApi  from '@/api/vod'

export default {
  data() {
    return {
      // TODO replace
      BASE_API: 'http://localhost:8081',
      dialogVisible: false,
      video: {
        sort: 0,
        free: false
      },
      fileList: [],
      uploadBtnDisabled: false
    }
  },

  methods: {
    open(chapterId, videoId) {
      this.dialogVisible = true
      this.video.chapterId = chapterId

      // 编辑章节小节，回显小节数据
      if (videoId) {
        videoApi.getById(videoId).then(response => {
          this.video = response.data
          // 回显
          if (this.video.videoOriginalName) {
            this.fileList = [{ 'name': this.video.videoOriginalName }]
          }
        })
      }
    },

    close() {
      this.dialogVisible = false
      // 重置表单
      this.resetForm()
    },

    resetForm() {
      this.video = {
        sort: 0,
        free: false
      }
      // 重置视频上传列表
      this.fileList = []
    },

    saveOrUpdate() {
      // 传入 videoId 则为编辑小节
      if (this.video.id) {
        this.update()
      } else {
        this.save()
      }
    },

    save() {
      this.video.courseId = this.$parent.$parent.courseId
      videoApi.save(this.video).then(response => {
        this.$message.success(response.message)
        // 关闭组件
        this.close()
        // 刷新列表
        this.$parent.fetchNodeList()
      })
    },

    update() {
      videoApi.updateById(this.video).then(response => {
        this.$message.success(response.message)
        // 关闭组件
        this.close()
        // 刷新列表
        this.$parent.fetchNodeList()
      })
    },

    // 上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },

    // 视频上传
    submitUpload() {
      this.uploadBtnDisabled = true
      // 提交上传请求
      this.$refs.upload.submit()
    },

    // 视频上传成功的回调
    handleUploadSuccess(response, file, fileList) {
      this.$message.success(response.message)
      this.uploadBtnDisabled = false
      this.video.videoSourceId = response.data
      this.video.videoOriginalName = file.name
    },

    // 失败回调
    handleUploadError() {
      this.uploadBtnDisabled = false
      this.$message.error('上传视频失败')
    },

    // 删除视频文件确认
    handleBeforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },

    // 执行视频文件的删除
    handleOnRemove(file, fileList) {
      if (this.video.videoSourceId) {
        vodApi.removeVideoById(this.video.videoSourceId).then(response => {
          this.$message.success(response.message)
          this.video.videoSourceId = ''
          this.video.videoOriginalName = ''
          videoApi.updateById(this.video)
        })
      }
    }
  }
}
</script>
