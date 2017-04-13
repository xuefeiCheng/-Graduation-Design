/**
 * Created by cxf on 2016/9/23.
 */

//自定义路由
//angular.module('app', ['ui.router'])
angular.module('app')
    .run(['$rootScope', '$window', '$state', '$location','$stateParams', '$log','$templateCache', function ($rootScope,$stateParams,$state, $window, $location, $log,$templateCache) {
        //监测路由的变化
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        $rootScope.$on('$stateChangeSuccess',
            function(event, toState, toParams, fromState, fromParams){
                if(toState.name=="login"){
                    $templateCache.removeAll();
                }
            });
        //var stateChangeSuccess = $rootScope.$on('$stateChangeSuccess', stateChangeSuccess);
        //
        //function stateChangeSuccess($rootScope) {
        //    $templateCache.removeAll();
        //}

    }])
    .config(
        [ '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG',
            function ($stateProvider,   $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {
                var layout = "tpl/blocks/home.layout.html";
                $urlRouterProvider
                    //路由重定向
                    .otherwise('login');

                $stateProvider
                    .state('login', {
                        url: '/login',
                        cache:'false',//禁止页面缓存
                        controller: 'LoginCtrl',
                        templateUrl: 'login-cxf.html',
                        resolve:load(['css/login.css'])
                    })
                    .state('app', {
                        abstract: true,
                        url: '/app',
                        templateUrl: layout,
                        resolve:load(['css/header.css'])
                        //resolve:(['js/home/home.js'])
                    })
                    //首页
                    .state('app.home', {
                        url: '/home/:UserId/:roleId',
                        //多个 view的视图设置
                        //views: {
                        //    "header_view": {
                        //        controller:"headerCtrl",
                        //        templateUrl: 'tpl/blocks/home.header.html'
                        //    },
                        //    "": {
                        //        controller:'homeCtrl',
                        //        templateUrl: 'tpl/blocks/home.html'
                        //    }
                        //}
                        controller: 'homeCtrl',
                        templateUrl: 'tpl/blocks/home.html'
                        // use resolve to load other dependences
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //个人信息 管理
                    .state('app.information', {
                        url: '/information/:UserId/:roleId',
                        abstract: true,
                        controller:"infoCtrl",
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/information.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    .state('app.information.edit', {
                        url: '/edit',
                        //controller:"infoCtrl",
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/details/information-edit.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    .state('app.information.edit-do', {
                        url: '/edit',
                        //controller:"infoCtrl",
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/details/information-edit-do.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教
                    .state('app.myTeachers', {
                        url: '/myTeachers/:UserId',
                        abstract: true,
                        controller:'PingTeCtrl',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/myTeachers.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教 默认 页面
                    .state('app.myTeachers.default', {
                        url: '/myTeachers/default',
                        templateUrl: 'tpl/details/myTeachers-default.html'
                    })
                    //评教详情页面
                    .state('app.myTeachers.detail', {
                        url: '/myTeachers/detail/:TeId/:courseId',
                        controller: 'PingTeDetailCtrl',
                        templateUrl: 'tpl/details/myTeachers-detail.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评学结果查询
                    .state('app.studentsResult', {
                        abstract: true,
                        url: '/studentsResult',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/studentsResult.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评学结果查询  详情页面
                    .state('app.studentsResult-detail', {
                        url: '/studentsResult/detail',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/details/studentsResult-detail.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评学
                    .state('app.myStudents', {
                        url: '/myStudents/:UserId',
                        abstract: true,
                        controller: 'PingStCtrl',
                        templateUrl: 'tpl/function/myStudents.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评学  默认进入页面
                    .state('app.myStudents.default', {
                        url: '/myStudents/default',
                        templateUrl: 'tpl/details/myStudents-default.html'
                    })
                    //评学 根据课程 查询到 班级 -> 学生列表
                    .state('app.myStudents.list', {
                        url: '/myStudents/list/:courseId',
                        controller: 'studentListCtrl',
                        templateUrl: 'tpl/details/myStudents-list.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评学详情页面 具体学生所在的班级 即课程 评价表
                    .state('app.myStudents.detail', {
                        url: '/myStudents/detail/:courseId',
                        controller: 'PingStDetailCtrl',
                        templateUrl: 'tpl/details/myStudents-detail.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教结果查询
                    .state('app.teachersResult', {
                        abstract: true,
                        url: '/teachersResult',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/teachersResult.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教结果查询  详情页面
                    .state('app.teachersResult-detail', {
                        url: '/teachersResult/detail',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/details/teachersResult-detail.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教统计
                    .state('app.count', {
                        url: '/count',
                        abstract: true,
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/count.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //评教统计  详情页面
                    .state('app.count.detail', {
                        url: '/count/detail',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/details/count-detail.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //老师排名
                    .state('app.rankingList', {
                        url: '/rankingList',
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/function/rankingList.html'
                        //resolve: load(['moment', 'echarts3'])
                    })
                    //    学生
                    .state('app.students', {
                        url: '/students',
                        abstract: true,
                        //controller: 'studentsCtrl',
                        templateUrl: 'tpl/students/students.html'
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
                    .state('app.teachers', {
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
                        templateUrl: 'tpl/school/result.html'
                    })
                    //班级统计
                    .state('app.school.classes', {
                        url: '/classes',
                        templateUrl: 'tpl/school/class-charts.html'
                    })
                    //老师统计
                    .state('app.school.teachers', {
                        url: '/teachers',
                        template: '老师统计图表'
                    })

                //load函数
                function load(srcs, callback) {
                    return {
                        deps: ['$ocLazyLoad', '$q',
                            function( $ocLazyLoad, $q ){
                                var deferred = $q.defer();
                                var promise  = false;
                                srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                                if(!promise){
                                    promise = deferred.promise;
                                }
                                angular.forEach(srcs, function(src) {
                                    promise = promise.then( function(){
                                        if(JQ_CONFIG[src]){
                                            return $ocLazyLoad.load(JQ_CONFIG[src]);
                                        }
                                        angular.forEach(MODULE_CONFIG, function(module) {
                                            if( module.name == src){
                                                name = module.name;
                                            }else{
                                                name = src;
                                            }
                                        });
                                        return $ocLazyLoad.load(name);
                                    } );
                                });
                                deferred.resolve();
                                return callback ? promise.then(function(){ return callback(); }) : promise;
                            }]
                    }
                }
            }]
    );


//自定义 工厂方法
angular.module('app')
    .factory('Auth',function($http,$q,$rootScope){
        var Auth = {};
        var defer = $q.defer();
        var User = defer.promise;
        $http.get('/application/user').then(function(response){
            defer.resolve(response.data);
        },function(){
            defer.reject();
            throw 'Get User Failed!'
        });
        User.then(function(User){
            Auth.user = User;
            $rootScope.auth = Auth;
            $rootScope.user = User;
            $rootScope.navi = createNavi(User);
        });
        function createNavi(User){
            var topic = {
                name:'专题事件',
                check: 'dissertation',
                state: 'app.dissertation.line',
                type:'direct-link',
                href:'/topic',
                color:"clr-dissertation",
                icon:"img/ztsj.png"
            };
            var infoMonitor = {
                name: '信息监测',
                check: 'infomonitor',
                state: 'app.infomonitor.infom.index',
                color: "clr-infomonitor",
                icon: "img/xxjc.png"
            };
            var groupview = {
                name:'群体监测',
                check:'groupview',
                state: 'app.groupview.work-sub({itemId:1})',
                color:"clr-groupview",
                icon:"img/qtjc.png"
            };
            var sentimentsituation = {
                name:'舆情态势',
                check:'sentimentsituation',
                state:'app.sentimentsituation.sentiment',
                color:"clr-sentimentsituation",
                icon:"img/yqts.png"
            };
            var hotpoint = {
                name:'热点推荐',
                check: 'hotpoint',
                state:'app.hotpoint.hotpointdochome',
                color:"clr-hotpoint",
                icon:"img/rdtj.png"
            };
            var infoalarm = {
                name: '行动预警',
                check: 'infoalarm',
                state: 'app.infoalarm.sudden.sub({itemName:"自然灾害",itemId:0})',
                color:"clr-infoalarm",
                icon:"img/yjzt.png"
            };
            var knowledgepool = {
                name: '知识库',
                check: 'knowledgepool',
                state: 'app.knowledgepool.groupdata.sub({itemId:1})'
            };
            var materialmanage = {
                name:'素材管理',
                check:'materialmanage',
                state:'app.materialmanage.matter({itemId:1})'
            };
            var systemmanage ={
                name: '系统管理',
                check:'systemmanage',
                /*state: 'app.systemmanage.right.sub({itemId:1})'*/
                state: 'app.systemmanage.right.user'
            };
            var navi ={main:[],top:[]};
            var main = [infoMonitor,topic,groupview,sentimentsituation,hotpoint,infoalarm];
            var top = [materialmanage,knowledgepool,systemmanage];
            for(var i=0;i<main.length;i++) {
                var config = main[i];
                +function(config) {
                    Auth.check('menu_' + config.check).then(function (result) {
                        if (result == true) {
                            navi.main.push(config);
                        }
                    })
                }(config)
            }
            for(var i=0;i<top.length;i++) {
                var config = top[i];
                +function (config) {
                    Auth.check('menu_'+config.check).then(function(result){
                        if(result == true){
                            navi.top.push(config);
                        }
                    })
                }(config)

            }
            return navi;
        }
        function checkauth(o,k) {
            if(!o || !o.length){
                return false;
            }
            for(var i=0;i<o.length;i++) {
                if(o[i].name == k) {
                    return true;
                }
                if(o[i].children && o[i].children.length){
                    if(checkauth(o[i].children,k)){
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Auth.check('menu_key').then(function(result){
         *    if(result==true){
         *        //....
         *    }
         * });
         * @type {{check: Function}}
         */
        var Auth = {
            check: function(key){
                return User.then(function(User){
                    var menuReg = /^menu_/,functionReg = /^function_/;
                    if(menuReg.test(key)){
                        key = key.substring(5);
                        return checkauth(User.menu.children,key);
                    }
                    if(functionReg.test(key)){
                        key = key.substring(9);
                        return checkauth(User.functions,key);
                    }
                    throw "Illegal check format!";
                })
            }
        };
        return Auth;
    });