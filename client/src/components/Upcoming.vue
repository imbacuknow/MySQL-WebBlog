<template class="rounded-lg justify-center align-center">
     <div id="app">
          <v-app id="inspire">
               <v-card max-width="780" max-height="auto" class="mx-auto">
                    <v-container>
                         <v-row dense>
                              <v-col
                                   v-for="(item, i) in list_posts"
                                   :key="i"
                                   cols="12"
                              >
                                   <v-card color="#7d0c4a" dark>
                                        <div
                                             class="d-flex flex-no-wrap justify-space-between"
                                        >
                                             <div>
                                                  <v-card-title
                                                       class="headline"
                                                       v-text="item.title"
                                                  ></v-card-title>

                                                  <v-card-subtitle
                                                       v-text="item.story"
                                                  ></v-card-subtitle>
                                             </div>
                                        </div>
                                   </v-card>
                              </v-col>
                         </v-row>
                    </v-container>
               </v-card>
          </v-app>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     name: "Upcoming",
     data: () => ({
          list_posts: [],
          set_content: [],
          set_content_id: undefined,
     }),
     mounted() {
          this.getPost();
     },
     methods: {
          getPost() {
               api.get("/webblog/post/upcoming")
                    .then((response) => {
                         this.list_posts = response.data;
                         console.log(JSON.parse(JSON.stringify(response.data)));
                    })
                    .catch((e) => {
                         console.log("Error in getPost() : " + e);
                    });
          },
          ReadMore(value) {
               this.$router.push({
                    name: "Content",
                    params: { content_id: value },
               });
          },
     },
};
</script>