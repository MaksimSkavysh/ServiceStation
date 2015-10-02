var userPageModule = angular.module('userPageModule', []);

userPageModule.config(function ($routeProvider) {
    $routeProvider.when('/user/:userId', {
        templateUrl: 'resources/userpaige/user.html',
        controller: 'userPageController',
        resolve: {
            getUserInfo: ['$q', '$routeParams', '$location', '$route', 'userPageHttpService', function ($q, $routeParams, $location, $route, userPageHttpService) {
                var def = $q.defer();
                var id = $route.current.params.userId;
                console.log('asda s   ' + id);
                userPageHttpService.getUser(id).then(function (data, status, headers, config) {
                    def.resolve(data);
                }, function (error) {
                    console.error(error);
                });
                return function () {
                    return def.promise;
                };
            }]
        }
    });
});