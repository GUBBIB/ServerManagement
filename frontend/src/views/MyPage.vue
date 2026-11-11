<template>
  <main>
    <h1>마이페이지</h1>

    <div v-if="user">
      <p>이름 {{ user.name }}</p>
      <p>이메일 {{ user.email }}</p>
      <p>전화번호 {{ user.phone }}</p>
      <p>역할 {{ user.roles.join(', ') }}</p>
    </div>
    <p v-else>로그인한 사용자 정보가 없습니다.</p>

    <button type="button" @click="goToDashboard">대시보드로 돌아가기</button>
  </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useAuthStore } from '@/stores/auth'

export default {
  name: 'MyPage',
  computed: {
    user() {
      return useAuthStore().user
    },
  },

  methods: {
    goToDashboard() {
      this.$router.push({ name: 'dashboard' })
    },
  },
}
</script>
