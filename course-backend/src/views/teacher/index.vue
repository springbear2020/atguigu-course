<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-card class="operate-container" shadow="never">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="conditions.name" placeholder="讲师姓名（支持模糊查询）"/>
        </el-form-item>
        <el-form-item label="头衔">
          <el-select v-model="conditions.level" clearable placeholder="头衔">
            <el-option value="1" label="高级讲师"/>
            <el-option value="0" label="首席讲师"/>
          </el-select>
        </el-form-item>
        <el-form-item label="入驻时间">
          <el-date-picker v-model="conditions.joinDateBegin" placeholder="开始时间" value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-form-item label="-">
          <el-date-picker v-model="conditions.joinDateEnd" placeholder="结束时间" value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchPageData()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
    </el-card>

    <!-- 工具按钮 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" @click="addTeacher()" style="margin-left: 10px;">添加讲师</el-button>
      <el-button class="btn-add" @click="batchRemove()">批量删除</el-button>
    </el-card>

    <!-- 表格数据 -->
    <el-table :data="list" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection"/>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80"/>
      <el-table-column label="头衔" width="90">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success" size="mini">高级讲师</el-tag>
          <el-tag v-if="scope.row.level === 0" size="mini">首席讲师</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="简介"/>
      <el-table-column prop="sort" label="排序" width="60"/>
      <el-table-column prop="joinDate" label="入驻时间" width="160"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="removeById(scope.row.id)">删除</el-button>
          <router-link :to="'/vod/teacher/edit/'+scope.row.id">
            <el-button type="text" size="mini">修改</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="pageNum"
      :total="total"
      :page-size="pageSize"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />
  </div>
</template>

<script>
import api from '@/api/teacher.js'

export default {
  name: 'List',
  data() {
    return {
      list: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      conditions: {},
      idList: []
    }
  },
  created() {
    this.fetchPageData()
  },
  methods: {
    fetchPageData() {
      api.getTeachers(this.pageNum, this.pageSize, this.conditions).then(
        res => {
          this.list = res.data.records
          this.total = res.data.total
          // this.pageNum = res.data.current;
          // this.pageSize = res.data.size;
        })
    },
    changePageSize(newPageSize) {
      this.pageSize = newPageSize
      this.fetchPageData()
    },
    changeCurrentPage(newPageNum) {
      this.pageNum = newPageNum
      this.fetchPageData()
    },
    resetData() {
      this.conditions = {}
      this.fetchPageData()
    },
    removeById(tid) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '删除提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return api.deleteTeacher(tid)
      }).then((response) => {
        this.fetchPageData()
        this.$message.success(response.message)
      })
    },
    addTeacher() {
      this.$router.push('/vod/teacher/insert')
    },
    // 教师多选改变事件
    handleSelectionChange(selections) {
      this.idList = []
      selections.forEach((item, index) => {
        this.idList.push(item.id)
      })
    },
    batchRemove() {
      let size = this.idList.length
      if (size > 0) {
        this.$confirm(`将要删除 ${size} 条讲师数据, 是否继续?`, '删除提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return api.deleteTeachers(this.idList)
        }).then((response) => {
          this.fetchPageData()
          this.$message.success(response.message)
        })
      } else {
        this.$message.warning('请先选择您要删除的讲师数据')
      }
    }
  }
}
</script>
