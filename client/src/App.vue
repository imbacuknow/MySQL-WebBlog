<template>
     <v-app id="inspire">
          <div>
               <v-toolbar app class="light-blue" height="180px">
                    <v-col>
                         <v-row class="justify-center align-center">
                              <v-spacer></v-spacer>
                              <v-col cols="auto">
                                   <img
                                        @click="gotoHome"
                                        justify="center"
                                        src="./assets/IM_logo.png"
                                        alt="IM_logo"
                                        width="180"
                                        height="80"
                                   />
                              </v-col>
                              <v-spacer></v-spacer>
                         </v-row>

                         <v-row class="pt-ma-2" color="blue">
                              <v-tabs
                                   class="blue"
                                   centered
                                   color="blue-grey darken-4"
                              >
                                   <v-tab to="/">Home</v-tab>
                                   <v-tab to="/Upcoming">Upcoming</v-tab>
                                   <v-tab to="/Post">Post</v-tab>
                                   <v-tab to="/ManageUser">Manage User</v-tab>
                                   <v-tab to="/Login">Login</v-tab>
                                   <v-tabs-slider color="pink"></v-tabs-slider>
                              </v-tabs>
                         </v-row>
                    </v-col>
               </v-toolbar>
          </div>

          <template>
               <div id="app">
                    <v-container align="center">
                         <router-view></router-view>
                    </v-container>
               </div>
          </template>
     </v-app>
</template>

<script>
import api from "./api.js";
export default {
     data: () => ({
          user: {},
          ThereIsSIgnIn: false,
     }),
     mounted() {
          this.user = JSON.parse(localStorage.getItem("user"));
          if (this.user) {
               this.ThereIsSIgnIn = true;
          } else {
               this.ThereIsSIgnIn = false;
          }
     },
     methods: {
          gotoHome() {
               window.location.href = "/";
          },
          getUser() {
               api.get("webblog/userid=" + 5).then((response) => {
                    let user = response.data;
                    localStorage.setItem("user", JSON.stringify(user));
               });
          },
     },
};
</script>