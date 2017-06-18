const workspaceHome = function (resolve) {
    Vue.http.get('/api/component/get/workspace-home').then(function (response) {
        if (response.body.success) {
            resolve({
                template: response.body.data.component
            });
        }
        else {
            resolve({
                template: '<div>Error<div>'
            });
        }
    }, function (response) {
        resolve({
            template: '<div>Internal Error<div>'
        });
    });
};

//----------------------------------------------------------------------------------------------//

var SUPER_DATA = {course_id: 1};

const allCourse = function (resolve) {
    Vue.http.get('/api/component/get/course-all').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;

                resolve({
                    template: template,
                    data: function () {
                        return {coursesList: {}}
                    },
                    methods: {
                        openCourse: function (id) {
                            SUPER_DATA.course_id = id;
                            router.push('/course/'+id);
                        },
                        createCourse: function () {
                            router.push('/course/create');
                        }
                    },
                    mounted: function () {
                        content_app.pageHeader = 'All course';
                        function lock(vue) {
                            Vue.http.get('/new_api/read/course/all').then(function (response) {
                                vue.coursesList = response.body.data.course_list;
                            });
                        }
                        lock(this);
                    }
                });
        }
    });
};

const createCourse = function (resolve) {
    Vue.http.get('/api/component/get/course-create').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;
            resolve({
                template: template,
                data: function () {
                    return {
                        new_course: {
                            bg_color: 'bg-red',
                            image_class: 'fa fa-sticky-note-o',
                            lessons:[]
                        }
                    }
                },
                methods: {
                    save: function () {
                        Vue.http.post('/new_api/create/course', this.new_course).then(function (response) {
                            SUPER_DATA.course_id = response.body.data.course_id;
                            router.push('/course/' + SUPER_DATA.course_id);
                        });
                    },
                    cancel: function () {
                        router.push('/course');
                    }
                }
            });
        }
    });
};

const oneCourse = function (resolve) {
    Vue.http.get('/api/component/get/course-one').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;
            resolve({
                template: template,
                data: function () {
                    return {course: {}}
                },
                methods: {
                    editCourse: function (id) {
                        SUPER_DATA.course_id = id;
                        router.push('/course/' + id +'/edit');
                    },
                    removeCourse: function (id) {
                        SUPER_DATA.course_id = id;
                        Vue.http.get('/new_api/delete/'+SUPER_DATA.course_id).then(function () {
                            router.push('/course');
                        });
                    },
                    openLesson: function (id) {
                        SUPER_DATA.lesson_id = id;
                        router.push('/lesson/' + id);
                    },
                    createLesson: function (id) {
                        SUPER_DATA.course_id = id;
                        router.push('/lesson/create');
                    }
                },
                mounted: function () {
                    function lock(vue) {
                        Vue.http.get('/new_api/read/course/'+SUPER_DATA.course_id).then(function (response) {
                            vue.course = response.body.data.course;
                        });
                    }
                    lock(this);
                }
            });
        }
    });
};

const editCourse = function (resolve) {
    Vue.http.get('/api/component/get/course-edit').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;
                resolve({
                    template: template,
                    data: function () {
                        return {course: {}}
                    },
                    methods: {
                        save: function () {
                            Vue.http.post('/new_api/update/'+this.course.id, this.course).then(function () {
                                router.push('/course/' + SUPER_DATA.course_id);
                            });
                        },
                        cancel: function () {
                            router.push('/course/' + SUPER_DATA.course_id);
                        }
                    },
                    mounted: function () {
                        function lock(vue) {
                            Vue.http.get('/new_api/read/course/' + SUPER_DATA.course_id).then(function (response) {
                                vue.course = response.body.data.course;
                                var lessons = vue.course.lessons;
                                var lessons_id = [];
                                lessons.forEach(function (lesson) {
                                    lessons_id.push(lesson.id);
                                });
                                vue.course.lessons = lessons_id;
                            });
                        }
                        lock(this);
                    }
                });
        }
    });
};

const createLesson = function (resolve) {
    Vue.http.get('/api/component/get/lesson-create').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;
            resolve({
                template: template,
                data: function () {
                    return {
                        new_lesson: {
                            bg_color: 'bg-red',
                            image_class: 'fa fa-sticky-note-o'
                        }
                    }
                },
                methods: {
                    save: function () {
                        this.new_lesson.text = tinymce.editors['id_textarea'].getContent();
                        Vue.http.post('/new_api/create/lesson', this.new_lesson).then(function (response) {
                            Vue.http.get('/new_api/link/'+ SUPER_DATA.course_id+'/'+response.body.data.lesson_id).then(function (response) {
                                router.push('/course/' + SUPER_DATA.course_id);
                            });
                        });
                        tinymce.execCommand('mceRemoveEditor', false, 'id_textarea');
                    },
                    cancel: function () {
                        tinymce.execCommand('mceRemoveEditor', false, 'id_textarea');
                        router.push('/course/' + SUPER_DATA.course_id);
                    }
                },
                mounted: function (){
                    tinymce.init({ selector:'#id_textarea' });
                }
            });
        }
    });
};

const oneLesson = function (resolve) {
    Vue.http.get('/api/component/get/lesson-one').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;

                resolve({
                    template: template,
                    data: function () {
                        return {lesson: {}}
                    },
                    methods: {
                        editLesson: function (id) {
                            SUPER_DATA.lesson_id = id;
                            router.push('/lesson/' + id +'/edit');
                        },
                        removeLesson: function (id) {
                            SUPER_DATA.lesson_id = id;
                            Vue.http.get('/new_api/delete/' + SUPER_DATA.lesson_id).then(function () {
                                Vue.http.get('/new_api/unlink/'+SUPER_DATA.course_id+'/' + SUPER_DATA.lesson_id).then(function () {
                                    router.push('/course/' + SUPER_DATA.course_id);
                                });
                            });
                        },
                    },
                    mounted: function () {
                        function lock(vue) {
                            Vue.http.get('/new_api/read/lesson/' + SUPER_DATA.lesson_id).then(function (response) {
                                vue.lesson = response.body.data.lesson;
                            });
                        }
                        lock(this);
                    }
                });
        }
    });
};

const editLesson = function (resolve) {
    Vue.http.get('/api/component/get/lesson-edit').then(function (response) {
        if (response.body.success) {
            var template = response.body.data.component;

                resolve({
                    template: template,
                    data: function () {
                        return {lesson: {}}
                    },
                    methods: {
                        save: function () {
                            this.lesson.text = tinymce.editors['id_textarea'].getContent();
                            SUPER_DATA.lesson_id = this.lesson.id;
                            Vue.http.post('/new_api/update/'+SUPER_DATA.lesson_id, this.lesson).then(function (response) {
                                router.push('/lesson/' + SUPER_DATA.lesson_id);
                            });
                        },
                        cancel: function () {
                            SUPER_DATA.lesson_id = this.lesson.id;
                            tinymce.execCommand('mceRemoveEditor', false, 'id_textarea');
                            router.push('/lesson/' + SUPER_DATA.lesson_id);
                        }
                    },
                    mounted: function () {
                        function lock(vue) {
                            Vue.http.get('/new_api/read/lesson/' + SUPER_DATA.lesson_id).then(function (response) {
                                vue.lesson = response.body.data.lesson;
                                tinymce.init({ selector:'#id_textarea' });
                                setTimeout(function () {
                                    tinymce.editors['id_textarea'].setContent(vue.lesson.text);
                                },1000);
                            });
                        }
                        lock(this);

                    }
                });
        }
    });
};


const router = new VueRouter({
    routes: [
        {path: '/', component: workspaceHome},
        {path: '/course', component: allCourse},
        {path: '/course/create', component: createCourse},
        {path: '/course/:id', component: oneCourse},
        {path: '/course/:id/edit', component: editCourse},
        {path: '/lesson/create', component: createLesson},
        {path: '/lesson/:id', component: oneLesson},
        {path: '/lesson/:id/edit', component: editLesson}
        //{path: '/test/create', component: createTest},
        //{path: '/test/:id', component: oneTest},
        //{path: '/test/:id/edit', component: editTest}
    ]
});
