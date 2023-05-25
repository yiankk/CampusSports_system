import Vue from 'vue'
import App from './App.vue'
import router from './router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/global.css'  //@表示src目录

Vue.config.productionTip = false
Vue.use(ElementUI, {size: 'small'});  //medium small mini

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
