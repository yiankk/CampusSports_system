<template>
  <div>
    <!-- 头部区域 -->
    <div style="height: 60px; line-height:60px; background-color:white; margin-bottom:2px">
      <img src="@/assets/logo.png" alt="" style="width:40px; position:relative; top:10px">
      <span style="margin-left:10px; font-size:24px">校园体育器材管理系统</span>
    </div>

    <!-------侧边栏和主体------->
    <div style="display: flex">
      <!-- 侧边栏 -->
      <div style="width: 200px;min-height:calc(100vh - 62px); overflow:hidden;margin-right:2px;background-color: white">

        <el-menu :default-active="$route.path" :default-openeds="['user','admin','category','sports','borrow','retur']" router class="el-menu-demo">
          <el-menu-item index="/">
            <i class="el-icon-eleme"></i>
            <span>首页</span>
          </el-menu-item>
          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-user"></i>
              用户管理
            </template>
            <el-menu-item index="/adduser">用户添加</el-menu-item>
            <el-menu-item index="/userList">用户列表</el-menu-item>
          </el-submenu>


          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-user"></i>
              管理员管理
            </template>
            <el-menu-item index="/addAdmin">管理员添加</el-menu-item>
            <el-menu-item index="/adminList">管理员列表</el-menu-item>
          </el-submenu>

          <el-submenu index="category">
            <template slot="title">
              <i class="el-icon-user"></i>
              器材分类管理
            </template>
            <el-menu-item index="/addCategory">器材分类添加</el-menu-item>
            <el-menu-item index="/categoryList">器材分类列表</el-menu-item>
          </el-submenu>

          <el-submenu index="sports">
            <template slot="title">
              <i class="el-icon-user"></i>
              器材管理
            </template>
            <el-menu-item index="/addSports">器材添加</el-menu-item>
            <el-menu-item index="/sportsList">器材列表</el-menu-item>
          </el-submenu>

          <el-submenu index="borrow">
            <template slot="title">
              <i class="el-icon-user"></i>
              借出管理
            </template>
            <el-menu-item index="/addBorrow">借出添加</el-menu-item>
            <el-menu-item index="/borrowList">借出列表</el-menu-item>
          </el-submenu>

          <el-submenu index="retur">
            <template slot="title">
              <i class="el-icon-user"></i>
              归还管理
            </template>
            <el-menu-item index="/returList">归还列表</el-menu-item>
          </el-submenu>

        </el-menu>
      </div>

      <!-- 主体数据 -->
      <!----width:0可以限制容器宽度，不被子元素无限撑开------>
      <div style="flex: 1;width:0;background-color: white">

        <router-view />      <!-- 主体 -->
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: 'Layout.vue'
}

</script>
<style scoped>
</style>