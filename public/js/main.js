/**
 * Created by cxf on 2016/9/26.
 */
angular.module('app')
    .controller('AppCtrl', ['$scope','$state',
        function( $scope,$state) {
            $scope.pageKeyDown = function(e){
                var keycode = window.event?e.keyCode:e.which;
                if(keycode==13){
                    $state.go('app.search');
                }
            };
            $scope.search=function(){
                $state.go('app.search');
            }

            // add 'ie' classes to html
            var isIE = !!navigator.userAgent.match(/MSIE/i);
            if(isIE){ angular.element($window.document.body).addClass('ie');}
            if(isSmartDevice( $window ) ){ angular.element($window.document.body).addClass('smart')};

            // 系统顶部导航栏
            $scope.systemNavigator=[];
            //$scope.systemNavigatortop=[];






            //字号设置
            var fontConfig = {
                small: 14,
                middle: 15,
                large: 16
            }
            var selectFont = $('body');
            var selectFontHtml = $('html');
            var currentsize = localStorage.getItem("fontsize");
            if(currentsize==fontConfig.small){
                selectFont.addClass('small-font');
                selectFontHtml.addClass('small-font');
            }else if(currentsize==fontConfig.large){
                selectFont.addClass('large-font');
                selectFontHtml.addClass('large-font');
            }else{
                selectFont.addClass('middle-font');
                selectFontHtml.addClass('middle-font');
            }
            $scope.fontsizes=[{
                size:fontConfig.small,
                name:'小'
            },{
                size:fontConfig.middle,
                name:'中'
            },{
                size:fontConfig.large,
                name:'大'
            }];

            $scope.editToggle=false;
            $scope.editParams=function(){
                $('.edit-params').removeClass('hide');
                $scope.editToggle=!$scope.editToggle;
            }
            $scope.currentFont=currentsize;
            $scope.selectFont=function(font){
                $scope.currentFont = font.size;

                if(font.size==fontConfig.small){
                    selectFont.removeClass('middle-font');
                    selectFont.removeClass('large-font');
                    selectFont.addClass('small-font');

                    selectFontHtml.removeClass('middle-font');
                    selectFontHtml.removeClass('large-font');
                    selectFontHtml.addClass('small-font');
                    localStorage.setItem("fontsize",fontConfig.small);

                }else if(font.size==fontConfig.middle){
                    selectFont.removeClass('small-font');
                    selectFont.removeClass('large-font');
                    selectFont.addClass('middle-font');


                    selectFontHtml.removeClass('small-font');
                    selectFontHtml.removeClass('large-font');
                    selectFontHtml.addClass('middle-font');
                    localStorage.setItem("fontsize",fontConfig.middle);

                }else if(font.size==fontConfig.large){
                    selectFont.removeClass('middle-font');
                    selectFont.removeClass('small-font');
                    selectFont.addClass('large-font');

                    selectFontHtml.removeClass('middle-font');
                    selectFontHtml.removeClass('small-font');
                    selectFontHtml.addClass('large-font');
                    localStorage.setItem("fontsize",fontConfig.large);

                }
            }



            function isSmartDevice( $window )
            {
                // Adapted from http://www.detectmobilebrowsers.com
                var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
                // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
                return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
            }

        }]);
