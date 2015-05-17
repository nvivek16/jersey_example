var userDashboard = angular.module('userDashboard', ['ui.bootstrap', 'ngRoute', 'ngResource', 'ngDialog']);
//userDashboard.config(['$routeProvider', function($routeProvider){
//  $routeProvider.when('/users', {
//    templateUrl: 'templates/users_list.html',
//    controller: 'userListController'
//  });
//}]);
userDashboard.config(['ngDialogProvider', function (ngDialogProvider) {
            ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
                plain: false,
                showClose: true,
                closeByDocument: true,
                closeByEscape: true,
                appendTo: false,
                preCloseCallback: function () {
                    console.log('default pre-close callback');
                }
            });
        }]);