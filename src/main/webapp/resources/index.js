var serviceApp = angular.module('serviceApp', ['ngRoute','homeModule']);

//serviceApp.config(['$locationProvider',function($locationProvider){
//    $locationProvider.html5Mode(true);
//}]);



serviceApp.controller('serviceMainController', ['$scope','$http','$location', function($scope,$http,$location) {
    $scope.orderProp = 'age';
    $scope.url = 'resources/homepage/home.html';
    $scope.query = 's ';
    $scope.count=0;
    $scope.fun1=function (){
        $http.get('login').then(function (data){
            console.log(data);
            //$scope.data=data;
            //$scope.status=status;
            //$scope.headers=headers;
            //$scope.config=config;
        },function(error){
            console.error(error);
        });
    };
    $scope.fun2=function (){
        $location.url('/home');
    }
}]);

