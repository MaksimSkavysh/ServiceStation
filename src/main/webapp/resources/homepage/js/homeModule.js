angular.module('homeModule',[])
    .config(function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl : 'resources/homepage/home.html',
        controller: 'homeController'
    });
});