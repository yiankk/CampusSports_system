<template>
  <div>
    <!--搜索表单-->
    <div style="margin-bottom: 20px">
      <el-input style="width: 240px" placeholder="请输入器材名称" v-model="params.name"></el-input>
      <el-input style="width: 240px; margin-left:5px" placeholder="请输入器材器材码" v-model="params.sportsNo"></el-input>

      <el-button style="margin-left: 5px" type="primary"@click="load">
        <i class="el-icon-search"></i>搜索</el-button>
      <el-button style="margin-left: 5px" type="warning"@click="reset"> <i class="el-icon-search"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe row-key="id" default-expand-all>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="description" width="200" label="描述"></el-table-column>
      <el-table-column prop="category" label="分类"></el-table-column>
      <el-table-column prop="sportsNo" label="器材码"></el-table-column>
      <el-table-column prop="nums" label="数量"></el-table-column>
      <el-table-column prop="cover" label="封面">
        <template v-slot="scope">
          <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>


    <el-table-column label="操作" width="275">
      <template v-slot="scope">
        <!-- scope.rou 就是当前行数据-->
        <el-button type="primary" @click="$router.push('/editSports?id='+scope.row.id)">编辑</el-button>
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
  name: 'SportsList',
  data() {
    return {
      tableData:[],
      total:0,
      params:{
        pageNum: 1,
        pageSize: 4,
        name:'',
        sportsNo:''

      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //fetch('http://localhost:9090/sports/list').then(res =>res.json()).then(res =>{
      // console.log(res)
      //this.tableData = res
      //})
      request.get('/sports/page', {
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
        pageSize: 4,
        name: '',
        sportsNo:''
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

      request.delete("/sports/delete/" + id).then(res => {
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