var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
Vue.http.headers.common[header] = token;

var app = new Vue({
    el: "#app",

    data: {
        v1_user: [],
        v2_user: [],
    },

    ready: function () {
        console.log("ready");
        this.fetchV1IntermediaryUsers();
        this.fetchV2IntermediaryUsers();
    },

    methods: {
        fetchV1IntermediaryUsers: function () {
            console.log("get");
            this.$http.get('/api/v1_users').then(function (response) {
                console.log(response);
                this.v1_user = response.body.data;
            }, function (response) {
                console.log(response);
            });
        },

        fetchV2IntermediaryUsers: function () {
            this.$http.get('/api/v2_users', function (v2users) {
                this.$set('v2_user', v2users);
            });
        }
    }
});

app.fetchV1IntermediaryUsers();

var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            {text: 'Vegetables'},
            {text: 'Cheese'},
            {text: 'Whatever else humans are supposed to eat'}
        ]
    },

    methods: {
        resetList: function () {
                this.groceryList = [
                    {text: 'Vegetables'},
                    {text: 'Cheese'},
                    {text: 'Whatever else humans are supposed to eat'}
                ];
        },
        updateList: function () {
            this.$http.get('/api/update').then(function (response) {
                console.log(response);
                this.groceryList = response.body.data;
            }, function (response) {
                console.log(response);
            });
        },
        reverseList: function () {
            this.$http.post('/api/revers', {groceryList: this.groceryList}).then(function (response) {
                console.log(response);
                this.groceryList = response.body.data.groceryList;
            }, function (response) {
                console.log(response);
            });
        },
        echoList: function () {
            var echo = {
                groceryList: [
                    {text: 'This'},
                    {text: 'Is'},
                    {text: 'echo'},
                    {text: 'list'}
                ]
            };
            this.$http.post('/api/echo', echo).then(function (response) {
                console.log(response);
                this.groceryList = response.body.data.groceryList;
            }, function (response) {
                console.log(response);
            });
        }
    }
});
