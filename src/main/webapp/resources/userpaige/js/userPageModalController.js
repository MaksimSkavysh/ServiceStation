/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('userPageModule');
userPageModule.controller('userPageModalController', ['$scope', '$modalInstance', 'currentModalData', 'userPageHttpService', function ($scope, $modalInstance, currentModalData, userPageHttpService) {

    $scope.oldData = angular.copy(currentModalData);
    $scope.data = currentModalData;

    $scope.saveCarChanges = function () {
        $scope.data.oldVin = $scope.oldData.vin;
        userPageHttpService.saveEditedCar($scope.data).then(function () {
            $modalInstance.close();
        }, function (error) {
            $modalInstance.dismiss(error);
        });
    };

    $scope.saveUserChanges = function () {
        userPageHttpService.saveEditedUser($scope.data).then(function () {
            $modalInstance.close();
        }, function (error) {
            $modalInstance.dismiss(error);
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('canceled');
    };
}]);