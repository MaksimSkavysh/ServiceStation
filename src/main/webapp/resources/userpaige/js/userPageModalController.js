/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('userPageModule');
userPageModule.controller('userPageModalController', ['$scope', '$modalInstance', 'currentUser','userPageHttpService', function ($scope, $modalInstance, currentUser,userPageHttpService) {

    $scope.editedUser=currentUser;

    $scope.editedCar={
        make:'',
        model:'',
        year:'',
        vin:'',
        userId:''
    };

    $scope.editCar=function(vin){
        console.log(vin);
    };

    $scope.saveUserChanges=function(){
        userPageHttpService.saveEditedUser($scope.editedUser).then(function(){
            $modalInstance.close();
        },function(error){
            $modalInstance.dismiss(error);
        });
    };

    $scope.cancelModal=function(){
      $modalInstance.dismiss('canceled');
    };
}]);