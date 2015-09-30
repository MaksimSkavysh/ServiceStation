angular.module('homeModule').factory('homeHttpService',['$http',function($http){
    var services={};
    services.getUsers=function(){
        $http.get();
    };

    return services;
}]);