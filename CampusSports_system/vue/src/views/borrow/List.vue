<template>
  <div>
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入器材名称" v-model="params.sportsName"></el-input>
      <el-input style="width: 240px; margin-left:5px" placeholder="请输入器材码" v-model="params.sportsNo"></el-input>
      <el-input style="width: 240px; margin-left:5px" placeholder="请输入用户名称" v-model="params.userName"></el-input>

      <el-button style="margin-left: 5px" type="primary"@click="load">
        <i class="el-icon-search"></i>搜索</el-button>
      <el-button style="margin-left: 5px" type="warning"@click="reset"> <i class="el-icon-search"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="sportsName" label="器材名称"></el-table-column>
      <el-table-column prop="sportsNo" width="100" label="器材码"></el-table-column>
      <el-table-column prop="userNo" label="用户号"></el-table-column>
      <el-table-column prop="userName" label="用户名称"></el-table-column>
      <el-table-column prop="userPhone" label="用户联系方式"></el-table-column>
      <el-table-column prop="status" label="借出状态"></el-table-column>
      <el-table-column prop="createtime" label="借出时间"></el-table-column>
      <el-table-column prop="days" label="借出天数"></el-table-column>
      <el-table-column prop="returnDate" label="归还日期"></el-table-column>
      <el-table-column prop="note" label="过期提醒">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.note === '正常'">{{ scope.row.note }}</el-tag>
          <el-tag type="primary" v-if="scope.row.note === '即将到期'">{{ scope.row.note }}</el-tag>
          <el-tag type="warning" v-if="scope.row.note === '已到期'">{{ scope.row.note }}</el-tag>
          <el-tag type="danger" v-if="scope.row.note === '已过期'">{{ scope.row.note }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label = "管理">
        <template v-slot="scope">
          <el-button type="primary" @click="returnSports(scope.row)" v-if="scope.row.status === '已借出' ">归还</el-button>
        </template>
        </el-table-column>
<!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->


    <el-table-column label="操作" width="275">
      <template v-slot="scope">
        <!-- scope.row 就是当前行数据-->
<!--        <el-button type="primary" @click="$router.push('/editBorrow?id='+scope.row.id)">编辑</el-button>-->
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
  name: 'BorrowList',
  data() {
    return {
      tableData:[],
      total:0,
      params:{
        pageNum: 1,
        pageSize: 8,
        sportName:'',
        sportsNo:'',
        userName:''

      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //fetch('http://localhost:9090/borrow/list').then(res =>res.json()).then(res =>{
      // console.log(res)
      //this.tableData = res
      //})
      request.get('/borrow/page', {
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
        sportsName: '',
        sportsNo:'',
        userName:''
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

      request.delete("/borrow/delete/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    returnSports(row){
      request.post("/borrow/saveRetur", row).then(res => {
        if (res.code === '200') {
          this.$notify.success('归还成功')
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