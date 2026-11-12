import {createRouter, createWebHistory} from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import Login from '@/views/Login.vue';
import Dashboard from '@/views/Dashboard.vue';
import MyPage from '@/views/MyPage.vue';
import AccountStatus from '@/views/AccountStatus.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/login', name: 'login', component: Login },
    
    {
      path: '/',
      meta: { requiresLogin: true },

      children: [
        { path: '', redirect: { name: 'login' } },
        { path: '/account-status', name: 'account-status', component: AccountStatus },
        { path: '/dashboard', name: 'dashboard', component: Dashboard },
        { path: '/my-page', name: 'my-page', component: MyPage },
      ],
    },
  ],
})

router.beforeEach((to) => {
  if (!to.meta.requiresLogin) {
    return
  }

  const { session } = useAuthStore()

  if (!session) {
    return { name: 'login' }
  }

  if (session.status !== 'ACTIVE' && to.name !== 'account-status') {
    return { name: 'account-status' }
  }
})

export default router
