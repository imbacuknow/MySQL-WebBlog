<template>
     <div align="center">
          <v-card
               class="rounded-lg amber lighten-4"
               width="800"
               height="auto"
               max-height="2000"
          >
               <v-col cols="12" sm="8" md="10">
                    <v-card-title>{{ set_content.title }}</v-card-title>
               </v-col>

               <v-col align="left" class="" cols="12" sm="8" md="10">
                    <v-card-text>{{ set_content.story }}</v-card-text>
               </v-col>

               <h4 class="ml-6 font-weight-regular" align="center"> Tag: </h4>
               <template v-for="(item) in tags">
                    <v-chip class="ma-1" :key="item.id">{{ item.tagName }} </v-chip>
               </template>

               <v-col align="right">
                    <v-btn
                         class="ma-4"
                         raised
                         @click="goToHome"
                         x-large
                         dark
                         color="brown darken-3"
                         >Back</v-btn
                    >
               </v-col>
          </v-card>
          <br />

          <v-card width="800" class="grey lighten-5">
               <!--  -->
               <v-card-text class="blue-grey darken-1 white--text">
                    <h2>Comment Zone</h2>
               </v-card-text>
               <!-- list comment -->
               <v-list
                    three-line
                    class="grey lighten-5 black--text"
                    align="left"
               >
                    <v-list-item-group
                         v-model="selected"
                         multiple
                         class="grey lighten-5 mx-2"
                    >
                         <template v-for="(item, index) in comments">
                              <v-list-item :key="item.id">
                                   <v-list-item-content>
                                        <v-list-item-title>
                                             <h4 align="left">
                                                  {{ item.userName }}
                                             </h4>
                                             <h5 class="font-weight-thin" align="right">
                                                  {{ item.commentDate }}
                                             </h5>
                                        </v-list-item-title>
                                        <v-list-item-subtitle
                                             class="black--text"
                                             v-text="item.msg"
                                        />
                                   </v-list-item-content>
                              </v-list-item>
                              <v-divider :key="index" :inset="false" />
                         </template>
                    </v-list-item-group>
               </v-list>
               <!--  -->
               <v-form class="mx-2">
                    <v-container>
                         <v-col class="grey lighten-3">
                              <v-row>
                                   <v-text-field
                                        class="mx-2"
                                        v-model="cmt"
                                        label="Comment"
                                   />
                              </v-row>
                              <v-row justify="center">
                                   <v-btn
                                        class="mx-2"
                                        @click="onClickCM"
                                        raised
                                        dark
                                        width="720"
                                        color="green lighten-2"
                                        >comment</v-btn
                                   >
                              </v-row>
                         </v-col>
                    </v-container>
               </v-form>
          </v-card>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     name: "UserContent",
     props: ["content_id"],
     data: () => ({
          set_content: [],
          comments: [],
          set_com:[],
          cmt: "",
          user: [],
          tags: []
     }),
     mounted() {
          this.getContent();
          this.user = JSON.parse(localStorage.getItem("user"));
     },
     methods: {
          goToHome() {
               this.$router.push({ name: "Home" });
          },
           getContent() {
               api.get("/webblog/post/content/" + this.content_id)
               .then((response) => {
                    this.set_content = response.data;
                    // console.log(JSON.parse(JSON.stringify(response.data)));
                    this.getComments();
                    this.getTags();
               }).catch((e) => {
                    console.log("Error in getForDisContent():" + e);
               });
          },
          getComments() {
               api.get("/webblog/comment/postId/" + this.content_id)
               .then((res) => {
                    this.comments = res.data;
                    // console.log(JSON.parse(JSON.stringify(res.data)));
               }).catch((e) => {
                    console.log("error in getComments(): "+ e);
               });
          },
          getTags() {
               api.get("webblog/tag/postId=" + this.content_id)
               .then((res) => {
                    this.tags = res.data;
                    console.log(JSON.parse(JSON.stringify(res.data)));
               }).catch((e) => {
                    console.log("error in getTags(): "+ e);
               });
          },
          onClickCM() {
               if (!this.cmt) {
                    alert("กรุณากรอกข้อความ comment");
               } else {
                    this.submitComment();
               }
          },
          submitComment() {
               let databox = {
                    msg: this.cmt,
                    user: {
                         id: this.user.id
                    },
                    post: {
                         id: this.content_id
                    }
               };
               api.post("/webblog/comment/add_new", databox)
                    .then(() => {
                         this.cmt = null;
                         this.getComments();
                    })
                    .catch((e) => {
                         console.log("Error in submitComment():" + e);
                    });
          },
     },
}
</script>