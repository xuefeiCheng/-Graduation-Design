/**
 * Created by cxf on 2016/9/26.
 */

//自定义 服务  用于加载 Echars图
angular.module('app')
    .directive('line', function() {
        return {
            scope: {
                id: "@"
            },
            restrict: 'E',
            template: '<div style="height:600px;width:1000px;margin: 0 auto"></div>',
            replace: true

        };
});
angular.module('app')
    .controller("AppCtrl",["$scope","$state",function($scope,$state){
        $scope.$on("USER",function(event,data){
            console.log("ssss");
            console.log(data);
            $scope.$broadcast("child",{data:data})
        });
    }])
    .controller("LoginCtrl",["$scope","$state","$http","$location",function($scope,$state,$http,$location){
        //$location.reload();
        $scope.reloadRoute = function () {
            console.log("页面重新加载");
            $window.location.reload();
        };
    $("#student").keydown(function(event){
        if(event.keyCode ==13){
            $scope.login($scope.stName,$scope.stPsd);
        }
    });

        //$("#user").css("background-color","#B2E0FF");

        //原生js函数写法
        //$("#ps").onmouseover = function () {
        //    this.select();
        //};

        // 用户离开  输入框
        //onblur()  与  blur()的区别
        //js原生          jquery中的
        //注意  函数写法
        // 验证结果
        //function result(id,img,value){
        //    $(id).className=img;
        //    $(id).innerHTML=value;
        //}
        //封装 消息 函数
        function msg (id,result,txt){
            if(result=="wrong"){
                $(id).show();
                $(id).html(txt);
            }else{
                $(id).hide();
                $(id).html(txt);
            }
        }
        //学生登录验证  （页面）
        var stName = $("#stName");
        var teName = $("#teName");
        var leName = $("#leName");

        var stPsd = $("#stPsd");
        var tePsd = $("#tePsd");
        var lePsd = $("#lePsd");

        stName.focus();
        //鼠标悬停 选中文本
        stName.mouseover(function () {
           this.select();
        });
        teName.mouseover(function () {
            this.select();
        });
        leName.mouseover(function () {
            this.select();
        });
        stPsd.mouseover(function () {
            this.select();
        });
        tePsd.mouseover(function () {
            this.select();
        });
        lePsd.mouseover(function () {
            this.select();
        });
        //jquery中的函数  是mouseover

        //页面验证
        function st(){
            stName.blur(function(){
                var txt = this.value;
                if(txt == ""){
                    //console.log("学号不能为空");
                    stName.removeClass("right");
                    stName.focus();
                    stName.addClass("wrong");
                    msg("#result-wrong","wrong","学号不能为空，请重新输入");
                    //show("#result","学号不能为空");
                    //$("#result-wrong").show();
                    //$("#result-wrong").html("学号不能为空，请重新输入");
                    //$("#result").innerHTML="学号不能为空";
                    //userId.attr('placeholder','学号不能为空');
                }else if(isNaN(txt)){
                    stName.removeClass("right");
                    //console.log("学号仅为数字的组合");
                    this.select();
                    stName.addClass("wrong");
                    msg("#result-wrong","wrong","学号仅为数字的组合，请重新输入");
                    //$("#result-wrong").show();
                    //$("#result-wrong").html("学号仅为数字的组合，请重新输入");
                    //userId.attr('placeholder','学号仅为数字的组合');
                }else if(txt.length!=12){
                    stName.removeClass("right");
                    stName.addClass("wrong");
                    msg("#result-wrong","wrong","学号为12位数字组合，请重新输入");
                    //$("#result-wrong").show();
                    //$("#result-wrong").html("学号为12位数字组合，请重新输入");
                    //userId.attr('placeholder','学号为12位数字组合');
                    this.select();
                }
                else{
                    $("#result-wrong").hide();
                    stName.removeClass("wrong");
                    stName.addClass("right");
                }
                //console.log("用户离开输入框了");
            });
        }
        function te(){
            $("#result-wrong").hide();
            stName.removeClass("wrong");
            stName.removeClass("right");
        }
        function le(){
            $("#result-wrong").hide();
        }

        $(function(){
            $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                // 获取已激活的标签页的名称
                var activeTab = $(e.target).text();
                // 获取前一个激活的标签页的名称
                var previousTab = $(e.relatedTarget).text();
                $(".active-tab span").html(activeTab);
                $(".previous-tab span").html(previousTab);
                switch (activeTab){
                    case "学生登录":
                        stName.focus();
                        //st();
                        break;
                    case "教工登录":
                        //自动聚焦
                        teName.focus();
                        //te();
                        break;
                    case "督评中心登录":
                        leName.focus();
                        break;
                }
            });
        });





        //封装 登录函数
        $scope.login =function(a,b){
            var userId =a;
            var userPsd = b;
            //console.log(userPsd)
            if(a==""|| a==undefined){
                msg("#result-wrong","wrong","用户登录名不能为空，请输入");
                stName.focus();
            }else if(isNaN(a)){
                msg("#result-wrong","wrong","用户登录名仅为数字的组合，请重新输入");
                stName.select();
            }
            else if(b==""|| b==undefined){
                msg("#result-wrong","wrong","用户密码不能为空，请输入");
                stName.focus();
            }else{
                $http({
                    method:'post',
                    //url:'/login',
                    url:'/api/UserLoginController/login',
                    params:{
                        'username':userId,
                        'password':userPsd
                    }
                }).success(function(data){
                    //console.log("登录成功，用户的详细信息为");
                    console.log(data);
                    if(data ==""){
                        msg("#result-wrong","wrong","用户不存在或者密码错误，请检查重新输入");
                        stName.focus();
                        return;
                    }else{
                        $state.go('app.home',{UserId:data.user_id,roleId:data.role.id});
                    }

                })
            }
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

        }

    }])
    .controller("headerCtrl",function($scope,$state,$http,$stateParams,$rootScope,$injector){
        $scope.goLogin = function(){
            $state.go("login",{},{reload:true});
        };
        $scope.$on("child",function(event,data){
            console.log("这是header");
            console.log(data);
        });
        var user;//全局变量 用户的所有信息
        //console.log($stateParams.UserName);
        //由 LoginCtrl 传递过来的参数  可以用于全局的数据绑定
        $rootScope.userxxx=$stateParams.UserId;
        //    根据user_id查询到  权限
        //    根据权限查 function（目录）
        //    $http({
        //        method:'post',
        //        url:'/api/UserLoginController/getUserById',
        //        params:{
        //            'userId':$stateParams.UserId
        //        }
        //    }).success(function(data){
        //            console.log("当前用户权限id"+data.role.id);
        //            var role = data.role.id;
        //通过获取得来的 权限id  获得目录
        $http({
            method:'post',
            url:'/api/UserMenuController/getMenu',
            params:{
                'userId':$stateParams.UserId
            }
        }).success(function(data){
            user = data;
            //console.log("获得用户权限成功");
            //console.log(data);
            $scope.userId = data.userId;
            $scope.userRole = data.roleName;
            $scope.menus = data.menus;
            $scope.roleId = data.roleId;
        });
        //}
        //)
        //修改密码  函数
        function changePsd(userId,newPassword){
            $http({
                method:'post',
                url:'/api/UserMenuController/changePassword',
                params:{
                    'userId':userId,
                    'newPassword':newPassword
                }
            }).success(function(data){
                console.log("修改密码成功");
                $state.go('login');
                //$state.go("login", {}, { reload: true});
                //    跳转到 登录界面
            });
        }
        //清空 弹框 函数
        function clear(a,b,c){
            //$("#password").val("");
            //$("#mpassword").val("");
            //$("#lpassword").val("");
            $(a).val("");
            $(b).val("");
            $(c).val("");
        }
        //关闭  修改密码 弹框
        $scope.closePsd=function(){
            //console.log("清空 弹框内容");
            clear("#password","#mpassword","#lpassword");
            $("#psdMsg").removeClass("psdmsg-wrong","psdmsg-right");
            $("#psdMsg").html("请输入");
            //$("#myModal").hide();
        };
        //修改密码  操作
        $scope.psdSave = function(){
            //console.log(user);
            //console.log($("#psdMsg").attr('class'));
            var currentPsd = $("#password").val();
            var newPsd = $("#mpassword").val();
            var newPsdAgain = $("#lpassword").val();
            var psdMsg = $("#psdMsg");
            //psdMsg.html('点击了修改密码按钮');
            //console.log("点击了修改密码按钮");
            console.log(currentPsd);
            console.log(newPsd);
            console.log(newPsdAgain);
            if(user.psd =="" || newPsd=="" ||newPsdAgain==""){
                psdMsg.removeClass("psdmsg-right").addClass("psdmsg-wrong");
                psdMsg.html('输入不能为空');
            }else if(user.psd != currentPsd ){
                psdMsg.removeClass("psdmsg-right").addClass("psdmsg-wrong");
                psdMsg.html('旧密码输入不正确');
                clear("#password");
                $("#password").focus();
            }else if(newPsd !==newPsdAgain){
                psdMsg.removeClass("psdmsg-right").addClass("psdmsg-wrong");
                psdMsg.html('新密码两次输入不一致');
                clear("#mpassword","#lpassword");
                $("#mpassword").focus();
            }else{
                psdMsg.removeClass("psdmsg-wrong").addClass("psdmsg-right");
                psdMsg.html('新密码验证成功，稍后跳转至登录界面，请重新登录');
                //设置定时器
                setTimeout(function(){
                    $("#myModal").modal("hide");
                    //模态框 隐藏后 遮罩层 还在 在html文件中 出现<div class="modal-backdrop fade in"></div>
                    //解决办法是 需要 调用一下刷新函数
                    //全部清除 方法一 ：在控制器中 加入$injector 服务 ，然后调用
                    //方法二 在路由配置文件中 加入监听 路由状态更改 则 刷新一次
                    //$injector.get('$templateCache').removeAll();
                    changePsd(user.userId,newPsd);
                },3000);
                //setTimeout(changePsd(user.userId,newPsd),6000);
                //time时间过后在运行

            }
            //changePassword()
            //    当前密码 currentPsd
            //    新密码 newPsd
            //    再次确认新密码 newPsdAgain
        }
    })
    .controller("homeCtrl",function($scope,$state,$http,$stateParams){

        function $(id){return document.getElementById(id);}
        //free();
        function free(){
            var leader =0,target = -7200;
            var timer =null;
            timer = setInterval(imgPlay,5);
            function imgPlay(){

                target--;
                target <= -7200 ?target = 0 :target;
                leader = leader+(target-leader)/10;
                $("scroll").children[0].style.left = leader +"px";
            }
            $("scroll").onmouseout = function(){
                timer = setInterval(imgPlay,5);
            }
            $("scroll").onmouseover = function(){
                clearInterval(timer);
            }
        }
        var jd = $("jd");
        var ul = jd.children[0].children[0];
        var ol = jd.children[0].children[1];
        var liList = ol.children;
        // 小圆点  样式更改  排他思想
        for(var i=0,l=liList.length;i<l;i++){
            // 索引号
            liList[i].index = i;
            liList[i].onmouseover = function(){
                for(var j=0,le=liList.length;j<le;j++){
                    liList[j].className = "";
                }
                this.className = "current";
                target = this.index * (-1440);
            }

        }
        var leader =0, target = 0;
        setInterval(function(){
            leader = leader +(target - leader) /10;
            ul.style.left = leader +"px";
        },30);


        //console.log($stateParams.UserName);
        var userId=$stateParams.UserId;
        console.log("home");
        console.log(userId);
        $scope.$emit("USER",{data:$stateParams.UserId});
        $scope.$on("child",function(event,data){
            console.log("这是home");
            console.log(data);
        })

    })
    .controller("infoCtrl",function($scope,$http,$stateParams,$rootScope,$state){
    //    控制器之间 传递的参数  为  用户id 用户的权限
    //    根据权限 查不同角色的表
    //    显示具体的 角色的信息
    //    console.log("这是个人信息");
    //    console.log($stateParams.UserId);
        //问题：为什么 同样都是appCtrl的子控制器 只有headerCtrl能够接受到 父控制器的广播
        //页面加载之后  在F12中 ui-view 那是没有controller的
        //是不是因为这个原因呢 毕竟在该结果中 只有headerCtrl是写死在页面中的
        //要怎么解决呢？？？？？
        $rootScope.$on("child",function(event,data){
            console.log(data);
        });
    //    由于 每一个目录 都会用到 userId  所以用了一个 取巧的办法 直接在路径渲染后面加了 userId

    //    获得权限 id
    //    var roleId = $stateParams.roleId;
    //    console.log(typeof roleId);//String
        var roleId = parseInt($stateParams.roleId);
        console.log(typeof roleId);//nummber
        info(roleId);

    //    根据 权限 加载相应的函数
        function info(role){
            switch (role){
                case 1:getStudentJson();
                    //    视图 全部消失
                    hideAll("#teacher","#leader","#admin");
                    //更改 学生个人信息 并保存
                    $scope.save= function(){
                        changeInfo_st();
                    };
                    break;
                case 2:
                    getTeacherJson();
                    hideAll("#student","#leader","#admin");
                    $scope.save= function(){
                        changeInfo_te();
                    };
                    break;
                case 3:getLeaderJson();
                    //    视图 全部消失
                    hideAll("#student","#teacher","#admin");
                    //更改 督导个人信息 并保存
                    $scope.save= function(){
                        changeInfo_le();
                    };
                    break;
                case 4:
                    getAdminJson();
                    hideAll("#student","#teacher","#leader");
                    //更改 学生个人信息 并保存
                    $scope.save= function(){
                        changeInfo_ad();
                    };
                    break;
            }
        }
    //函数 封装
    //    全部消失
        function hideAll(a,b,c,d){
            $(a).hide();
            $(b).hide();
            $(c).hide();
            $(d).hide();
        }
        //    获得  学生 个人信息
        function getStudentJson(){
            $http({
                method:'post',
                url:'/api/UserInfo/studentInfo',
                params:{
                    'userId':$stateParams.UserId
                }
            }).success(function(data){
                //console.log(data);
                $scope.user = data;
            });
            $scope.goSub=function(){
                $state.go("app.information.edit-do");
            };
        }
        //更改学生信息
        function changeInfo_st(){
            $http({
                method:"post",
                url:"/api/UserInfo/changeInfo_st",
                params:{
                    "userId":$stateParams.UserId,
                    "email":$scope.user.email,
                    'dz':$scope.user.dz,
                    'phone':$scope.user.phone
                }
            }).success(function(data){
                //console.log("保存成功");
                //console.log(data);
                //跳转页面 并且 刷新页面 显示新数据
                //跳转到该路由下 会再一次 调用 该控制器 所以 会刷新一次
                //不用自己手动调用一次函数 刷新页面了
                $state.go("app.information.edit");
            })
        }

        //获得管理员个人信息
        function getAdminJson(){
            $http({
                method:'post',
                url:'/api/UserInfo/getAdminJson',
                params:{
                    'userId':$stateParams.UserId
                }
            }).success(function(data){
                //console.log(data);
                $scope.user = data;
            });
            $scope.goSub=function(){
                $state.go("app.information.edit-do");
            };
        }
        //更改 管理员信息
        function changeInfo_ad(){
            $http({
                method:"post",
                url:"/api/UserInfo/changeInfo_ad",
                params:{
                    "userId":$stateParams.UserId,
                    "email":$scope.user.email,
                    'dz':$scope.user.dz,
                    'phone':$scope.user.phone
                }
            }).success(function(data){
                //console.log("保存成功");
                //console.log(data);
                //跳转页面 并且 刷新页面 显示新数据
                //跳转到该路由下 会再一次 调用 该控制器 所以 会刷新一次
                //不用自己手动调用一次函数 刷新页面了
                $state.go("app.information.edit");
            })
        }


        //获得 教师 个人信息
        function getTeacherJson(){
            $http({
                method:'post',
                url:'/api/UserInfo/teacherInfo',
                params:{
                    'userId':$stateParams.UserId
                }
            }).success(function(data){
                console.log("获得老师的所有信息");
                console.log(data);
                $scope.user = data;
            });
        }

        //更改教师信息
        function changeInfo_te(){
            $http({
                method:"post",
                url:"/api/UserInfo/changeInfo_te",
                params:{
                    "userId":$stateParams.UserId,
                    "email":$scope.user.email,
                    'dz':$scope.user.dz,
                    'phone':$scope.user.phone
                }
            }).success(function(data){
                //console.log("保存成功");
                //console.log(data);
                //跳转页面 并且 刷新页面 显示新数据
                //跳转到该路由下 会再一次 调用 该控制器 所以 会刷新一次
                //不用自己手动调用一次函数 刷新页面了
                $state.go("app.information.edit");
            })
        }

        //获得 督导 个人信息
        function getLeaderJson(){
            $http({
                method:'post',
                url:'/api/UserInfo/getLeaderJson',
                params:{
                    'userId':$stateParams.UserId
                }
            }).success(function(data){
                console.log(data);
                $scope.user = data;
            });
        }

        //更改 督导个人信息
        function changeInfo_le(){
            $http({
                method:"post",
                url:"/api/UserInfo/changeInfo_le",
                params:{
                    "userId":$stateParams.UserId,
                    "email":$scope.user.email,
                    'dz':$scope.user.dz,
                    'phone':$scope.user.phone
                }
            }).success(function(data){
                //console.log("保存成功");
                //console.log(data);
                //跳转页面 并且 刷新页面 显示新数据
                //跳转到该路由下 会再一次 调用 该控制器 所以 会刷新一次
                //不用自己手动调用一次函数 刷新页面了
                $state.go("app.information.edit");
            })
        }


        $scope.goSub=function(){
            $state.go("app.information.edit-do");
        };
    })
    .controller("PingTeCtrl",function($scope,$http,$stateParams){
    //    根据id 获得 课程list
    //    学生 id 为 $stateParams.UserId
    //    alert("这是 评教页面");
        //console.log(typeof $stateParams.UserId);
        $scope.$on("status-te",function(event){
            //alert("状态要更改了");
            getCoursesListByStudent();
        });
        getCoursesListByStudent();
function getCoursesListByStudent(){
    $http({
        method:"post",
        url:"/api/getListController/getCoursesListByStudent",
        params:{
            "stId":$stateParams.UserId
        }
    }).success(function(data){
        console.log("获取选修课程list");
        console.log(data);
        $scope.data = data;
    });
}






    })
    .controller("PingTeDetailCtrl",function($scope,$http,$stateParams){
//        课程id 为 $stateParams.courseId
//        根据 课程id 查到课程表数据
//        学生id为$stateParams.UserId
//        老师id为$stateParams.TeId 或者是 下面http服务请求的数据里 data.te_id
        console.log($stateParams.UserId);
        //查找 课程信息 主要是得到课程name
        //查的是 课程表
        $http({
            method:"post",
            url:"/api/getListController/getCourseJson",
            params:{
                "co_id":$stateParams.courseId
            }
        }).success(function(data){
            //console.log("获取数据成功");
            console.log(data);
            $scope.courseName = data.name;
            //$scope.data = data;
        });
//        进入界面首先查找的是 学生 课程 link表 查看该课改学生是否已经评价过
//        若状态已评 则显示的是库表的数据 并且不可在编辑
//        查的是 结果表 查的是 该生 该课的该条结果
//        若状态为未评 则评教支持
        $http({
            method:"post",
            url:"/api/getListController/GetStatusNow",
            params:{
                "stId":$stateParams.UserId,
                "coId":$stateParams.courseId
            }
        }).success(function(data){
            console.log("获取该生的该课程的状态");
            console.log(data);
            var status =data.status;
            //console.log(status)
            switch (status){
                case 1:
                    //alert("状态为 已评");
                    $("#fat-btn").html("提交成功，无法再次编辑");
                    $("#fat-btn").removeClass("btn-primary btn-danger").addClass("disabled");
                    //调用 courseResult表中 方法
                    //传入 courseid 获得数据

                    $http({
                        method:"post",
                        url:"/api/getListController/getResult",
                        params:{
                            "stId":$stateParams.UserId,
                            "coId":$stateParams.courseId
                        }
                    }).success(function(data){
                        console.log("该生该课程的结果");
                        console.log(data);
                        $scope.p11 = data.p1;
                        $scope.p22 = data.p2;
                        $scope.p33 = data.p3;
                        $scope.p44 = data.p4;
                        $scope.p55 = data.p5;
                        $scope.p66 = data.p6;
                        $scope.p77 = data.p7;
                        $scope.p88 = data.p8;
                        $scope.p99 = data.p9;
                        $scope.p100 = data.p10;
                        $scope.text = data.content;
                    });
                    break;
                case 0:
                    $("#fat-btn").html("提交评学表单");
                    $("#fat-btn").removeClass("btn-primary btn-danger disabled").addClass("btn-primary");
                    //alert("状态为 未评");
                    break;
            }
        });
//    教师 id 为 $stateParams.TeId
        //    根据 教师id 返回教师 的某些信息
        //    调用的是  教师module中的方法
        //console.log($stateParams.courseId);
        $http({
            method:"post",
            url:"/api/UserInfo/getTeacherJson",
            params:{
                "userId":$stateParams.TeId
            }
        }).success(function(data){
            //console.log("获取数据成功");
            console.log(data);
            $scope.teacher = data;
            //$scope.data = data;
        });
    //    评教页面 选择项目
        $scope.pingjia = [{
            value : 10,
            name : "优秀"
        },{
            value : 8,
            name : "良好"
        },{
            value : 6,
            name : "中等"
        },{
            value : 2,
            name : "差强人意"
        }];
    //   评教 获得 分数
        var value1,value2,value3,value4,value5,value6,value7,value8,value9,value10;
        $scope.getValue1 = function(val){
            value1= val;
        };$scope.getValue2 = function(val){
            value2= val;
        };$scope.getValue3 = function(val){
            value3= val;
        };$scope.getValue4 = function(val){
            value4= val;
        };$scope.getValue5 = function(val){
            value5= val;
        };$scope.getValue6 = function(val){
            value6= val;
        };$scope.getValue7 = function(val){
            value7= val;
        };$scope.getValue8 = function(val){
            value8= val;
        };$scope.getValue9 = function(val){
            value9= val;
        };$scope.getValue10 = function(val){
            value10= val;
        };
        function SetP(){
            $http({
                method: "post",
                url: "/api/getListController/SetP",
                params: {
                    "stId": $stateParams.UserId
                }
            }).success(function(data){
                console.log("学生的整体状态查询更改完毕");
            })
        }
        function setPercent(){
            $http({
                method: "post",
                url: "/api/getListController/setPercent"
            }).success(function(data){
                console.log("评教率已更改，请查看 班级学院link表中的percent字段是否变化");
            })
        }
        //评教完毕后  直接更改 结果表中总分 以及老师学院link 表中的总分
        function scoreSave( score){
            $http({
                method:"post",
                url:"/api/InfoSave/scoreSave",
                params:{
                    "teId":$stateParams.TeId,
                    "score":score
                }
            }).success(function(data){
                console.log("成功将教师学院link中的 score字段 更新");
            })
        }

        //查询 每一项 分数 用于计算总分数
        function TotalScoreSave(){
            $http({
                method:"post",
                url:"/api/getListController/getTeacherResultListByCoId",
                params:{
                    "coId":$stateParams.courseId
                }
            })
                .success(function(data) {
                console.log("获取选修该课的 已经评教学生的 评教结果");
                console.log(data);

                var totalScore;//记录总分
                var num = 0;//暂时记录总分
                if (data.length < 3) {
                    for (var i = 0; i < data.length; i++) {
                        num = num + data[i].score;
                    }
                    totalScore = num / (data.length);
                } else {
                    //    去掉最高分去掉最低分 算平均分
                    //    得到最终的 totalScore
                    for (var i = 1; i < data.length - 1; i++) {
                        num = num + data[i].score;
                    }
                    totalScore = num / (data.length - 2);
                }
                scoreSave(totalScore);
            });
        }

        $scope.submit = function(){
            //页面验证 值不能为空 出现弹框
            //获得 对老师的评语
           console.log($scope.text) ;
            console.log(value1,value2);
        //    数据保存到数据库表中 当学生点击提交之后
        //    课程列表的 状态变为 已评状态 评教表单 展示的是 学生评教的数据 并且 不再支持编辑功能
        //    只要将 提交按钮的状态设置为不可点击即可
        //    disabled
        //    $("#fat-btn").attr("disabled",true);
            if(value1==undefined ||value2 == undefined ||value3 == undefined ||value4 == undefined ||value5 == undefined
                ||value6 == undefined ||value7== undefined ||value8 == undefined ||value8 == undefined||value10 == undefined){
                $("#fat-btn").html("表单中含有未填项，请检查填写后再次提交");
                $("#fat-btn").removeClass("btn-primary").addClass("btn-danger");
            }else{
                //开始调用接口 用于 将评价报存到 教师结果表中
                $http({
                    method: "post",
                    url: "/api/InfoSave/teacherResultSet",
                    params: {
                        "coId": $stateParams.courseId,
                        "stId": $stateParams.UserId,
                        "p1": value1,
                        "p2": value2,
                        "p3": value3,
                        "p4": value4,
                        "p5": value5,
                        "p6": value6,
                        "p7": value7,
                        "p8": value8,
                        "p9": value9,
                        "p10": value10,
                        "content": $scope.text
                    }
                }).success(function(data){
                    console.log("评教成功");
                    //需要 改变一下 学生 课程link表中的状态
                    //然后通知一下 父控制器 刷新一下界面的 课程旁边的状态展示
                    TotalScoreSave();
                    $http({
                        method: "post",
                        url: "/api/InfoSave/statusSave",
                        params: {
                            "coId": $stateParams.courseId,
                            "stId": $stateParams.UserId
                        }
                    }).success(function(data){
                        //传递消息
                        console.log("本课程状态更改完毕");
                        //根据 该学生 选修的所有课程的状态 更改该学生的评教状态
                        //更改数据库表 学生 班级link表
                        //调用接口 /api/getListController/SetP
                        SetP();
                        //若 学生 状态为 评教结束 则应相应更改 评教率
                        //调用接口 /api/getListController/setPercent
                        //先计算 在更新库表 班级 学院link表中的 percent字段
                        setPercent();
                        console.log(data);
                        $scope.$emit('status-te');
                    });
                    $("#fat-btn").html("提交成功，无法再次编辑");
                    $("#fat-btn").removeClass("btn-primary btn-danger").addClass("disabled");
                });

            }

        }

    })
    .controller("PingStCtrl",function($scope,$http,$stateParams){
        $scope.$on("status",function(event){
            //alert("状态要更改了");
            getCoursesListByTeacher();
        });
        //教师 id 为 $stateParams.UserId
        console.log($stateParams.UserId);
    //    根据 教师 id 查 课程表 返回 课程 信息
        getCoursesListByTeacher();
        function getCoursesListByTeacher(){
            $http({
                method:"post",
                url:"/api/getListController/getCoursesListByTeacher",
                params:{
                    "userId":$stateParams.UserId
                }
            }).success(function(data){
                console.log("获取数据成功");
                console.log(data);
                $scope.data = data;
            });
        }
    })
    .controller("studentListCtrl",function($scope,$http,$stateParams){
        $scope.courseId =$stateParams.courseId;
        //    根据 课程 id 查 课程学生link表 返回 学生 信息
        $http({
            method:"post",
            url:"/api/getListController/getStudentListByCourse",
            params:{
                "coId":$stateParams.courseId
            }
        }).success(function(data){
            //console.log("获取数据成功");
            console.log(data);
            $scope.data = data;
        });
    })
    .controller("PingStDetailCtrl",function($scope,$http,$stateParams){
        //console.log($stateParams.stId);
    //    课程id 为$stateParams.courseId
    //    学生id 为$stateParams.stId
    //    通过学生id 查找学生信息 调用 学生module中的方法
    //    $http({
    //        method:"post",
    //        url:"/api/UserInfo/getStudentJson",
    //        params:{
    //            "userId":$stateParams.stId
    //        }
    //    }).success(function(data){
    //        console.log("学生信息");
    //        console.log(data);
    //        $scope.student = data;
    //    })

    //    根据 课程id 查找课程表 返回 课程信息
        $http({
            method:"post",
            url:"/api/getListController/getCourseJson",
            params:{
                "co_id":$stateParams.courseId
            }
        }).success(function(data){
            console.log("课程信息");
            console.log(data);
            $scope.co = data;
            var status =data.status;
            //console.log(status)
            switch (status){
                case 1:
                    //alert("状态为 已评");
                    $("#fat-btn").html("提交成功，无法再次编辑");
                    $("#fat-btn").removeClass("btn-primary btn-danger").addClass("disabled");
                    //调用 courseResult表中 方法
                    //传入 courseid 获得数据
                    $http({
                        method:"post",
                        url:"/api/getListController/getCourseResultByCoId",
                        params:{
                            "cooId":$stateParams.courseId
                        }
                    }).success(function(data){
                        console.log(data);
                        $scope.p11 = data.p1;
                        $scope.p22 = data.p2;
                        $scope.p33 = data.p3;
                        $scope.p44 = data.p4;
                        $scope.p55 = data.p5;
                        $scope.p66 = data.p6;
                        $scope.p77 = data.p7;
                        $scope.p88 = data.p8;
                        $scope.p99 = data.p9;
                        $scope.p100 = data.p10;
                        $scope.text = data.content;
                    });
                    break;
                case 0:
                    $("#fat-btn").html("提交评学表单");
                    $("#fat-btn").removeClass("btn-primary btn-danger disabled").addClass("btn-primary");
                    //alert("状态为 未评");
                    break;
            }

        });
        //if course.status =0
        // 调用 更改库表 方法
        //else 调用的是 查询 courseResult表 数据方法


        //评教页面 选择项目
        $scope.pingjia = [{
            value : 5,
            name : "优秀"
        },{
            value : 4,
            name : "良好"
        },{
            value : 3,
            name : "中等"
        },{
            value : 1,
            name : "差强人意"
        }];
        //   评教 获得 分数
        var value1,value2,value3,value4,value5,value6,value7,value8,value9,value10;
        $scope.getValue1 = function(val){
            value1= val;
        };$scope.getValue2 = function(val){
            value2= val;
        };$scope.getValue3 = function(val){
            value3= val;
        };$scope.getValue4 = function(val){
            value4= val;
        };$scope.getValue5 = function(val){
            value5= val;
        };$scope.getValue6 = function(val){
            value6= val;
        };$scope.getValue7 = function(val){
            value7= val;
        };$scope.getValue8 = function(val){
            value8= val;
        };$scope.getValue9 = function(val){
            value9= val;
        };$scope.getValue10 = function(val){
            value10= val;
        };
        $scope.submit = function(){
            //页面验证 值不能为空 出现弹框
            //获得 对老师的评语
            console.log($scope.text) ;
            console.log(value1,value2);
            //    数据保存到数据库表中 当学生点击提交之后
            //    课程列表的 状态变为 已评状态 评教表单 展示的是 学生评教的数据 并且 不再支持编辑功能
            //    只要将 提交按钮的状态设置为不可点击即可
            //    disabled
            //    $("#fat-btn").attr("disabled",true);
            if(value1==undefined ||value2 == undefined ||value3 == undefined ||value4 == undefined ||value5 == undefined
                ||value6 == undefined ||value7== undefined ||value8 == undefined ||value8 == undefined||value10 == undefined){
                $("#fat-btn").html("表单中含有未填项，请检查填写后再次提交");
                $("#fat-btn").removeClass("btn-primary").addClass("btn-danger");
            }else{
                //请求 方法 保存
                //调用的是 courseResult表中的SetResult方法
                //传递的参数含有 该课程的id 以及 评价项
                $http({
                    method:"post",
                    url:"/api/InfoSave/CourseResultSet",
                    params:{
                        "coId":$stateParams.courseId,
                        "p1":value1,
                        "p2":value2,
                        "p3":value3,
                        "p4":value4,
                        "p5":value5,
                        "p6":value6,
                        "p7":value7,
                        "p8":value8,
                        "p9":value9,
                        "p10":value10,
                        "content":$scope.text
                    }
                }).success(function(data){
                    //成功之后 调用一下 course表的方法 变更状态 并且消息传递给 父级控制器 刷新一下 页面
                    console.log(data);
                    $http({
                            method: "post",
                            url: "/api/InfoSave/statusChange",
                            params: {
                                "coId": $stateParams.courseId
                            }
                        }).success(function(data){
                        //传递消息
                        console.log("状态更改完毕");
                        console.log(data);
                        $scope.$emit('status');
                    })
                });
                $("#fat-btn").html("提交成功，无法再次编辑");
                $("#fat-btn").removeClass("btn-primary btn-danger").addClass("disabled");
            }

        }
    })
    .controller("stResultCtrl",function($scope,$http,$stateParams){
    //    根据 学生id 查找 学生 课程link表格 查出 本学期的课程
        $http({
            method:"post",
            url:"/api/getListController/getCoursesListByStudent",
            params:{
                "stId":$stateParams.UserId
            }
        }).success(function(data){
            console.log("本学期课程list");
            console.log(data);
            $scope.data = data;
        });

    })
    .controller("stReDetailCtrl",function($scope,$http,$stateParams){
    //    根据课程id 查找的是 课程result表 中的数据
        //评教页面 选择项目
        $scope.pingjia = [{
            value : 5,
            name : "优秀"
        },{
            value : 4,
            name : "良好"
        },{
            value : 3,
            name : "中等"
        },{
            value : 1,
            name : "差强人意"
        }];

        $http({
            method:"post",
            url:"/api/getListController/getCourseJson",
            params:{
                "co_id":$stateParams.courseId
            }
        }).success(function(data){
            console.log("课程信息");
            console.log(data);
            $scope.co = data;
            var status = data.status;
            if(status == 0){
                $scope.p11 = 5;
                $scope.p22 = 5;
                $scope.p33 = 5;
                $scope.p44 = 5;
                $scope.p55 = 5;
                $scope.p66 = 5;
                $scope.p77 = 5;
                $scope.p88 = 5;
                $scope.p99 = 5;
                $scope.p100 = 5;
                $scope.text = "老师还没有评论哦";
            }else{
                $http({
                    method:"post",
                    url:"/api/getListController/getCourseResultByCoId",
                    params:{
                        "cooId":$stateParams.courseId
                    }
                }).success(function(data){
                    console.log(data);
                    $scope.p11 = data.p1;
                    $scope.p22 = data.p2;
                    $scope.p33 = data.p3;
                    $scope.p44 = data.p4;
                    $scope.p55 = data.p5;
                    $scope.p66 = data.p6;
                    $scope.p77 = data.p7;
                    $scope.p88 = data.p8;
                    $scope.p99 = data.p9;
                    $scope.p100 = data.p10;
                    $scope.text = data.content;
                });
            }
        });

    })
    .controller("teResultCtrl",function($scope,$http,$stateParams){

        getCoursesListByTeacher();
        function getCoursesListByTeacher(){
            $http({
                method:"post",
                url:"/api/getListController/getCoursesListByTeacher",
                params:{
                    "userId":$stateParams.UserId
                }
            }).success(function(data){
                console.log("获取数据成功");
                console.log(data);
                $scope.data = data;
            });
        }
    })
    .controller("teReDetailCtrl",function($scope,$http,$stateParams){
        request();
        $scope.pages=[5,10,15,20];
        //换页函数
        //    根据 课程id 查找 评教结果表 查出 该课程下的所有学生的记录 list
        //    根据这些 将学生的评语list展示在界面中
        function request(){
            //    查询数据 输入 pagesize
            $http({
                method:"post",
                url:"/api/getListController/listTeResult",
                params:{
                    "coId":$stateParams.courseId,
                    'p':$scope.currentPage,
                    'ps':$scope.pageSize
                }
            }).success(function(data){
                console.log("获取选修该课的 已经评教学生的 评语列表 分页展示");
                console.log(data);
                $scope.totalItems = data.count;
                $scope.list = data.rows;
                $scope.page = data.page;
                $scope.pageSize = data.pageSize;
            });
        }

        $scope.pageSizeChange=function(pageSize){
            $scope.pageSize = pageSize;
            request();
        };
        $scope.pageChange=function(currentPage){
            $scope.currentPage=currentPage;
            request();
        };

    //    课程id 为$stateParams.courseId
    //    教师 id为$stateParams.UserId
    //    根据 课程id 查课程表 查到课程信息 name
        $http({
            method:"post",
            url:"/api/getListController/getCourseJson",
            params:{
                "co_id":$stateParams.courseId
            }
        }).success(function(data){
            //console.log("获取数据成功");
            console.log(data);
            $scope.courseName = data.name;
            //$scope.data = data;
        });
    //    根据 课程id 查找 教师结果表 查出所有学生 的评分项 并通过计算 展示在 界面中
        $http({
            method:"post",
            url:"/api/getListController/getTeacherResultListByCoId",
            params:{
                "coId":$stateParams.courseId
            }
        }).success(function(data){
            console.log("获取选修该课的 已经评教学生的 评教结果");
            console.log(data);

            var scoreList = [];//记录 每一个学生的 打分情况
            var totalScore;//记录总分
            var num =0;//暂时记录总分
            var p1= 0,p2= 0,p3= 0,p4= 0,p5= 0,p6= 0,p7= 0,p8= 0,p9= 0,p10=0;//记录每一项总分
            var ListP1= [],ListP2= [],ListP3= [],ListP4= [],ListP5= [],ListP6= [],ListP7= [],ListP8= [],ListP9= [],ListP10= [];//记录每一项 的所有学生的 分值list
            var avgP1,avgP2,avgP3,avgP4,avgP5,avgP6,avgP7,avgP8,avgP9,avgP10;//记录每一项 平均分


            console.log("得分 数组为"+scoreList);
            for(var i= 0,l=data.length;i<l;i++){
                ListP1.push(data[i].p1);
                ListP2.push(data[i].p2);
                ListP3.push(data[i].p3);
                ListP4.push(data[i].p4);
                ListP5.push(data[i].p5);
                ListP6.push(data[i].p6);
                ListP7.push(data[i].p7);
                ListP8.push(data[i].p8);
                ListP9.push(data[i].p9);
                ListP10.push(data[i].p10);
            }
            //console.log(list.sort());
            //console.log("平" +ListP6.sort()[1]);//降序排列 10 10 8
            if(data.length<3){
                for(var i=0;i<data.length;i++){
                    //console.log(data[i].score);
                    num = num+data[i].score;
                    p1=p1+data[i].p1;
                    p2=p2+data[i].p2;
                    p3=p3+data[i].p3;
                    p4=p4+data[i].p4;
                    p5=p5+data[i].p5;
                    p6=p6+data[i].p6;
                    p7=p7+data[i].p7;
                    p8=p8+data[i].p8;
                    p9=p9+data[i].p9;
                    p10=p10+data[i].p10;

                    //scoreList.push(data[i].score);
                }
                avgP1 = p1/(data.length);
                avgP2 = p2/(data.length);
                avgP3 = p3/(data.length);
                avgP4 = p4/(data.length);
                avgP5 = p5/(data.length);
                avgP6 = p6/(data.length);
                avgP7 = p7/(data.length);
                avgP8 = p8/(data.length);
                avgP9 = p9/(data.length);
                avgP10 = p10/(data.length);
                totalScore = num/(data.length);
                $scope.total = totalScore.toFixed(2);
            }else{
                //    去掉最高分去掉最低分 算平均分
                //    得到最终的 totalScore
                for(var i=1;i<data.length-1;i++){
                    //console.log(data[i].score);
                    num = num+data[i].score;
                    p1=p1+ListP1.sort()[i];
                    p2=p2+ListP2.sort()[i];
                    p3=p3+ListP3.sort()[i];
                    p4=p4+ListP4.sort()[i];
                    p5=p5+ListP5.sort()[i];
                    p6=p6+ListP6.sort()[i];
                    p7=p7+ListP7.sort()[i];
                    p8=p8+ListP8.sort()[i];
                    p9=p9+ListP9.sort()[i];
                    p10=p10+ListP10.sort()[i];
                    scoreList.push(data[i].score);
                }
                totalScore = num/(data.length-2);
                avgP1 = p1/(data.length-2);
                avgP2 = p2/(data.length-2);
                avgP3 = p3/(data.length-2);
                avgP4 = p4/(data.length-2);
                avgP5 = p5/(data.length-2);
                avgP6 = p6/(data.length-2);
                avgP7 = p7/(data.length-2);
                avgP8 = p8/(data.length-2);
                avgP9 = p9/(data.length-2);
                avgP10 = p10/(data.length-2);
                $scope.total = totalScore.toFixed(2);
            }
            var pList=[];//用于存储 最终 每项 结果
            pList = [avgP1.toFixed(2),avgP2.toFixed(2),avgP3.toFixed(2),avgP4.toFixed(2),avgP5.toFixed(2),avgP6.toFixed(2),avgP7.toFixed(2),avgP8.toFixed(2),avgP9.toFixed(2),avgP10.toFixed(2)];

            //界面所需要的评价指标
            var content;
            $scope.content = content=[
                {
                    id:1,
                    text:"是否科学组织教案，教学内容熟悉，授课认真，精神饱满有激情 ",
                    s:10,
                    //value:null,
                    //nn:null
                },
                {
                    id:2,
                    text:"是否爱岗敬业，仪表端庄；完成规定教学课时；尊重学生人格，严格要求，有问必答，辅导答疑及时、耐心，经常与学生交流",
                    s:10,
                    //value:null
                },
                {
                    id:3,
                    text:"信息量适度，吸收学科新成果，反映学科前沿状况，充实更新教学内容 ",
                    s:10,
                    //value:null
                },
                {
                    id:4,
                    text:"基本概念准确清晰，逻辑结构合理，阐述科学严谨，观点正确，条理清晰，系统性强 ",
                    s:10,
                    //value:null
                },
                {
                    id:5,
                    text:"善于启发诱导，教学方法灵活多样， 师生互动活跃，启 发学生思维，学生分析问题、解决问题能力、学习能力得到提高 ",
                    s:10,
                    //value:null
                },
                {
                    id:6,
                    text:"教学手段运用恰当，合理运用现代化教育技术手段  ",
                    s:10,
                    //value:null
                }, {
                    id:7,
                    text:"重点突出，难度、深度适宜，理论联系实际 ",
                    s:10,
                    //value:null
                },
                {
                    id:8,
                    text:"普通话标准，语言生动流畅，深入浅出，感染力强  ",
                    s:10,
                    //value:null
                },
                {
                    id:9,
                    text:"学生能较好地理解并掌握主要教学内容，学生上课积极性高，教学秩序好，课堂气氛活跃  ",
                    s:10,
                    //value:null
                }, {
                    id:10,
                    text:"是否教学进度节奏适中，能有效利用上课时间，上课效率高",
                    s:10,
                    //value:null
                }
            ];
            for(var i= 0,l=content.length;i<l;i++){
                content[i].value=pList[i];
                //偏差
                content[i].nn=((10-pList[i])/10*100).toFixed(2)+"%";
            }
            console.log(content);
        });
    })
    .controller("CountCtrl",function($scope,$http,$rootScope){
        //动态加载id
        //查表  班级学院表 查出 学院id
        //SELECT college_id FROM `classroomcollegelink` GROUP BY college_id;

            var ids =[];//存储 处理后的id 列表
            var myCharts=[];//存储 处理好的 charts 列表
            $http({
                method:"post",
                url:"/api/getListController/getCollegeIdGroup"
            }).success(function(data){
                for(var i= 0,l=data.length;i<l;i++){
                    myCharts.push("myCharts"+data[i]);
                    ids.push("countEchart" + data[i]);
                }
                $scope.idLibs =ids;
                console.log( $scope.idLibs);
            });


        if (echarts.version == '3.2.2') {
            $rootScope.echarts33 = echarts;
        }



        //var colors = ['#88cffa', '#d36ed4'];
        $http({
            method:"post",
            url:"/api/getListController/GetPercentGroupByCollege"
        }).success(function(data){
            console.log("评教率");
            console.log(data);

            //将数据 处理成 每个属性一个 数组
            var effectRow1 =[];
            var map = {};
            for(i=0;i<data.length;i++){
                effectRow1["all"]=effectRow1.push(data[i]);
            }
            for(i=0;i<effectRow1.length;i++){

//                           //把相同值的属性取出来放进key中
                var key =effectRow1[i].college;
                //console.log(key);
                map[key] = map[key] || (map[key] = []);
                //把json对象进行分组处理，属性值相同的则放进一起，此时map[key]是数组
                map[key].push(effectRow1[i]);
                map[key].name=effectRow1[i].collegeName ;
                console.log(map[key].name);
                //$scope.map

            }
            console.log(map);
            for(var name in map){
                var classRoomName=[];
                var percent =[];
                var idLibs = [];
                for(var i = 0;i < map[name].length; i++) {
                    classRoomName.push(map[name][i].classRoomName);
                    var p = (map[name][i].percent * 100).toFixed(2);
                    percent.push(p);
                    //评教率
                    //console.log("评价率");
                    //console.log(p);

                    //    将数据  绑定给 E charts图表
                    //console.log(map[name][i].college);

                    idLibs.push("countEchart" + map[name][i].college);
                }
                for(var i = 0;i < map[name].length; i++) {
                    myCharts[i]   = $rootScope.echarts33.init(document.getElementById(idLibs[i]));
                    console.log(map[name].name+"给定 id为"+idLibs[i]+"的评教率为"+p+"init图标的id为"+myCharts[i]);

                    var option = {
                        color: ['#3398DB'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        title: {
                            left: 'center',
                            text: map[name].name
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        //下载 区域缩放功能等
                        toolbox: {
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        xAxis: [
                            {
                                type: 'category',
                                name:"班级",
                                data:classRoomName ,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                name:"评教率(%)"
                            }
                        ],
                        series: [
                            {
                                name: '评教率',
                                type: 'bar',
                                barWidth: '60%',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'inside'
                                    }
                                },
                                //data:[10, 52, 200, 334, 390, 330, 220]
                                data:percent
                            }
                        ]
                    };
                    myCharts[i].setOption(option);
                }

            }


            //表格 视图  数据来源
            //将数据 处理成 以学院分组
            //var jobsSortObject = {};
            //for(var i =0; i< data.length; i++){
            //    var job = data[i],
            //        mark = job.college,//mark 是 学院 id
            //        //mark = job.collegeName;//mark 是 collegeName
            //        jobItem = jobsSortObject[mark];
            //    if(jobItem){
            //        jobsSortObject[mark].push(job);
            //
            //    }else{
            //        jobsSortObject[mark] = [job];
            //
            //    }
            //    jobsSortObject[mark].name=job.collegeName ;
            //}
            //$scope.dataHa = jobsSortObject;
            //console.log("这是按 学院id 分组的评教率");
            //console.log(jobsSortObject);


        })
    })
    .controller("rankingCtrl",function($http,$scope,$rootScope){
        var id =[];//存储 处理后的id 列表
        var myCharts=[];//存储 处理好的 charts 列表
        $http({
            method:"post",
            url:"/api/getListController/getCollegeIdGroup"
        }).success(function(data){
            for(var i= 0,l=data.length;i<l;i++){
                id.push("teEchart"+data[i]);
                myCharts.push("myCharts"+data[i]);
            }
            $scope.idLibs =id;
        });
        if (echarts.version == '3.2.2') {
            $rootScope.echarts33 = echarts;
        }
    //查询 教师 学院link 表
    //    输出 所有数据
    //    组织数据 以学院为依据 分组 展示在前端界面
    //    遗留问题 取top10
        $http({
            method:"post",
            url:"/api/getListController/getScoreGroupByCollege"
        }).success(function(data){
            console.log("教师排名");
            console.log(data);

            //最新视图
            //将数据 处理成 每个属性一个 数组
            var effectRow1 =[];
            var map = {};
            for(i=0;i<data.length;i++){
                effectRow1["all"]=effectRow1.push(data[i]);
            }
            for(i=0;i<effectRow1.length;i++){

//                           //把相同值的属性取出来放进key中
                var key =effectRow1[i].college;
                //console.log(key);
                map[key] = map[key] || (map[key] = []);
                //把json对象进行分组处理，属性值相同的则放进一起，此时map[key]是数组
                map[key].push(effectRow1[i]);
                map[key].name=effectRow1[i].collegeName ;
                //console.log(map[key].name);
                //$scope.map

            }
            $scope.map =map;
            console.log(map);
            for(var name in map) {
                var classRoomName = [];
                var percent = [];
                var idLibs =[];
                for (var i = 0; i < map[name].length; i++) {
                    classRoomName.push(map[name][i].teacherName);
                    percent.push(map[name][i].score.toFixed(2));//显示结果 保留两位小数

                    //    将数据  绑定给 E charts图表

                    idLibs.push("teEchart" + map[name][i].college);
                    console.log(idLibs[i]);
                }
                for(var i = 0;i < map[name].length; i++) {
                    //var myCharts = ["myChart1", "myChart2"];
                    console.log(myCharts[i]);
                    console.log(document.getElementById(idLibs[i]));
                    myCharts[i] = $rootScope.echarts33.init(document.getElementById(idLibs[i]));

                    //var option = {
                    //    color: ['#3398DB'],
                    //    tooltip: {
                    //        trigger: 'axis',
                    //        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    //            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    //        }
                    //    },
                    //    title: {
                    //        left: 'center',
                    //        text: map[name].name
                    //    },
                    //    grid: {
                    //        left: '3%',
                    //        right: '4%',
                    //        bottom: '3%',
                    //        containLabel: true
                    //    },
                    //    //下载 区域缩放功能等
                    //    toolbox: {
                    //        feature: {
                    //            dataZoom: {
                    //                yAxisIndex: 'none'
                    //            },
                    //            restore: {},
                    //            saveAsImage: {}
                    //        }
                    //    },
                    //    xAxis: [
                    //        {
                    //            type: 'category',
                    //            name: "班级",
                    //            data: classRoomName,
                    //            axisTick: {
                    //                alignWithLabel: true
                    //            }
                    //        }
                    //    ],
                    //    yAxis: [
                    //        {
                    //            type: 'value',
                    //            name: "评教率(%)"
                    //        }
                    //    ],
                    //    series: [
                    //        {
                    //            name: '评教率',
                    //            type: 'bar',
                    //            barWidth: '60%',
                    //            label: {
                    //                normal: {
                    //                    show: true,
                    //                    position: 'inside'
                    //                }
                    //            },
                    //            //data:[10, 52, 200, 334, 390, 330, 220]
                    //            data: percent
                    //        }
                    //    ]
                    //};
                    option = {
                        color: ['#3398DB'],
                        title: {
                            text: map[name].name,
                            left: 'center',
                            subtext: '数据来自评教系统结果表'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        //legend: {
                        //    data: ['2011年', '2012年']
                        //},
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                            //下载 区域缩放功能等
                            toolbox: {
                                feature: {
                                    dataZoom: {
                                        yAxisIndex: 'none'
                                    },
                                    restore: {},
                                    saveAsImage: {}
                                }
                            },
                        xAxis: {
                            type: 'value',
                            name:"分数",
                            boundaryGap: [0, 0.01]
                        },
                        yAxis: {
                            type: 'category',
                            name:"教师姓名",
                            data: classRoomName
                        },
                        series: [
                            {
                                name: '得分',
                                type: 'bar',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'inside'
                                    }
                                },
                                data: percent
                            }
                            //,
                            //{
                            //    name: '2012年',
                            //    type: 'bar',
                            //    data: [19325, 23438, 31000, 121594, 134141, 681807]
                            //}
                        ]
                    };
                    myCharts[i].setOption(option);
                }
            }


                //表格视图 数据 组织
            var jobsSortObject = {};
            for(var i =0; i< data.length; i++){
                var job = data[i],
                    mark = job.college,//mark 是 学院 id
                //mark = job.collegeName;//mark 是 collegeName
                    jobItem = jobsSortObject[mark];
                if(jobItem){
                    jobsSortObject[mark].push(job);

                }else{
                    jobsSortObject[mark] = [job];

                }
                jobsSortObject[mark].name=job.collegeName ;
            }
            $scope.dataHa = jobsSortObject;
            console.log("这是按 学院id 分组的评教率");
            console.log(jobsSortObject);
        })
    });