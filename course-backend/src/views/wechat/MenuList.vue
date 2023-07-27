<template>
  <div class="app-container">
    <!-- 工具条 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" size="mini" @click="openDisplay">添加菜单</el-button>
      <el-button class="btn-add" size="mini" @click="syncMenu">同步菜单</el-button>
    </el-card>

    <!-- 菜单数据列表 -->
    <el-table :data="list" style="width: 100%; margin-bottom: 20px;" row-key="id" border default-expand-all
              :tree-props="{children: 'children'}"
    >
      <el-table-column label="名称" prop="name" width="350"></el-table-column>
      <el-table-column label="类型" width="100">
        <template slot-scope="scope">
          {{ scope.row.type === 'view' ? '链接' : scope.row.type === 'click' ? '事件' : '' }}
        </template>
      </el-table-column>
      <el-table-column label="菜单 URL" prop="url"></el-table-column>
      <el-table-column label="菜单 KEY" prop="menuKey" width="130"></el-table-column>
      <el-table-column label="排序号" prop="sort" width="70"></el-table-column>
      <el-table-column label="操作" width="170" align="center">
        <template slot-scope="scope">
          <el-button v-if="scope.row.parentId > 0" type="text" size="mini" @click="openShow(scope.row.id)">修改
          </el-button>
          <el-button v-if="scope.row.parentId > 0" type="text" size="mini" @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 菜单添加修改弹框 -->
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="flashPromotionForm" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="选择一级菜单">
          <el-select v-model="menu.parentId" placeholder="请选择">
            <el-option v-for="item in list" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>

        <!-- 菜单名称 -->
        <el-form-item v-if="menu.parentId === 1" label="菜单名称">
          <!-- 一级菜单为直播，查询所有直播课程并展示 -->
          <el-select v-model="menu.name" placeholder="请选择" @change="liveCourseChanged">
            <el-option v-for="item in liveCourseList" :key="item.id" :label="item.courseName" :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item v-else-if="menu.parentId === 2" label="菜单名称">
          <!-- 一级菜单为课程，查询所有课程并展示 -->
          <el-select v-model="menu.name" placeholder="请选择" @change="subjectChanged">
            <el-option v-for="item in subjectList" :key="item.id" :label="item.title" :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item v-else-if="menu.parentId === 3" label="菜单名称">
          <!-- 一级菜单为我的，展示输入框 -->
          <el-input v-model="menu.name"/>
        </el-form-item>

        <el-form-item label="菜单类型">
          <el-radio-group v-model="menu.type">
            <el-radio label="view">链接</el-radio>
            <el-radio label="click">事件</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="menu.type === 'view'" label="链接">
          <el-input v-model="menu.url"/>
        </el-form-item>
        <el-form-item v-else-if="menu.type === 'click'" label="菜单 KEY">
          <el-input v-model="menu.menuKey"/>
        </el-form-item>
        <el-form-item label="排序">
          <el-input v-model="menu.sort"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>


<script>
import menuApi from '@/api/wechat/menu'
import subjectApi from '@/api/vod/subject'
//import liveCourseApi from '@/api/live/liveCourse'

const defaultForm = {
  id: null,
  // 一级菜单：1：直播；2：课程；3：我的
  parentId: 1,
  name: '',
  nameId: null,
  sort: 1,
  type: 'view',
  menuKey: '',
  url: ''
}

export default {
  data() {
    return {
      list: [],
      liveCourseList: [],
      subjectList: [],
      dialogVisible: false,
      menu: defaultForm,
      saveBtnDisabled: false
    }
  },

  // 当页面加载时获取数据
  created() {
    this.fetchData()
    this.fetchSubjects()
    // this.fetchLiveCourse()
  },

  methods: {
    fetchData() {
      menuApi.listMenuDetails().then(response => {
        this.list = response.data
      })
    },
    openDisplay() {
      this.dialogVisible = true
      this.menu = Object.assign({}, defaultForm)
    },
    openShow(id) {
      menuApi.getById(id).then(response => {
        this.menu = response.data
      })
      this.dialogVisible = true
    },
    saveOrUpdate() {
      // 防止表单重复提交
      this.saveBtnDisabled = true
      if (!this.menu.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },
    // 保存菜单
    saveData() {
      menuApi.save(this.menu).then(response => {
        if (response.code) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.dialogVisible = false
          this.fetchData(this.page)
        }
      })
    },
    // 删除菜单
    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 点击确定，远程调用 ajax
        return menuApi.removeById(id)
      }).then((response) => {
        this.$message.success(response.message)
        this.fetchData(this.page)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消删除'
        })
      })
    },
    // 更新记录
    updateData() {
      menuApi.updateById(this.menu).then(response => {
        if (response.code) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.dialogVisible = false
          this.fetchData(this.page)
        }
      })
    },

    // TODO 查询所有直播课程
    // fetchLiveCourse() {
    //   liveCourseApi.findLatelyList().then(response => {
    //     this.liveCourseList = response.data
    //     this.liveCourseList.push({'id': 0, 'courseName': '全部列表'})
    //   })
    // },
    liveCourseChanged(item) {
      // this.menu.name = item.courseName
      // if(item.id === 0) {
      //   this.menu.url = '/live'
      // } else {
      //   this.menu.url = '/liveInfo/' + item.id
      // }
    },
    // 查询顶级课程分类
    fetchSubjects() {
      subjectApi.getSubjects(0).then(res => {
        this.subjectList = res.data
      })
    },
    subjectChanged(item) {
      this.menu.name = item.title
      this.menu.url = '/course/' + item.id
    },
    // 同步最新菜单到微信后台
    syncMenu() {
      this.$confirm('你确定上传菜单吗, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return menuApi.syncMenu()
      }).then((response) => {
        this.$message.success(response.message)
        this.fetchData()
      }).catch(error => {
        this.$message.info('取消同步')
      })
    }
  }
}
</script>
