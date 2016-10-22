/**
 * Created by cxf on 2016/9/23.
 */
angular.module('app', ['ui.router','studentsCtrl']).config(
    ['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            var layout = "tpl/blocks/home.layout.html";
            $urlRouterProvider
                .otherwise('app/home');

            $stateProvider
                .state('app', {
                    abstract: true,
                    url: '/app',
                    templateUrl: layout
                    //resolve:(['js/home/home.js'])
                })
                //首页
                .state('app.home', {
                    url: '/home',
                    //controller: 'homeCtrl',
                    templateUrl: 'tpl/blocks/home.html'
                    // use resolve to load other dependences
                    //resolve: load(['moment', 'echarts3'])
                })
                //    学生
                .state('app.students', {
                    url: '/students',
                    abstract: true,
                    //controller: 'studentsCtrl',
                    templateUrl: 'tpl/students/students.html'
                    //deps:'([js/students/controller.js])'
                    //deps:(['js/students/config.js'])
                    // use resolve to load other dependences
                    //resolve: load(['moment', 'echarts3'])
                })
                //学生个人
                .state('app.students.myself', {
                    url: '/myself',
                    controller: 'studentsmyselfCtrl',
                    templateUrl: 'tpl/students/students.myself.html'

                })
                .state('app.students.myself1', {
                    url: '/myself1',
                    controller: 'studentsmyselfCtrl1',
                    templateUrl: 'tpl/students/students.myself1.html'
                })
                //学生评学结果查询
                .state('app.students.my-result', {
                    url: '/my-result',
                    template: '学生评学结果查询'
                })
                //学生评教入口
                .state('app.students.my-teachers', {
                    url: '/my-teachers',
                    template: '学生评教入口'
                })


                //    老师
                .state('app.teachers'
                , {
                    url: '/teachers',
                    abstract: true,
                    //controller: 'teachersCtrl',
                    templateUrl: 'tpl/teachers/teachers.html'
                    // use resolve to load other dependences
                    //resolve: load(['moment', 'echarts3'])
                })

                //老师个人
                .state('app.teachers.myself', {
                    url: '/myself',
                    template: '老师个人信息'
                })
                //老师评教结果查询
                .state('app.teachers.my-result', {
                    url: '/my-result',
                    template: '老师评教结果查询'
                })
                //老师评学入口
                .state('app.teachers.my-students', {
                    url: '/my-students',
                    template: '老师评教入口'
                })

                //    督导中心
                .state('app.school', {
                    url: '/school',
                    abstract: true,
                    //controller: 'schoolCtrl',
                    templateUrl: 'tpl/school/school.html'
                    //deps:(['js/school/config.js'])
                    // use resolve to load other dependences
                    //resolve: load(['moment', 'echarts3'])
                })
                //督导个人
                .state('app.school.myself', {
                    url: '/myself',
                    template: '督导个人管理'
                })
                //评学评教结果查询
                .state('app.school.result', {
                    url: '/result',
                    template: '评学评教查阅'
                })
                //班级统计
                .state('app.school.classes', {
                    url: '/classes',
                    template: '班级统计图表'
                })
                //老师统计
                .state('app.school.teachers', {
                    url: '/teachers',
                    template: '老师统计图表'
                });


            //function load(srcs, callback) {
            //    return {
            //        deps: ['$ocLazyLoad', '$q',
            //            function( $ocLazyLoad, $q ){
            //                var deferred = $q.defer();
            //                var promise  = false;
            //                srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
            //                if(!promise){
            //                    promise = deferred.promise;
            //                }
            //                angular.forEach(srcs, function(src) {
            //                    promise = promise.then( function(){
            //                        if(JQ_CONFIG[src]){
            //                            return $ocLazyLoad.load(JQ_CONFIG[src]);
            //                        }
            //                        angular.forEach(MODULE_CONFIG, function(module) {
            //                            if( module.name == src){
            //                                name = module.name;
            //                            }else{
            //                                name = src;
            //                            }
            //                        });
            //                        return $ocLazyLoad.load(name);
            //                    } );
            //                });
            //                deferred.resolve();
            //                return callback ? promise.then(function(){ return callback(); }) : promise;
            //            }]
            //    }
            //}

        }])