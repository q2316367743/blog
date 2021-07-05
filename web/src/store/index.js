import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		author: {
			avatar: "",
			name: "",
			description: "",
			gitee: "",
			other: [],
		},
		web_info: {
			article_count: 0,
			tag_count: 0,
			category_count: 0,
			run_time: "1",
			person_count: 0,
			access_count: 0,
			last_update: "",
		},
		notices: [],
		category: []
	},
	getters: {
		author: state => state.author,
		web_info: state => state.web_info,
		notices: state => state.notices,
		category: state => state.category
	},
	mutations: {
		SET_AUTHOR(state, author) {
			state.author = author;
		},
		SET_WEB_INFO(state, web_info) {
			state.web_info = web_info;
		},
		SET_NOTICES(state, notices) {
			state.notices = notices;
		},
		SET_CATEGORY(state, category) {
			state.category = category;
		}
	},
	actions: {}
});
