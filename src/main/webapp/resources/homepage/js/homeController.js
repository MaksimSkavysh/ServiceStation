angular.module('homeModule').controller('homeController', ['$scope', '$location','homeHttpService','$modal', function ($scope, $location,homeHttpService,$modal) {
    $scope.location = $location;

    $scope.searchData={
        firstName:'',
        lastName:''
    };

    $scope.users=null;

    $scope.registerNewUser=function(){
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'resources/homepage/createUserModal.html',
            controller: 'createUserModalController',
            windowClass:'createUserModalWindow'
        });
        modalInstance.result.then(function () {
        }, function (error) {
            console.error(error);
        });
    };

    $scope.submit=function(){
        homeHttpService.getUsers($scope.searchData).then(function(data, status, headers, config){
            $scope.users=data.data.users;
            console.log($scope.users);
        },function(error){
            console.error(error);
        });
    }
}]);