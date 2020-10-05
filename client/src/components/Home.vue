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
                                        justify="center"
                                        align="left"
                                        style="height: 25%"
                                        class="headline green accent-3 rounded-lg black--text"
                                        @click="ReadMore(item.id)"
                                        >{{
                                             item.title.slice(0, 50)
                                        }}</v-card-title
                                   >

                                   <v-card-text
                                        align="left"
                                        class="pa-2 my-3 px-4"
                                        v-text="item.story.slice(0, 440)"
                                   />

                                   <div
                                        align="left"
                                        style="height: 19%"
                                        class="pa-2 green accent-3 black--text"
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
     name: "Home",
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
               api.get("/webblog/post/home")
                    .then((response) => {
                         this.list_posts = response.data;
                         console.log(JSON.parse(JSON.stringify(response.data)));
                    })
                    .catch((e) => {
                         console.log("Error in getPost() :" + e);
                    });
          },
          ReadMore(value) {
               this.$router.push({
                    name: "UserContent",
                    params: { content_id: value },
               });
          },
     },
};
</script>