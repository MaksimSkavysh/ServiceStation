/**
 * Created by maksim on 01.10.2015.
 */
//angular.module('userPageModule').controller('userPageController', ['$scope', function ($scope) {
//    //getUserInfo();
//}]);
var userPageModule = angular.module('userPageModule');
userPageModule.controller('userPageController', ['$scope', '$location', 'getUserInfo', function ($scope, $location, getUserInfo) {



    getUserInfo().then(function (data,status,headers,config) {
        console.log("data:");
        $scope.user=data.data.user;
        console.log(data);
    }, function (error) {
        console.error(error);
    });
}]);