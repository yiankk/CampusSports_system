<template>
  <div style="width: 50%">
    <div style="margin-bottom: 30px">新增管理员</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="会员号" prop="username">
        <el-input v-model="form.username" placeholder="请输入会员号"></el-input>
      </el-form-item>

      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="address">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
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
  name: "AddAdmin",
  data() {
    return{
      form:{}
    }
  },
  methods:{
    save(){
      request.post('/admin/save', this.form).then(res =>{
        if(res.code  ==='200'){
          this.$notify.success('新增成功')
          this.form ={}
        } else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
