var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
Vue.http.headers.common[header] = token;



var scope = {
    header: {
        user: {
            fullName: "Alexander Pierce",
            icon: "/static/images/user2-160x160.jpg",
            place: "Web Developer",
            member: "Nov. 2012"
        }
    },

    sidebar: {
        meny: "lol"
    }
};

var header_app = new Vue({
    el: "#header-app",

    data: scope.header,

    methods: {
        getUsersInfo: function () {
            console.log("get getUsersInfo");
            this.$http.get('/api/user').then(function (response) {
                console.log(response);
                scope.header.user = response.body.data.user;
            }, function (response) {
                console.log(response);
            });
        }
    }
});

var sidebar_app = new Vue({
    router: router,
    el: "#sidebar-app",

    data:{
        scope: scope
    },

    created: function () {
        //console.log("created");
    },

    methods: {
        getUsersInfo: header_app.getUsersInfo
    }
});
