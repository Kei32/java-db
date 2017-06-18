var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
Vue.http.headers.common[header] = token;



var content_app = new Vue({
    router: router,
    el: "#content-app",

    data: {
        pageHeader: "Page Header"
    },

    methods: {
        initTest: function () {
            console.log("function initTest");
            this.$http.get('/api/init-test').then(function (response) {
                console.log("success", response);
                this.test = response.body.data;
            }, function (response) {
                console.log("failed", response);
            });
        },
        colorSwicher: function () {
            console.log("function colorSwicher ");
            colorVal == "red" ? colorVal = "blue" : colorVal = "red";
            console.log("Result color: " + colorVal);
        }
    }
});