/**
 * Created by cxf on 2016/9/26.
 */
angular.module('app')
    .controller("AppCtrl",["$scope","$state",function($scope,$state){
        $scope.$on("USER",function(event,data){
            console.log("ssss");
            console.log(data);
            $scope.$broadcast("child",{data:data})
        });
    }])
    .controller("LoginCtrl",["$scope","$state","$http",function($scope,$state,$http){

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
                url:'/api/UserInfo/getStudentJson',
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
                url:'/api/UserInfo/getTeacherJson',
                params:{
                    'userId':$stateParams.UserId
                }
            }).success(function(data){
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


        $http({
            method:"post",
            url:"/api/getListController/getCoursesListByStudent",
            params:{
                "stId":$stateParams.UserId
            }
        }).success(function(data){
            //console.log("获取数据成功");
            console.log(data);
            $scope.data = data;
        });





    })
    .controller("PingTeDetailCtrl",function($scope,$http,$stateParams){
//        课程id 为 $stateParams.courseId
//        根据 课程id 查到课程表数据
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
    })
    .controller("PingStCtrl",function($scope,$http,$stateParams){
        //教师 id 为 $stateParams.UserId
        console.log($stateParams.UserId);
    //    根据 教师 id 查 课程表 返回 课程 信息
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
        $scope.stId =$stateParams.stId;
        console.log("hahah")
    })