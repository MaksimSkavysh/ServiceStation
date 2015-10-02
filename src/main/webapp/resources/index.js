var serviceApp = angular.module('serviceApp', ['ngRoute', 'ui.bootstrap', 'homeModule', 'userPageModule']);

//serviceApp.config(['$locationProvider',function($locationProvider){
//    $locationProvider.html5Mode(true);
//}]);

serviceApp.controller('serviceMainController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.url = 'resources/homepage/home.html';
    $scope.goToSearch = function () {
        $location.url('/');
    }
}]);

