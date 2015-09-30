angular.module('homeModule').controller('createUserModalController',['$scope','$modalInstance','homeHttpService',function($scope,$modalInstance,homeHttpService){
    $scope.user={
        firstName:'',
        lastName:'',
        birthDate:'',
        address:'',
        email:'',
        phone:''
    };
    $scope.cancelModal=function(){
        $modalInstance.dismiss();
    };
    $scope.submit=function(){
        //TODO: add redirect to user paige
        homeHttpService.addNewUser($scope.user).then(function(data){

        },function(error){
            $modalInstance.dismiss(error);
        });
    };
}]);