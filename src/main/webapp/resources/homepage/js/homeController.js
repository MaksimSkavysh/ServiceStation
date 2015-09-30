angular.module('homeModule').controller('homeController', ['$scope', '$location', function ($scope, $location) {
    $scope.location = $location;

    $scope.searchData={
        firstName:'',
        lastName:''
    };

    $scope.registerNewUser=function(){

    };

    $scope.submit=function(){
    }
}]);