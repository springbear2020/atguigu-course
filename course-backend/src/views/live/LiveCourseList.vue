<template>
  <div class="app-container">
     <!-- 工具条 -->
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets" style="margin-top: 5px"></i>
      <span style="margin-top: 5px">数据列表</span>
      <el-button class="btn-add" type="primary" @click="add">添 加</el-button>
    </el-card>

     <!-- 直播课程列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%; margin-top: 10px"
    >
      <el-table-column label="序号" width="50" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="封面" width="200" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.cover" width="100%" alt="课程封面" />
        </template>
      </el-table-column>
      <el-table-column prop="courseName" label="直播名称" />
      <el-table-column prop="startTime" label="直播时间">
        <template slot-scope="scope">
          {{ scope.row.startTime }} <br />
          {{ scope.row.endTime }}
        </template>
      </el-table-column>
      <el-table-column prop="endTime" label="直播结束时间" />
      <el-table-column prop="param.teacherName" label="直播老师" />
      <el-table-column label="头衔" width="90">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.param.teacherLevel === 1"
            type="success"
            size="mini"
            >高级讲师</el-tag
          >
          <el-tag v-if="scope.row.param.teacherLevel === 2" size="mini"
            >首席讲师</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="mini" @click="edit(scope.row.id)"
            >修改</el-button
          >
          <el-button
            type="text"
            size="mini"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
          <el-button type="text" size="mini" @click="showAccount(scope.row)"
            >账号</el-button>
          <br/>
          <router-link :to="'/live/config/' + scope.row.id">
            <el-button type="text" size="mini">课程配置</el-button>
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
      style="padding: 30px 0; text-align: center"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changeSize"/>

     <!-- 添加或修改对话框  -->
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="60%">
      <el-form
        ref="flashPromotionForm"
        label-width="150px"
        size="small"
        style="padding-right: 40px"
      >
         课程讲师 
        <el-form-item label="直播讲师">
          <el-select v-model="liveCourse.teacherId" placeholder="请选择">
            <el-option
              v-for="teacher in teacherList"
              :key="teacher.id"
              :label="teacher.name"
              :value="teacher.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="直播讲师登录密码" v-if="liveCourse.id === ''">
          <el-input v-model="liveCourse.password" />
        </el-form-item>
        <el-form-item label="直播名称">
          <el-input v-model="liveCourse.courseName" />
        </el-form-item>
        <el-form-item label="直播开始时间">
          <el-date-picker
            v-model="liveCourse.startTime"
            type="datetime"
            placeholder="选择开始日期"
            value-format="yyyy-MM-dd HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="直播结束时间">
          <el-date-picker
            v-model="liveCourse.endTime"
            type="datetime"
            placeholder="选择结束日期"
            value-format="yyyy-MM-dd HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="直播封面">
          <el-upload
            :show-file-list="false"
            :on-success="handleCoverSuccess"
            :before-upload="beforeCoverUpload"
            :on-error="handleCoverError"
            :action="url + '/admin/vod/transfer/upload'"
            class="cover-uploader"
          >
            <img v-if="liveCourse.cover" :src="liveCourse.cover" width="60%" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="直播详情">
          <el-input v-model="liveCourse.description" type="textarea" rows="5" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate()" size="small"
          >确 定</el-button
        >
      </span>
    </el-dialog>

        <!-- 查看账号对话框 -->
       <el-dialog title="查看账号" :visible.sync="accountDialogVisible" width="60%">
          <el-form ref="accountForm" label-width="150px" size="small" style="padding-right: 40px;">
            <div style="margin-left: 40px;">
              <h4>主播帮助信息</h4>
              <el-row style="height:35px;">
                <el-co>
                  <span class="spd-info">主播登录链接：</span>
                  <span class="spd-info"
                  >https://live.zhibodun.com/live/courseLogin.php?course_id={{
                      liveCourseAccount.courseId
                    }}&role=admin</span>
                </el-co>
              </el-row>
              <el-row style="height:35px;">
                <el-col>
                  <span class="spd-info">主播登录密码：{{ liveCourseAccount.liveKey }}</span>
                </el-col>
              </el-row>
            </div>
            <div style="margin-left: 40px;">
              <h4>主播客户端账号信息</h4>
              <el-row style="height:35px;">
                <el-col>
                  <span class="spd-info">主播登录账户：{{ liveCourseAccount.liveAccount }}</span>
                </el-col>
              </el-row>
              <el-row style="height:35px;">
                <el-col>
                  <span class="spd-info">主播登录密码：{{ liveCourseAccount.livePassword }}</span>
                </el-col>
              </el-row>
            </div> 

            <div style="margin-left: 40px;">
              <h4>助教信息</h4>
              <el-row style="height:35px;">
                <el-co>
                  <span class="spd-info">助教登录连接：</span>
                  <span class="spd-info"
                  >https://live.zhibodun.com/live/courseLogin.php?course_id={{
                      liveCourseAccount.courseId
                    }}&role=admin</span>
                </el-co>
              </el-row>
              <el-row style="height:35px;">
                <el-col>
                  <span class="spd-info">助教登录密码：{{ liveCourseAccount.adminKey }}</span>
                </el-col>
              </el-row>
            </div>
            <div style="margin-left: 40px;">
              <h4>学生观看信息</h4>
              <el-row style="height:35px;">
                <el-co>
                  <span class="spd-info">观看连接：</span>
                  <!-- TODO 修改直播课程观看链接 -->
                  <span class="spd-info">http://glkt-api.atguigu.cn/#/live/{{ liveCourseAccount.courseId }}</span>
                </el-co>
              </el-row>
            </div>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="accountDialogVisible = false" size="small">关 闭</el-button>
          </span>
        </el-dialog>
  </div>
</template>

<script>
import api from "@/api/live/live";
import teacherApi from "@/api/vod/teacher";
import { mapGetters } from "vuex";

const defaultForm = {
  id: "",
  courseName: "",
  startTime: "",
  endTime: "",
  teacherId: "",
  password: "",
  description: "",
  cover: "",
};

export default {
  data() {
    return {
      listLoading: false,
      list: null,
      total: 0,
      page: 1,
      limit: 10,
      searchObj: {},

      teacherList: [],

      dialogVisible: false,
      liveCourse: defaultForm,
      saveBtnDisabled: false,

      accountDialogVisible: false,
      liveCourseAccount: {
        courseId: "",
      },
    };
  },

  computed: {
    ...mapGetters(["url"]),
  },

  created() {
    this.fetchData();
    // 获取讲师列表
    this.initTeacherList();
  },

  methods: {
    // 当页码发生改变的时候
    changeSize(size) {
      this.limit = size;
      this.fetchData(1);
    },

    // 加载 banner 列表数据
    fetchData(page = 1) {
      // 异步获取远程数据（ajax）
      this.page = page;
      api.getPageList(this.page, this.limit).then((response) => {
        this.list = response.data.records;
        this.total = response.data.total;
        // 数据加载并绑定成功
        this.listLoading = false;
      });
    },

    // 获取讲师列表
    initTeacherList() {
      teacherApi.getAllTeachers().then((response) => {
        this.teacherList = response.data;
      });
    },

    // 重置查询表单
    resetData() {
      this.searchObj = {};
      this.fetchData();
    },

    // 根据 id 删除数据
    removeDataById(id) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // promise
          // 点击确定，远程调用ajax
          return api.removeById(id);
        })
        .then((response) => {
          if (response.code) {
            this.$message({
              type: "success",
              message: response.message,
            });
          }
          this.fetchData(this.page);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    // 保存或更新
    saveOrUpdate() {
      this.saveBtnDisabled = true;
      if (!this.liveCourse.id) {
        this.saveData();
      } else {
        this.updateData();
      }
    },

    // 打开新增编辑框
    add() {
      this.dialogVisible = true;
      this.liveCourse = Object.assign({}, defaultForm);
    },

    // 新增
    saveData() {
      api.save(this.liveCourse).then((response) => {
        this.$message({
          type: "success",
          message: response.message,
        });
        this.dialogVisible = false;
        this.fetchData(this.page);
      });
    },

    // 打开修改编辑框
    edit(id) {
      this.dialogVisible = true;
      this.fetchDataById(id);
    },

    // 根据 id 查询记录
    fetchDataById(id) {
      api.getById(id).then((response) => {
        this.liveCourse = response.data;
      });
    },

    // 根据 id 更新记录
    updateData() {
      api.updateById(this.liveCourse).then((response) => {
        if (response.code) {
          this.$message({
            type: "success",
            message: response.message,
          });
          this.dialogVisible = false;
          this.fetchData(this.page);
        }
      });
    },

    // 查询直播课程账号信息
    showAccount(row) {
      this.accountDialogVisible = true
      api.getLiveCourseAccount(row.id).then(response => {
        this.liveCourseAccount = response.data
        this.liveCourseAccount.courseId = row.courseId
      })
    },

    // ------------upload------------
    // 上传成功回调
    handleCoverSuccess(res) {
      this.liveCourse.cover = res.data;
    },

    // 上传校验
    beforeCoverUpload(file) {
      const isMin = file.size / 1024 / 1024 < 10;
      const isImg = file.type === "image/jpeg" || file.type === "image/png";

      if (!isImg) {
        this.$message.error("图片只能是 JPG 或 PNG 格式!");
      }
      if (!isMin) {
        this.$message.error("图片大小不能超过 10MB!");
      }
      return isImg && isMin;
    },

    // 错误处理
    handleCoverError() {
      this.$message.warning("图片上传失败");
    },
  },
};
</script>


<style scoped>
.cover-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  font-size: 28px;
  color: #8c939d;
  width: 450px;
  height: 200px;
  line-height: 200px;
  text-align: center;
}

.cover-uploader .avatar-uploader-icon:hover {
  border-color: #409eff;
}

.cover-uploader img {
  width: 450px;
  height: 200px;
  display: block;
}
</style>
