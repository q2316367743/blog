import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        username: '',
        nickname: '',
    },
    getters: {
        username: state => state.username,
        nickname: state => state.nickname,
        isLogin: state => {
            if (state.username.length == 0) {
                state.username = '';
                state.nickname = '';
                return false
            }
            return true;
        }
    },
    mutations: {
        SET_USERNAME(state, username) {
            state.username = username
        },
        SET_NICKNAME(state, nickname) {
            state.nickname = nickname
        },
        LOGOUT(state) {
            state.username = '';
            state.nickname = '';
        },
        LOGIN(state, user) {
            state.username = user.username;
            state.nickname = user.nickname;
        }
    },
    actions: {}
});