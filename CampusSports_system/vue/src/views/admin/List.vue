<template>
  <div>
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入会员号" v-model="params.username"></el-input>
      <el-input style="width: 240px; margin-left: 5px"placeholder="请输入联系方式" v-model="params.phone"></el-input>
      <el-input style="width: 240px; margin-left: 5px"placeholder="请输入邮箱" v-model="params.email"></el-input>
      <el-button style="margin-left: 5px" type="primary"@click="load">
        <i class="el-icon-search"></i>搜索</el-button>
      <el-button style="margin-left: 5px" type="warning"@click="reset"> <i class="el-icon-search"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="username" label="会员号"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>


    <el-table-column label="操作">
      <template v-slot="scope">
        <!-- scope.rou 就是当前行数据-->
        <el-button type="primary" @click="$router.push('/editAdmin?id='+scope.row.id)">编辑</el-button>
        <el-popconfirm

            title="您确定删除吗？"
            @confirm="del(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除</el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
    </el-table>

    <!--分页-->
    <div style="margin-top: 50px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handlelCurrentChange"
          :total="total">
      </el-pagination>
    </div>


  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'User',
  data() {
    return {
      tableData:[],
      total:0,
      params:{
        pageNum: 1,
        pageSize: 8,
        username:'',
        phone:'',
        email:''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //fetch('http://localhost:9090/admin/list').then(res =>res.json()).then(res =>{
      // console.log(res)
      //this.tableData = res
      //})
      request.get('/admin/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }

      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 8,
        username: '',
        phone: '',
        email: ''
      }
      this.load()
    },
    handlelCurrentChange(pageNum) {
      //点击分页触发按钮
      //console.log(pageNum)
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {

      request.delete("/admin/delete/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>