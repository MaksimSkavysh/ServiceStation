angular.module('homeModule', [])
    .config(function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'resources/homepage/home.html',
            controller: 'homeController'
        });
    });