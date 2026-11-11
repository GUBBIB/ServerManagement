import { defineStore } from 'pinia'

export interface AuthUser {
  user_id: number
  email: string
  name: string
  phone: string
  roles: string[]
}

interface LoginSession {
  status: string
  accessToken: string
}

interface AuthState {
  user: AuthUser | null
  session: LoginSession | null
}

export const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    user: null,
    session: null,
  }),

  actions: {
    setSession(session: LoginSession) {
      this.user = null
      this.session = session
    },

    setUser(user: AuthUser) {
      this.user = user
    },

    clearUser() {
      this.user = null
      this.session = null
    },
  },
})
