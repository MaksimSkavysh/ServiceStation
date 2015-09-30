angular.module('homeModule').factory('homeHttpService',['$http',function($http){
    var services={};
    services.getUsers=function(searchData){
        return $http.get('rest/home?firstName='+searchData.firstName+'&lastName='+searchData.lastName);
    };

    services.addNewUser=function(user){
        return $http.post('rest/home',user);
    };

    return services;
}]);