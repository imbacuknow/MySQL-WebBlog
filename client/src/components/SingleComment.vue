<template>
     <div align="center">
          <v-card-text class="blue-grey darken-1 white--text">
               <h2>Comment Zone</h2>
          </v-card-text>
          <!-- list comment -->
          <v-list three-line class="grey lighten-5 black--text" align="left">
               <v-list-item-group
                    v-model="selected"
                    multiple
                    class="grey lighten-5 mx-2"
               >
                    <template v-for="(item, index) in passComments">
                         <v-list-item :key="item.title">
                              <v-list-item-content>
                                   <v-list-item-title>
                                        <h4>{{ item.commented.username }}</h4>
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
     </div>
</template>

<script>
export default {
     name: "SingleComment",
     data: () => ({
          cmt: "",
     }),
     props: ["passComments"],
};
</script>


