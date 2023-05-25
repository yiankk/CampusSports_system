<template>
  <div style="width: 50%">
    <div style="margin-bottom: 30px">编辑器材</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入名称"></el-input>
      </el-form-item>

      <el-form-item label="描述" prop="description">
        <el-input style="width: 300px" type = "textarea" v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>

      <el-form-item label="分类" prop="category">
        <el-cascader
            :props="{ value:'name', label:'name'}"
            v-model="form.categories"
            :options="categories"
            >

        </el-cascader>
      </el-form-item>

      <el-form-item label="器材码" prop="sportsNo">
        <el-input v-model="form.sportsNo" placeholder="请输入器材码"></el-input>
      </el-form-item>

      <el-form-item label="数量" prop="nums">
        <el-input v-model="form.nums" placeholder="请输入数量"></el-input>
      </el-form-item>

      <el-form-item label="封面" prop="cover">
        <el-input v-model="form.cover" placeholder="请输入封面"></el-input>
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
  name: "EditSports",
  data() {
    return{
      form:{},
      categories:[],
    }
  },
  created() {
    request.get('/category/tree').then(res => {
      this.categories = res.data
    })
    const id = this.$route.query.id
    request.get("/sports/"+id).then(res =>{
      this.form =res.data
      if(this.form.category){
        this.form.categories = this.form.category.split(' > ')
      }
    })
  },
  methods:{
    save(){
      request.put('/sports/update', this.form).then(res =>{
        if(res.code  ==='200'){
          this.$notify.success('更新成功')
          this.$router.push("/sportsList")
        } else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
