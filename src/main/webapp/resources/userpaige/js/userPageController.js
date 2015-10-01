/**
 * Created by maksim on 01.10.2015.
 */
//angular.module('userPageModule').controller('userPageController', ['$scope', function ($scope) {
//    //getUserInfo();
//}]);
var userPageModule = angular.module('userPageModule');
userPageModule.controller('userPageController', ['$scope', '$location', 'getUserInfo','userPageHttpService', function ($scope, $location, getUserInfo,userPageHttpService) {

    $scope.newCar={
        make:'',
        model:'',
        year:'',
        vin:'',
        userId:''
    };

    $scope.addNewCar=function(){
        userPageHttpService.addNewCar($scope.newCar).then(function(){
            userPageHttpService.getUserCars($scope.user.id).then(function(data,status,headers,config){
                $scope.cars=data.data.cars;
                $scope.newCar.make='';
                $scope.newCar.model='';
                $scope.newCar.year='';
                $scope.newCar.vin='';
            },function(error){
                console.error(error);
            });
        },function(error){
            console.error(error);
        });
    };

    $scope.editCar=function(vin){
        console.log(vin);
    };

    getUserInfo().then(function (data,status,headers,config) {
        $scope.user=data.data.user;
        $scope.newCar.userId=$scope.user.id;
        userPageHttpService.getUserCars($scope.user.id).then(function(data,status,headers,config){
            $scope.cars=data.data.cars;
        },function(error){
            console.error(error);
        });
    }, function (error) {
        console.error(error);
    });
}]);