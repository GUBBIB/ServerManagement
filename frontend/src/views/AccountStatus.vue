<template>
  <main>
    <h1>계정 상태</h1>

    <p v-if="status === 'PENDING'">가입 신청이 승인 대기 중입니다.</p>

    <template v-else-if="status === 'REJECTED'">
      <p>가입 신청이 반려되었습니다.</p>
      <p v-if="details?.rejectionReason">
        반려 사유 {{ details.rejectionReason }}
      </p>
    </template>

    <template v-else-if="status === 'WITHDRAWN'">
      <p>탈퇴한 계정입니다.</p>
      <p v-if="details?.withdrawnAt">
        탈퇴 일시 {{ details.withdrawnAt }}
      </p>
    </template>

    <p v-else>현재 계정 상태 {{ status }}</p>
  </main>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useAuthStore } from '@/stores/auth'

interface AccountStatusDetails {
  status: string
  rejectionReason?: string | null
  withdrawnAt?: string | null
}

export default defineComponent({
  name: 'AccountStatus',

  data(): { details: AccountStatusDetails | null } {
    return {
      details: null,
    }
  },

  computed: {
    status() {
      return this.details?.status ?? useAuthStore().session?.status
    },
  },

  created() {
    this.getAccountStatus()
  },

  methods: {
    getAccountStatus() {
      const { session } = useAuthStore()
      if (!session) return

      this.axios
        .get<{ data: AccountStatusDetails }>('/api/v1/auth/signup/status', {
          headers: { Authorization: `Bearer ${session.accessToken}` },
        })
        .then((res) => {
          this.details = res.data.data
        })
        .catch((err) => {
          this.$error(err)
        })
    },
  },
})
</script>
