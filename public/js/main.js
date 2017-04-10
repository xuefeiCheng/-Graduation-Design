/**
 * Created by cxf on 2016/9/26.
 */
angular.module('app')
    .controller("AppCtrl",["$scope","$state",function($scope,$state){
    }])
    .controller("LoginCtrl",["$scope","$state","$http",function($scope,$state,$http){
            //登录验证  （页面）
        var userId = $("#user");
        var ps = $("#ps");
        //$("#user").css("background-color","#B2E0FF");

            //原生js函数写法
            //$("#ps").onmouseover = function () {
            //    this.select();
            //};
            //鼠标悬停 选中文本
        userId.mouseover(function () {
            this.select();
        });
            //jquery中的函数  是mouseover
        ps.mouseover(function(){
            this.select();
        });

            // 用户离开  输入框
            //onblur()  与  blur()的区别
            //js原生          jquery中的
            //注意  函数写法
        // 验证结果
        //function result(id,img,value){
        //    $(id).className=img;
        //    $(id).innerHTML=value;
        //}
        function msg (id,result,txt){
            if(result=="wrong"){
                $(id).show();
                $(id).html(txt);
            }else{
                $(id).hide();
                $(id).html(txt);
            }

        }
        userId.blur(function(){
            var txt = this.value;
            if(txt == ""){
                //console.log("学号不能为空");
                userId.removeClass("right");
                userId.focus();
                userId.addClass("wrong");
                msg("#result-wrong","wrong","学号不能为空，请重新输入");
                //show("#result","学号不能为空");
                //$("#result-wrong").show();
                //$("#result-wrong").html("学号不能为空，请重新输入");
                //$("#result").innerHTML="学号不能为空";
                //userId.attr('placeholder','学号不能为空');
            }else if(isNaN(txt)){
                userId.removeClass("right");
                //console.log("学号仅为数字的组合");
                this.select();
                userId.addClass("wrong");
                msg("#result-wrong","wrong","学号仅为数字的组合，请重新输入");
                //$("#result-wrong").show();
                //$("#result-wrong").html("学号仅为数字的组合，请重新输入");
                //userId.attr('placeholder','学号仅为数字的组合');
            }else if(txt.length!=12){
                userId.removeClass("right");
                userId.addClass("wrong");
                msg("#result-wrong","wrong","学号为12位数字组合，请重新输入");
                //$("#result-wrong").show();
                //$("#result-wrong").html("学号为12位数字组合，请重新输入");
                //userId.attr('placeholder','学号为12位数字组合');
                this.select();
            }
            else{
                $("#result-wrong").hide();
                userId.removeClass("wrong");
                userId.addClass("right");
            }
            //console.log("用户离开输入框了");
        });
        $("#ps").blur(function(){});
            //自动聚焦
        userId.focus();


            //console.log("hhahaha");
            $scope.login=function(){
                //if(ps == null){
                //    alert("密码不能为空");
                //    return ;
                //}
                //if(userId == null){
                //    alert("学号不能为空");
                //    return;
                //}
                //console.log($scope.username);
                //console.log($scope.password);
                //$state.go('app.home');
                $http({
                    method:'post',
                    url:'/login',
                    params:{
                        'username':$scope.username,
                        'password':$scope.password
                    }
                }).success(function(data){
                    console.log(data);
                    if(data ==""){
                        msg("#result-wrong","wrong","用户不存在或者密码错误，请检查重新输入");
                        return;
                    }else{
                        $state.go('app.home',{UserName:data.name});
                    }

                })
            }
        }])
    .controller("homeCtrl",function($scope,$state,$http,$stateParams){
        //console.log($stateParams.UserName);
    })
    .controller("headerCtrl",function($scope,$state,$http,$stateParams,$rootScope){
        //console.log($stateParams.UserName);
        //由 LoginCtrl 传递过来的参数  可以用于全局的数据绑定
        $rootScope.userxxx=$stateParams.UserName;
    });
    //.controller('AppCtrl', ['$scope','$state',
    //    function( $scope,$state) {
    //        $scope.pageKeyDown = function(e){
    //            var keycode = window.event?e.keyCode:e.which;
    //            if(keycode==13){
    //                $state.go('app.search');
    //            }
    //        };
    //        $scope.search=function(){
    //            $state.go('app.search');
    //        };
    //
    //        // add 'ie' classes to html
    //        var isIE = !!navigator.userAgent.match(/MSIE/i);
    //        if(isIE){ angular.element($window.document.body).addClass('ie');}
    //        if(isSmartDevice( $window ) ){ angular.element($window.document.body).addClass('smart')};
    //
    //        // 系统顶部导航栏
    //        $scope.systemNavigator=[];
    //        //$scope.systemNavigatortop=[];
    //
    //
    //
    //
    //
    //
    //        //字号设置
    //        var fontConfig = {
    //            small: 14,
    //            middle: 15,
    //            large: 16
    //        };
    //        var selectFont = $('body');
    //        var selectFontHtml = $('html');
    //        var currentsize = localStorage.getItem("fontsize");
    //        if(currentsize==fontConfig.small){
    //            selectFont.addClass('small-font');
    //            selectFontHtml.addClass('small-font');
    //        }else if(currentsize==fontConfig.large){
    //            selectFont.addClass('large-font');
    //            selectFontHtml.addClass('large-font');
    //        }else{
    //            selectFont.addClass('middle-font');
    //            selectFontHtml.addClass('middle-font');
    //        }
    //        $scope.fontsizes=[{
    //            size:fontConfig.small,
    //            name:'小'
    //        },{
    //            size:fontConfig.middle,
    //            name:'中'
    //        },{
    //            size:fontConfig.large,
    //            name:'大'
    //        }];
    //
    //        $scope.editToggle=false;
    //        $scope.editParams=function(){
    //            $('.edit-params').removeClass('hide');
    //            $scope.editToggle=!$scope.editToggle;
    //        };
    //        $scope.currentFont=currentsize;
    //        $scope.selectFont=function(font){
    //            $scope.currentFont = font.size;
    //
    //            if(font.size==fontConfig.small){
    //                selectFont.removeClass('middle-font');
    //                selectFont.removeClass('large-font');
    //                selectFont.addClass('small-font');
    //
    //                selectFontHtml.removeClass('middle-font');
    //                selectFontHtml.removeClass('large-font');
    //                selectFontHtml.addClass('small-font');
    //                localStorage.setItem("fontsize",fontConfig.small);
    //
    //            }else if(font.size==fontConfig.middle){
    //                selectFont.removeClass('small-font');
    //                selectFont.removeClass('large-font');
    //                selectFont.addClass('middle-font');
    //
    //
    //                selectFontHtml.removeClass('small-font');
    //                selectFontHtml.removeClass('large-font');
    //                selectFontHtml.addClass('middle-font');
    //                localStorage.setItem("fontsize",fontConfig.middle);
    //
    //            }else if(font.size==fontConfig.large){
    //                selectFont.removeClass('middle-font');
    //                selectFont.removeClass('small-font');
    //                selectFont.addClass('large-font');
    //
    //                selectFontHtml.removeClass('middle-font');
    //                selectFontHtml.removeClass('small-font');
    //                selectFontHtml.addClass('large-font');
    //                localStorage.setItem("fontsize",fontConfig.large);
    //
    //            }
    //        };
    //
    //
    //
    //        function isSmartDevice( $window )
    //        {
    //            // Adapted from http://www.detectmobilebrowsers.com
    //            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
    //            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
    //            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
    //        }
    //
    //    }]);
