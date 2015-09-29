angular.module('homeModule').controller('homeController', ['$scope', '$location', function ($scope, $location) {
    $scope.location = $location;
    $scope.navigation = {url: '/resources/homepage/home.html'};
    $scope.aaa='AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA';
}]);