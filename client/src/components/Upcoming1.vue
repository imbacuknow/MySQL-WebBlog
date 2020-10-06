<template>
     <div align="center">
          <div>
               <v-col v-for="(item, i) in list_posts" :key="i" cols="12">
                    <v-card
                         class="rounded-lg"
                         width="780"
                         height="230"
                         color="#333333"
                         dark
                    >
                         <div class="d-flex flex-no-wrap justify-space-between">
                              <div>
                                   <v-card-title
                                        align="left"
                                        class="headline deep-orange lighten-2 rounded-lg black--text"
                                        style="height: 25%"
                                        v-text="item.title.slice(0, 50)"
                                        @click="ReadMore(item.id)"
                                   />

                                   <v-card-text
                                        align="left"
                                        class="pa-2 my-3 px-4"
                                        style="height: 47%"
                                        >{{
                                             item.story.slice(0, 440)
                                        }}</v-card-text
                                   >

                                   <div
                                        align="left"
                                        style="height: 19%"
                                        class="pa-2 deep-orange lighten-2 lighten-2 black--text"
                                   >
                                        Tag:
                                   </div>
                              </div>
                         </div>
                    </v-card>
               </v-col>
          </div>
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
