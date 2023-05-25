<template>
  <div style="width: 50%">
    <div style="margin-bottom: 30px">新增借出纪录</div>
    <el-form :inline="true" :model="form":rules="rules" ref="ruleForm"  label-width="100px">

      <el-form-item label="器材码" prop="sportsNo">
        <el-select v-model="form.sportsNo" filterable placeholder="请选择" @change="selSports">
          <el-option
              v-for="item in sport_s"
              :key="item.id"
              :label="item.sportsNo"
              :value="item.sportsNo">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="器材名称" prop="sportsName">
        <el-input v-model="form.sportsName" disabled placeholder="请输入名称"></el-input>
      </el-form-item>

      <el-form-item label="器材数量" prop="nums">
        <el-input v-model="form.nums" disabled ></el-input>
      </el-form-item>

      <el-form-item label="用户号" prop="userNo">
        <el-select v-model="form.userNo" filterable placeholder="请选择" @change="selUser">
          <el-option
              v-for="item in users"
              :key="item.id"
              :label="item.username"
              :value="item.username">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="用户名称" prop="userName">
        <el-input disabled v-model="form.userName"></el-input>
      </el-form-item>

      <el-form-item label="用户联系方式" prop="userPhone">
        <el-input disabled v-model="form.userPhone" ></el-input>
      </el-form-item>

      <el-form-item label="借出的天数" prop="days">
        <el-input-number v-model = "form.days" :min="1" :max="30" laber="所需积分"></el-input-number>
      </el-form-item>

    </el-form>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>
  </div>
</template>




<script>
import request from "@/utils/request";
export default {
  name: "AddSports",
  data() {
    return{
      form:{days: 1},
      sport_s:[],
      users:[],
    }
  },
  created() {
    request.get('/sports/list').then(res => {
      this.sport_s = res.data
    })

    request.get('/user/list').then(res => {
      this.users = res.data.filter(v=> v.status)
    })
  },
  methods:{
    save(){
      request.post('/borrow/save', this.form).then(res =>{
        if(res.code  ==='200'){
          this.$notify.success('新增成功')
          this.form ={}
        } else{
          this.$notify.error(res.msg)
        }
      })
    },
    selSports(){
      const sports = this.sport_s.find(v =>v.sportsNo === this.form.sportsNo)
      request.get('/sports/' + sports.id).then(res => {
        this.$set(this.form,'sportsName',res.data.name)
        this.form.nums = res.data.nums
      })
    },
    selUser(){
      const user = this.users.find(v =>v.username === this.form.userNo)
      request.get('/user/' + user.id).then(res =>{
        this.$set(this.form,'userName',res.data.name)
        this.form.userPhone = res.data.phone
      })

    }

  }
}
</script>
