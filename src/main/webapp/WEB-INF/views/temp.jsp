<a href="#" class="logo" v-on:click="getUsersInfo">Vue on click</a>

<!-- ?????? ????????? -->
<a v-bind:href="url"></a>
<!-- ?????????? -->
<a :href="url"></a>

<img :src="user.icon" class="img-circle" alt="User Image">

<p>{{user.fullName}}</p>

<!-- ?????? ????????? -->
<a v-on:click="doSomething"></a>
<!-- ?????????? -->
<a @click="doSomething"></a>


<router-link :to="link.url">{{link.name}}</router-link>

<h1 v-if="ok">??</h1>
<h1 v-else>???</h1>

<template v-if="ok">
  <h1>?????????</h1>
  <p>????? 1</p>
  <p>????? 2</p>
</template>

<h1 v-show="ok">??????!</h1>

<ol>
  <li v-for="todo in todos">
    {{ todo.text }}
  </li>
</ol>

<input v-model="parentMsg">



<script>
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    Vue.http.headers.common[header] = token;

    var instruction= [
        {type: 1, params:{text:'HEADER'}},
        {type: 2, params:{to:"'/user/foo'",icon:'fa-link',text:'/user/foo'}},
        {type: 3, params:{to:"'/user/foo/profile'",icon:'fa-link',text:'/user/foo/profile'}},
        {type: 3, params:{to:"'/user/foo/posts'",icon:'fa-link',text:'/user/foo/posts'}},
        {type: 4, params:{icon:'fa-link',text:'multilevel',links:[
            {type: 1, params:{to:"'/user/foo'",icon:'fa-link',text:'/user/foo'}},
            {type: 2, params:{to:"'/user/foo/profile'",icon:'fa-link',text:'/user/foo/profile'}},
            {type: 2, params:{to:"'/user/foo/posts'",icon:'fa-link',text:'/user/foo/posts'}}
        ]}}
    ];

    const User = {
        template: '<div class="user"><h2>User {{ $route.params.id }}</h2><router-view></router-view></div>'
    };

    const router = new VueRouter({
        routes: [
            { path: '/user/:id', component: User,
                children: [
                    { path: '', component: UserHome },
                    { path: 'profile', component: UserProfile },
                    { path: 'posts', component: UserPosts }
                ]
            }
        ]
    });

    Vue.component('link-item', {
        props: ['link'],
        template: '<router-link :to="link.url">{{link.name}}</router-link>'
    });

    Vue.component('async-example', function (resolve, reject) {
        setTimeout(function () {
            // ???????? ?????? ?????????? ? ??????? resolve
            resolve({
                template: '<div>? ? ???????????!</div>'
            })
        }, 1000)
    })

    var app = new Vue({
        el: "#app",

        data: {
            variable: "This is value"
        },

        created: function () {
            console.log("create");
        },

        methods: {
            getRequest: function () {
                console.log("get");
                this.$http.get('/api').then(function (response) {
                    console.log(response, "success");
                    this.variable = response.body.data;
                }, function (response) {
                    console.log(response, "failed");
                    this.variable = "Default value when error"
                });
            }
        }
    });

    Vue.http.get('/api/component/get/all-test').then(function (response) {
    }, function (response) {

    });


</script>











<!-- Sidebar Menu -->
<ul class="sidebar-menu">
  <li class="header">HEADER</li>
  <!-- Optionally, you can add icons to the links -->
  <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
  <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
  <li class="treeview">
    <a href="#"><i class="fa fa-link"></i> <span>Multilevel</span>
      <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
    </a>
    <ul class="treeview-menu">
      <li><router-link to="/user/foo">/user/foo</router-link></li>
      <li><router-link to="/user/foo/profile">/user/foo/profile</router-link></li>
      <li><router-link to="/user/foo/posts">/user/foo/posts</router-link></li>
    </ul>
  </li>
</ul>
<!-- /.sidebar-menu -->