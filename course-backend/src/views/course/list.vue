<template>
  <div class="app-container">
    <!--查询表单-->
    <el-card class="operate-container" shadow="never">
      <el-form :inline="true" class="demo-form-inline">
        <!-- 一级分类 -->
        <el-form-item label="课程类别">
          <el-select v-model="searchObj.subjectParentId" placeholder="请选择" @change="subjectLevelOneChanged">
            <el-option v-for="subject in subjectList" :key="subject.id" :label="subject.title" :value="subject.id"/>
          </el-select>
          <!-- 二级分类 -->
          <el-select v-model="searchObj.subjectId" placeholder="请选择">
            <el-option v-for="subject in subjectLevelTwoList" :key="subject.id" :label="subject.title" :value="subject.id"/>
          </el-select>
        </el-form-item>
        <!-- 标题 -->
        <el-form-item label="标题">
          <el-input v-model="searchObj.title" placeholder="课程标题"/>
        </el-form-item>
        <!-- 讲师 -->
        <el-form-item label="讲师">
          <el-select v-model="searchObj.teacherId" placeholder="请选择讲师">
            <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id"/>
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
    </el-card>

    <!-- 工具按钮 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" type="primary" style="margin-left: 10px" @click="add()">添加</el-button>
    </el-card>

    <!-- 表格 -->
    <el-table :data="list" border stripe>
      <el-table-column label="#" width="50">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column label="封面" width="200" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.cover" alt="scope.row.title" width="100%">
        </template>
      </el-table-column>
      <el-table-column label="课程信息">
        <template slot-scope="scope">
          <a href="">{{ scope.row.title }}</a>
          <p>
            分类：{{ scope.row.parentSubjectName }} > {{ scope.row.subjectName }}
          </p>
          <p>
            课时：{{ scope.row.lessonNum }} /
            浏览：{{ scope.row.viewCount }} /
            付费学员：{{ scope.row.buyCount }}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="讲师" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.teacherName }}
        </template>
      </el-table-column>
      <el-table-column label="价格(元)" width="100" align="center">
        <template slot-scope="scope">
          <el-tag v-if="Number(scope.row.price) === 0" type="success">免费</el-tag>
          <!-- 后端解决保留两位小数的问题，前端不用处理 -->
          <el-tag v-else>{{ scope.row.price }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="课程状态" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 0 ? 'warning' : 'success'">{{scope.row.status === 0 ? '未发布' : '已发布' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" width="140" align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime ? scope.row.createTime.substr(0, 16) : '' }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="210" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/'+scope.row.id">
            <el-button type="text" icon="el-icon-edit">修改</el-button>
          </router-link>
          <router-link :to="'/course/chapter/'+scope.row.id">
            <el-button type="text" icon="el-icon-edit">编辑大纲</el-button>
          </router-link>
          <router-link :to="'/course/chart/'+scope.row.id">
            <el-button type="text" icon="el-icon-edit">课程统计</el-button>
          </router-link>
          <el-button type="text" icon="el-icon-delete" @click="removeById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="changePageSize"
      @current-change="changeCurrentPage"
    />
  </div>
</template>

<script>
import courseApi from '@/api/course'
import teacherApi from '@/api/teacher'
import subjectApi from '@/api/subject'

export default {

  data() {
    return {
      list: [],
      total: 0,
      page: 1,
      limit: 10,
      searchObj: {
        // 解决查询表单无法选中二级类别
        subjectId: ''
      },
      teacherList: [],
      subjectList: [],
      subjectLevelTwoList: []
    }
  },
  created() {
    this.fetchData()
    this.initSubjectList()
    this.initTeacherList()
  },
  methods: {
    fetchData() {
      courseApi.getCoursePageData(this.page, this.limit, this.searchObj).then(res => {
        this.total = res.data.total
        this.list = res.data.records
      })
    },

    initTeacherList() {
      teacherApi.getAllTeachers().then(response => {
        this.teacherList = response.data
      })
    },

    initSubjectList() {
      subjectApi.getSubjects(0).then(response => {
        this.subjectList = response.data
      })
    },

    subjectLevelOneChanged(value) {
      subjectApi.getSubjects(value).then(response => {
        this.subjectLevelTwoList = response.data
        this.searchObj.subjectId = ''
      })
    },

    add() {
      this.$router.push({ path: '/course/info' })
    },

    // 每页记录数改变，size：回调参数，表示当前选中的“每页条数”
    changePageSize(size) {
      this.limit = size
      this.fetchData()
    },

    // 改变页码，page：回调参数，表示当前选中的“页码”
    changeCurrentPage(page) {
      this.page = page
      this.fetchData()
    },

    // 重置表单
    resetData() {
      this.searchObj = {}
      this.subjectLevelTwoList = []
      this.fetchData()
    },

    // 根据id删除数据
    removeById(id) {
      this.$confirm('此操作将永久删除该课程，以及关联的章节和视频，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return courseApi.removeById(id)
      }).then(response => {
        this.fetchData()
        this.$message.success(response.message)
      }).catch((response) => {
        if (response === 'cancel') {
          this.$message.info('取消删除')
        }
      })
    }
  }
}
</script>
