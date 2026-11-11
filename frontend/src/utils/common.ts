import axios from 'axios';

interface ApiErrorResponse {
  status: number
  code: string
  message: string
  path: string
  timestamp: string
}

export function error(error: unknown): void {
  const fallback = "요청을 처리하지 못했습니다.";

  if(axios.isAxiosError<ApiErrorResponse>(error)) {
    window.alert(error.response?.data?.message || fallback);
    return;
  }

  window.alert(fallback);
}
