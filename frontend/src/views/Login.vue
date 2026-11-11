<template>
  <main>
    <h1>로그인</h1>

    <form @submit.prevent="login">
      <input
        v-model="email"
        placeholder="이메일"
        autocomplete="username"
        required
      />
      <input
        v-model="password"
        type="password"
        placeholder="비밀번호"
        autocomplete="current-password"
        required
      />
      <button type="submit">로그인</button>
    </form>

    <p v-if="errorMessage">{{ errorMessage }}</p>
  </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useAuthStore, type AuthUser} from '@/stores/auth'

interface LoginResponse {
  message: string
  data: {
    status: string
    message: string
    accessToken?: string | null
    expiresInMillis: number
  }
}

export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: '',
      errorMessage: '',
    }
  },

  methods: {
    login() {
      this.errorMessage = ''

      const cond = {
        email: this.email,
        password: this.password,
      }

      this.axios
        .post<LoginResponse>('/api/v1/auth/login', cond)
        .then((res) => {
          const { status, message, accessToken } = res.data.data

          if (!accessToken) {
            this.errorMessage = message || '로그인 정보를 받지 못했습니다.'
            return
          }

          const auth = useAuthStore()
          auth.setSession({ status, accessToken })

          if (status !== 'ACTIVE') {
            return this.$router.push({ name: 'account-status' })
          }

          return this.axios
            .get<{ data: AuthUser }>('/api/v1/user/me', {
              headers: { Authorization: `Bearer ${accessToken}` },
            })
            .then((res) => {
              auth.setUser(res.data.data)
              return this.$router.push({ name: 'dashboard' })
            })
        })
        .catch((err) => {
          this.$error(err)
        })
    },
  },
}
</script>
