<template>
     <div align="center">
          <v-row align="center" justify="center">
               <v-col cols="12" sm="8" md="6">
                    <v-alert type="success" dismissible v-model="alertSuccess"
                         >บันทึกข้อมูลสำเร็จ</v-alert
                    >
                    <v-alert type="error" dismissible v-model="alertFailed"
                         >{{ alertmsg }}!</v-alert
                    >
               </v-col>
          </v-row>
          <v-card
               class="justify-start ma-3 pb-2 rounded-lg red lighten-3"
               width="750"
               height="auto"
               max-height="2000"
          >
               <br />
               <v-col cols="12" sm="8" md="10">
                    <v-text-field
                         v-model="ins_title"
                         label="Title"
                         width="400"
                         solo
                    />
               </v-col>

               <!-- <v-col cols="12" sm="8" md="10">
            <Tag @createNewTag="receiveTag" />
         </v-col> -->
               <!-- tag   -->
               <v-col cols="12" sm="8" md="10">
                    <v-combobox
                         v-model="model"
                         :filter="filter"
                         :hide-no-data="!search"
                         :items="items"
                         :search-input.sync="search"
                         hide-selected
                         label="Search for an option"
                         multiple
                         small-chips
                         solo
                    >
                         <template v-slot:no-data>
                              <v-list-item>
                                   <span class="subheading">Create</span>
                                   <v-chip
                                        :tagColor="`${
                                             colors[nonce - 1]
                                        } lighten-3`"
                                        label
                                        small
                                   >
                                        {{ search }}
                                   </v-chip>
                              </v-list-item>
                         </template>
                         <template
                              v-slot:selection="{
                                   attrs,
                                   item,
                                   parent,
                                   selected,
                              }"
                         >
                              <v-chip
                                   v-if="item === Object(item)"
                                   v-bind="attrs"
                                   :color="`${item.tagColor} lighten-3`"
                                   :input-value="selected"
                                   label
                                   small
                              >
                                   <span class="pr-2">{{ item.tagName }}</span>
                                   <v-icon
                                        small
                                        @click="parent.selectItem(item)"
                                        >mdi-close</v-icon
                                   >
                              </v-chip>
                         </template>
                         <template v-slot:item="{ index, item }">
                              <v-text-field
                                   v-if="editing === item"
                                   v-model="editing.tagName"
                                   autofocus
                                   flat
                                   background-color="transparent"
                                   hide-details
                                   solo
                                   @keyup.enter="edit(index, item)"
                              ></v-text-field>
                              <v-chip
                                   v-else
                                   :color="`${item.tagColor} lighten-3`"
                                   dark
                                   label
                                   small
                                   >{{ item.tagName }}</v-chip
                              >
                              <v-spacer></v-spacer>
                              <v-list-item-action @click.stop>
                                   <v-btn
                                        icon
                                        @click.stop.prevent="edit(index, item)"
                                   >
                                        <v-icon>{{
                                             editing !== item
                                                  ? "mdi-pencil"
                                                  : "mdi-check"
                                        }}</v-icon>
                                   </v-btn>
                              </v-list-item-action>
                         </template>
                    </v-combobox>

                    <!-- <v-col class="my-2" cols="12" sm="5" md="8">
               <v-btn @click="addTag" x-large color="Primmary" dark>Add tag</v-btn>
            </v-col> -->
               </v-col>
               <!-- ^^^^ tag ^^^^ -->

               <v-col class="mt-n7" cols="12" sm="8" md="10">
                    <v-textarea
                         multi-line
                         v-model="ins_story"
                         label="Story Area"
                         counter
                         maxlength="5000"
                         full-width
                         single-line
                         background-color="#ffffff"
                         height="auto"
                    />
               </v-col>

               <v-col class="my-2" cols="12" sm="5" md="8">
                    <v-btn @click="checkPost" x-large color="secondary" dark
                         >Post</v-btn
                    >
               </v-col>
          </v-card>
     </div>
</template>

<script>
import api from "../api.js";
// import Tag from "./Tag.vue";

export default {
     // components: [Tag],
     data: () => ({
          ins_title: undefined,
          ins_story: undefined,
          alertFailed: false,
          alertSuccess: false,
          alertmsg: undefined,
          Tags: [],
          // newTags: [],
          tagid: [],
          tagname: [],

          // Tag
          activator: null,
          attach: null,
          colors: ["green", "purple", "indigo", "cyan", "teal", "orange"],
          editing: null,
          index: -1,
          items: [],
          nonce: 1,
          menu: false,
          model: [],
          storageNewTag: [],
          x: 0,
          search: null,
          y: 0,
     }),
     mounted() {
          // Tag
          this.getTag();

          //Post
     },
     methods: {
          postYourContent() {
               let databox = {
                    title: this.ins_title,
                    story: this.ins_story,
                    user: {"id": 1},
                    tag: this.tagname,
               };
               console.log("tagname");
               console.log(JSON.parse(JSON.stringify(this.tagname)));
               api.post("/webblog/post/create", databox)
                    .then(() => {
                         this.clearAlert();
                         this.alertSuccess = true;

                         this.ins_title = null;
                         this.ins_story = null;
                         this.Tags = null;
                         this.storageNewTag = null;
                         // this.newTags = null;
                    })
                    .catch((e) => {
                         console.log("Error in postYourContent(): " + e);
                    });
          },
          checkPost() {
               if (!this.ins_title || !this.ins_story) {
                    this.clearAlert();
                    this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
                    this.alertFailed = true;
               } else {
                    this.addTag();
               }
          },
          pushVarArr() {
               for (let i = 0; i < this.model.length; i++) {
                    this.tagname.push(this.model[i].nameTag);
               }
               this.postYourContent();
          },
          clearAlert() {
               this.alertSuccess = false;
               this.alertFailed = false;
          },
          // receiveTag(value) {
          //    this.storageNewTag = value;
          // console.log("In Post.vue");
          // console.log(JSON.parse(JSON.stringify(this.storageNewTag)));
          // },
          nameTag(value) {
               this.Tags = value;
               console.log("In Post.vue This.Tags");
               console.log(JSON.parse(JSON.stringify(this.Tags)));
          },
          // postTag() {
          //    api.post("webblog/add_tag", this.storageNewTag)
          //       .then(() => {
          //          console.log("Added Tags");
          //       })
          //       .catch(e => {
          //          console.log("Error in postTags():" + e);
          //       })
          // },

          // Tag
          edit(index, item) {
               if (!this.editing) {
                    this.editing = item;
                    this.index = index;
               } else {
                    this.editing = null;
                    this.index = -1;
               }
          },
          filter(item, queryText, itemText) {
               if (item.header) return false;
               const hasValue = (val) => (val != null ? val : "");
               const tagName = hasValue(itemText);
               const query = hasValue(queryText);
               return (
                    tagName
                         .toString()
                         .toLowerCase()
                         .indexOf(query.toString().toLowerCase()) > -1
               );
          },
          getTag() {
               api.get("/webblog/tag/showall")
                    .then((response) => {
                         this.items = response.data;
                         console.log("items");
                         console.log(JSON.parse(JSON.stringify(response.data)));
                    })
                    .catch((e) => {
                         console.log("Error in getTag():" + e);
                    });
          },
          addTag() {
               api.post("/webblog/tag/add", this.storageNewTag)
                    .then(() => {
                         console.log("Added Tags");
                    })
                    .catch((e) => {
                         console.log("Error in postTag(): " + e);
                    });
               this.pushVarArr();
          },
          random_color(colors) {
               return colors[Math.floor(Math.random() * colors.length)];
          },
          // ^^^^ Tag ^^^^
     },
     // Tag
     watch: {
          model(val, prev) {
               if (val.length === prev.length) return;
               this.model = val.map((v) => {
                    if (typeof v === 'string') {
                         v = {
                              tagName: v,
                              tagColor: this.random_color(this.colors),
                         };
                         this.storageNewTag.push(v);
                         this.items.push(v);
                         this.nonce++;
                    }
                    return v;
               });
               console.log("Model");
               console.log(JSON.parse(JSON.stringify(this.model)));
               console.log("Space");
               console.log(JSON.parse(JSON.stringify(this.storageNewTag)));
          },
     },
};
</script>