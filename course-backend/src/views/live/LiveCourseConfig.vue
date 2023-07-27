<template>
  <div class="app-container">
    <el-form label-width="120px" size="small">
      <div
        style="
          background-color: #e0e0e0;
          width: 100%;
          padding: 1px 10px;
          margin: 10px 0;
        "
      >
        <h3>功能设置&nbsp;&nbsp;&nbsp;</h3>
      </div>
      <el-form-item label="界面模式">
        <el-radio-group v-model="liveCourseConfigVo.pageViewMode">
          <el-radio :label="1">全屏模式</el-radio>
          <el-radio :label="0">二分屏</el-radio>
          <el-radio :label="2">课件模式</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="观看人数开关">
        <el-radio-group v-model="liveCourseConfigVo.numberEnable">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="商城开关:">
        <el-radio-group v-model="liveCourseConfigVo.storeEnable">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>

      <div
        style="
          background-color: #e0e0e0;
          width: 100%;
          padding: 1px 10px;
          margin: 10px 0;
        "
      >
        <h3>
          商品列表&nbsp;&nbsp;&nbsp;
          <el-button type="primary" size="mini" @click="addCourse()">添加</el-button>
        </h3>
      </div>
      <el-table
        v-loading="listLoading"
        :data="liveCourseConfigVo.liveCourseGoodsList"
        stripe
        border
        style="width: 100%; margin-top: 10px"
      >
        <el-table-column label="序号" width="70" align="center">
          <template slot-scope="scope">
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="商品图片" width="120" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.img" width="80px" />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="名称" width="100" />
        <el-table-column prop="price" label="价格" width="100" />
        <el-table-column prop="originalPrice" label="原价" />
        <el-table-column label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              @click="removeCourseById(scope.$index)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-dialog title="添加课程" :visible.sync="dialogVisible" width="50%">
        <el-form
          :inline="true"
          label-width="150px"
          size="small"
          style="padding-right: 40px"
        >
          <el-table
            v-loading="listLoading"
            :data="courseList"
            stripe
            border
            style="width: 100%; margin-top: 10px"
            @selection-change="handleSelectionChange"
          >
            <el-table-column type="selection" width="55" />
            <el-table-column label="序号" width="70" align="center">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column label="分类">
              <template slot-scope="scope">
                {{ scope.row.parentSubjectName }} >
                {{ scope.row.subjectName }}
              </template>
            </el-table-column>
            <el-table-column prop="title" label="课程名称" width="150" />
            <el-table-column prop="lessonNum" label="课时" width="100" />
            <el-table-column prop="teacherName" label="讲师" />
          </el-table>
          <el-form-item style="margin-top: 10px">
            <el-button type="" @click="dialogVisible = false">取消</el-button>
            <el-button type="" @click="selectCourse()">保存</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <br /><br />
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
        <el-button @click="back">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from "@/api/live/live";
import courseApi from "@/api/vod/course";

const defaultForm = {
  id: "",
  liveCourseId: "",
  pageViewMode: 1,
  numberEnable: 1,
  storeEnable: 1,
  storeType: 1,
  liveCourseGoodsList: [],
};

export default {
  data() {
    return {
      listLoading: false,
      liveCourseConfigVo: defaultForm,
      saveBtnDisabled: false,
      dialogVisible: false,
      courseList: [],
      multipleSelection: [],
    };
  },

  watch: {
    $route() {
      this.init();
    },
  },

  created() {
    this.init();
  },

  methods: {
    // 初始化课程配置信息和课程列表
    init() {
      this.liveCourseConfigVo.liveCourseId = this.$route.params.id;
      this.fetchDataById(this.liveCourseConfigVo.liveCourseId);
      this.fetchCourseList();
    },

    // 返回直播课程列表页
    back() {
      this.$router.push({ path: "/live/list" });
    },

    // 查询课程配置信息
    fetchDataById(id) {
      api.getCourseConfig(id).then((response) => {
        if (null !== response.data.id) {
          this.liveCourseConfigVo = response.data;
        }
        this.listLoading = false;
      });
    },

    // 查询所有课程信息
    fetchCourseList() {
      courseApi.getAllCourse().then((response) => {
        this.courseList = response.data;
      });
    },

    // 打开添加课程对话框
    addCourse() {
      this.dialogVisible = true;
    },

    // 多选课程
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },

    // 选择课程
    selectCourse() {
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: "warning",
          message: "请选择对应课程!",
        });
        return;
      }
      var list = [];
      this.multipleSelection.forEach((item) => {
        var obj = {
          liveCourseId: this.liveCourseConfigVo.liveCourseId,
          goodsId: item.id,
          name: item.title,
          img: item.cover,
          price: item.price,
          originalPrice: item.price,
          tab: "1",
          // TODO 替换课程链接
          url: "http://glkt-api.atguigu.cn/#/courseInfo/" + item.id,
          putaway: "1",
          pay: "1",
          qrcode: "",
        };
        list.push(obj);
      });
      this.liveCourseConfigVo.liveCourseGoodsList = list;
      this.dialogVisible = false;
    },

    // 列表移除课程
    removeCourseById(index) {
      this.liveCourseConfigVo.liveCourseGoodsList.splice(index, 1);
    },

    saveOrUpdate() {
      api.updateConfig(this.liveCourseConfigVo).then((response) => {
        this.$message({
          type: "success",
          message: response.message,
        });
        this.$router.push({ path: "/live/list" });
      });
    },
  },
};
</script>


<style scoped>
.littleMarginTop {
  margin-top: 10px;
}

.paramInput {
  width: 250px;
}

.paramInputLabel {
  display: inline-block;
  width: 100px;
  text-align: right;
  padding-right: 10px;
}

.cardBg {
  background: #f8f9fc;
}
</style>