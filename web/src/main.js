import Vue from 'vue'
import App from './App.vue'
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
import router from './router'
import store from './store'

Vue.config.productionTip = false

Vue.use(ElementUI);

// 设置图片目录

Vue.prototype.$image = process.env.NODE_ENV === "development" ? "localhost" : "esion.xyz/assets/image/blog"

new Vue({
    router,
	store,
    render: h => h(App),
}).$mount('#app')