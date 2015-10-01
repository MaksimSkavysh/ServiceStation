/**
 * Created by maksim on 01.10.2015.
 */
angular.module('userPageModule').factory('userPageHttpService',['$http',function($http){
    var services={};
    services.getUser=function(id){
        return $http.get('rest/userPaige?id='+id);
    };

    return services;
}]);