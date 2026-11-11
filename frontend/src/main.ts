import {createApp} from 'vue';
import {createPinia} from 'pinia';
import axios from 'axios';
import commonPlugin from '@/plugins/common'

import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.config.globalProperties.axios = axios;
app.use(commonPlugin);

app.mount('#app');
