/**
 * Created by maksim on 01.10.2015.
 */
angular.module('userPageModule').factory('userPageHttpService',['$http',function($http){
    var services={};

    services.getUser=function(id){
        return $http.get('rest/userPage?id='+id);
    };

    services.getUserCars=function(id){
        return $http.get('rest/Cars?id='+id);
    };

    services.addNewCar=function(newCar){
      return $http.post('rest/Cars',newCar)
    };

    services.saveEditedUser=function(user){
        return $http.put('rest/userPage',user);
    };

    return services;
}]);