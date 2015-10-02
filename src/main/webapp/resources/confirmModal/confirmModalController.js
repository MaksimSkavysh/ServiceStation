/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('serviceApp');
userPageModule.controller('confirmModalController', ['$scope', '$modalInstance', 'confirmText', function ($scope, $modalInstance, confirmText) {
    $scope.confirmText = confirmText;
    $scope.cancel = function () {
        $modalInstance.dismiss();
    };
    $scope.accept = function () {
        $modalInstance.close();
    }
}]);