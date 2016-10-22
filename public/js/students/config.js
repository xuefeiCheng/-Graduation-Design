/**
 * Created by cxf on 2016/9/26.
 */
angular.module('students',['ui.load'])
    //.run(function($timeout){
    //    $(document).ready(function(){
    //        var scope=angular.element(document.querySelector('[ng-controller=AppCtrl]')).scope();
    //        var t=$timeout(function(){
    //            $timeout.cancel(t);
    //            scope.$apply(function(){
    //                scope.systemNavigator.push({
    //                    name:'督导中心',
    //                    state:'app.school'
    //                });
    //            })
    //        },0);
    //    })
    //})
    .config(function($stateProvider,JQ_CONFIG,MODULE_CONFIG){
        $stateProvider
            //搜索
            .state('app.students.myself', {
                url: '/myself',
                template:'学生个人信息'
                //templateUrl: 'tpl/school/school.html',
                //// use resolve to load other dependences
                //controller: 'schoolCtrl',
                //resolve: load( [	  'js/search/controller.js',
                //    'css/newstyle/search.css','moment','toaster',
                //    'libs/jquery/bootstrap-daterangepicker/daterangepicker.js',
                //    'libs/jquery/bootstrap-daterangepicker/daterangepicker-bs3.css',
                //    'libs/jquery/bootstrap/dist/js/bootstrap.min.js'])
            })
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
    })