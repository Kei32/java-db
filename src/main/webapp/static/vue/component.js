Vue.component('sidebar-menu', function (resolve) {
    setTimeout(function () {
        var instruction_bac= [
            {type: 1, params:{text:'Teacher menu'}},
            {type: 2, params:{to:"'/'",icon:'fa-link',text:'Home'}},
            {type: 3, params:{to:"'/class'",icon:'fa-link',text:'Class'}},
            {type: 3, params:{to:"'/students'",icon:'fa-link',text:'Students'}},
            {type: 4, params:{icon:'fa-link',text:'Study',links:[
                {type: 1, params:{to:"'/study/course'",icon:'fa-link',text:'Course'}},
                {type: 2, params:{to:"'/study/lesson'",icon:'fa-link',text:'Lesson'}},
                {type: 2, params:{to:"'/study/test'",icon:'fa-link',text:'Test'}}
            ]}}
        ];

        var instruction= [
            {type: 1, params:{text:'Teacher menu'}},
            {type: 2, params:{to:"'/'",icon:'fa-link',text:'Home'}},
            {type: 3, params:{to:"'/course'",icon:'fa-link',text:'Course'}}
        ];
        var resultTemplate = "<ul class='sidebar-menu'>";
        instruction.forEach(function (item) {
            switch (item.type) {
                case 1:
                    resultTemplate+='<li class="header">'+item.params.text+'</li>';
                    break;
                case 2:
                    resultTemplate+='<li class="active"><a onclick="router.push('+item.params.to+');"><i class="fa '+
                        item.params.icon+'"></i> <span>'+item.params.text+'</span></a></li>';
                    break;
                case 3:
                    resultTemplate+='<li><a onclick="router.push('+item.params.to+');"><i class="fa '+
                        item.params.icon+'"></i> <span>'+item.params.text+'</span></a></li>';
                    break;
                case 4:
                    resultTemplate+='<li class="treeview"><a href="#"><i class="fa '+item.params.icon+'"></i> <span>'+item.params.text+'</span><span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span></a><ul class="treeview-menu">';
                    item.params.links.forEach(function (item) {
                        if(item.type == 1){
                            resultTemplate+='<li class="active"><a onclick="router.push('+item.params.to+');"><i class="fa '+item.params.icon+'"></i> <span>'+item.params.text+'</span></a></li>';
                        }
                        if(item.type == 2){
                            resultTemplate+='<li><a onclick="router.push('+item.params.to+');"><i class="fa '+item.params.icon+'"></i> <span>'+item.params.text+'</span></a></li>';
                        }
                    });
                    resultTemplate+='</ul></li>';
                    break;
            }
        });
        resultTemplate += "</ul>";
        resolve({
            template: resultTemplate
        });
    })
});

Vue.component('class-filter', {
    props: ['items'],
    template: '#class-filter',
    methods: {
        testMey: function () {
            this.items[1].progress = 50;
            this.items[1].progressBar = 'width:50%';
        }
    }
});

Vue.component('class-table', {
    props: ['items'],
    template: '#class-table',
    methods: {
        doSomethingMethods: function () {
            alert('doSomethingMethods');
        },
        rooutePush: function () {
            router.push('/class/tyui');
        }
    }
});