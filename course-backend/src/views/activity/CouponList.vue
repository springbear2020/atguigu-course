<template>
  <div class="app-container">
    <!-- 工具条 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" type="primary" @click="add()">添加</el-button>
    </el-card>

    <!-- banner列表 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="数据正在加载......" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="couponName" label="购物券名称"/>

      <el-table-column prop="couponType" label="购物券类型">
        <template slot-scope="scope">
          {{ scope.row.couponType === 'REGISTER' ? '注册券' : '推荐赠送券' }}
        </template>
      </el-table-column>

      <el-table-column label="规则">
        <template slot-scope="scope">
          {{ '现金券：' + scope.row.amount + '元' }}
        </template>
      </el-table-column>

      <el-table-column label="使用范围 ">所有商品</el-table-column>

      <el-table-column prop="publishCount" label="发行数量"/>

      <el-table-column prop="expireTime" label="过期时间"/>

      <el-table-column prop="createTime" label="创建时间"/>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/activity/coupon/edit/'+scope.row.id">
            <el-button size="mini" type="text">修改</el-button>
          </router-link>
          <el-button size="mini" type="text" @click="removeDataById(scope.row.id)">删除</el-button>
          <router-link :to="'/activity/coupon/show/'+scope.row.id">
            <el-button size="mini" type="text">详情</el-button>
          </router-link>
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
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changeSize"
    />
  </div>
</template>

<script>
import api from '@/api/activity/coupon'

export default {
  data() {
    return {
      listLoading: true,
      list: null,
      total: 0,
      page: 1,
      limit: 10,
      searchObj: {},
      multipleSelection: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // 每页显示的数量发生变化
    changeSize(size) {
      this.limit = size
      this.fetchData(1)
    },
    // 新增
    add() {
      this.$router.push({ path: '/activity/coupon/add' })
    },
    // 获取数据
    fetchData(page = 1) {
      this.page = page
      api.getPageList(this.page, this.limit, this.searchObj).then(
        response => {
          this.list = response.data.records
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    // 重置表单
    resetData() {
      this.searchObj = {}
      this.fetchData()
    },
    // 删除
    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return api.removeById(id)
      }).then((response) => {
        this.$message.success(response.message)
        this.fetchData(this.page)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消删除'
        })
      })
    }
  }
}
</script>
