<template>
  <main>
    <h1>대시보드</h1>
    <p v-if="user">{{ user.name }}님, 환영합니다.</p>
    <button type="button" @click="goToMyPage">마이페이지</button>
  </main>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {useAuthStore} from '@/stores/auth'

export default {
  computed: {
    user() {
      return useAuthStore().user
    },
  },

  methods: {
    goToMyPage() {
      this.$router.push({
        name: 'my-page'
      })
    },
  },
}
</script>
