<template>
  <div class="app-container">
    <!-- 文件导入导出 -->
    <div class="el-toolbar">
      <div class="el-toolbar-body" style="justify-content: flex-start;">
        <el-button type="primary" @click="dialogImportVisible = true">导入</el-button>
        <el-button type="primary" @click="exportData">导出</el-button>
      </div>
    </div>

    <!-- 数据表格 -->
    <el-table :data="list" style="width: 100%" row-key="id" border lazy :load="lazyLoad"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="title" label="名称" width="150"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
    </el-table>

    <!-- 文件导入弹层 -->
    <el-dialog title="导入" :visible.sync="dialogImportVisible" width="480px">
      <el-form label-position="right" label-width="170px">
        <el-form-item label="文件">
          <el-upload :multiple="false" :on-success="onUploadSuccess" class="upload-demo"
                     :action="url+'/admin/vod/subject/import'"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传 excel 文件，且不超过 500KB</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogImportVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import api from '@/api/vod/subject'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      list: [],
      // 文件选择弹层框是否展示
      dialogImportVisible: false
    }
  },
  computed: {
    ...mapGetters(['url'])
  },
  created() {
    // 获取父级课程分类
    this.getSubList(0)
  },
  methods: {
    //数据字典列表
    getSubList(id) {
      api.getSubjects(id).then(response => {
        this.list = response.data
        this.resolveChildNode()
      })
    },
    // 解析数据，判断是否拥有孩子节点
    resolveChildNode() {
      this.list.forEach((item) => {
        if (item.parentId === 0) {
          item.hasChildren = true
        }
      })
    },
    // 懒加载，点击时打开子课程目录
    lazyLoad(tree, treeNode, resolve) {
      api.getSubjects(tree.id).then(response => {
        resolve(response.data)
      })
    },
    exportData() {
      window.open(this.url + '/admin/vod/subject/export')
    },
    onUploadSuccess(res) {
      this.$message.success(res.message)
      this.dialogImportVisible = false
      this.getSubList(0)
    }
  }
}
</script>

<style scoped>
.el-button {
  margin-bottom: 10px;
}
</style>
