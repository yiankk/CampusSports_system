<template>
  <div>
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入名称" v-model="params.name"></el-input>
      <el-input style="width: 240px; margin-left: 5px"placeholder="请输入手机号" v-model="params.phone"></el-input>
      <el-button style="margin-left: 5px" type="primary"@click="load">
        <i class="el-icon-search"></i>搜索</el-button>
      <el-button style="margin-left: 5px" type="warning"@click="reset"> <i class="el-icon-search"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="username" label="用户号"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>
      <el-table-column prop="grade" label="班级"></el-table-column>
      <el-table-column label="状态" width="150">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.status"
              @change="changeStatus(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>


    <el-table-column label="操作">
      <template v-slot="scope">
        <!-- scope.rou 就是当前行数据-->
        <el-button type="primary" @click="$router.push('/edituser?id='+scope.row.id)">编辑</el-button>
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
        name:'',
        phone:''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    changeStatus(row) {
      request.put('/user/update', row).then(res => {
        if (res.code === '200') {
          this.$notify.success('操作成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    load() {
      //fetch('http://localhost:9090/user/list').then(res =>res.json()).then(res =>{
      // console.log(res)
      //this.tableData = res
      //})
      request.get('/user/page', {
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
        name: '',
        phone: ''
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

      request.delete("/user/delete/" + id).then(res => {
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