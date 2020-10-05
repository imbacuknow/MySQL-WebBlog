<template>
     <div class="home" align="center">
          <img alt="Vue logo" src="../assets/logo.png" />
          <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
          ManageUser
     </div>
</template>

<script>
import api from "../api";
export default {
     mounted() {
          // localStorage.removeItem("user");
          this.getUser();
     },
     methods: {
          getUser() {
               api.get("webblog/userid=" + 5).then((response) => {
                    let user = response.data;
                    localStorage.setItem("user", JSON.stringify(user));
               });
          },
     },
};
</script>