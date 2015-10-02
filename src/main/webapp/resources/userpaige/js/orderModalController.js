/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('userPageModule');
userPageModule.controller('orderModalController', ['$scope', '$modalInstance', 'currentModalData', 'userPageHttpService', function ($scope, $modalInstance, currentModalData, userPageHttpService) {

    $scope.car = currentModalData;

    $scope.cancelModal = function () {
        $modalInstance.dismiss('canceled');
    };
}]);