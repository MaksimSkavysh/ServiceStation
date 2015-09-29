var serviceApp = angular.module('serviceApp', ['ngRoute','homeModule']);

serviceApp.controller('serviceMainController', ['$scope', function($scope) {
    $scope.orderProp = 'age';
    $scope.url = 'resources/homepage/home.html';
    $scope.q12 = 'asdddddddddddddddddddddddda asssssssssssssss';
}]);

