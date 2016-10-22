/**
 * Created by cxf on 2016/9/28.
 */

//angular.module('app', []).controller('studentsCtrl',function($scope) {});
//function change($state){
//   alert('change')
//}
angular.module("studentsCtrl", [])
    .controller('studentsmyselfCtrl', ['$scope', '$state',
        function ($scope, $state) {
            $scope.user = {
                id: 201358503103,
                name: '程雪飞',
                sex:'女',
                age:'20'
            };
            $scope.students = [{
                id: 201358503103,
                name: '程雪飞',
                sex:'女',
                age:'20'
            },{
                id: 201358503101,
                name: '王雪娇',
                sex:'女',
                age:'20'
            },{
                id: 201358503105,
                name: '黄顺',
                sex:'男',
                age:'20'
            },{
                id: 201358503108,
                name: '于凤云',
                sex:'女',
                age:'20'
            }];
            $scope.save = function () {
                $state.go('app.students.myself1');
                //change();
            }
        }])
    .controller('studentsmyselfCtrl1', ['$scope', '$state',
        function ($scope, $state) {
            $scope.save1 = function () {
                //$state.go('app.students.myself1');
                alert(' save success!!!!');
                $state.go('app.students.myself');
            }
        }]);