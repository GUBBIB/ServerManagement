import type { App } from "vue";
import { error as error } from "@/utils/common";

export default {
  install(app: App){
    app.config.globalProperties.$error = error;
  }
}
