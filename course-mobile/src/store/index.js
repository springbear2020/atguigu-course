import Vue from "vue";
import Vuex from "vuex";
import {BASE_REQUEST_URL} from "../utils/request";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        baseURL: BASE_REQUEST_URL
    },
    mutations: {},
    actions: {},
    modules: {},
});
