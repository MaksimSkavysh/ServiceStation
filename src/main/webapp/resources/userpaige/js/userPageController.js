/**
 * Created by maksim on 01.10.2015.
 */
//angular.module('userPageModule').controller('userPageController', ['$scope', function ($scope) {
//    //getUserInfo();
//}]);
var userPageModule = angular.module('userPageModule');
userPageModule.controller('userPageController', ['$scope', '$location', 'getUserInfo', 'userPageHttpService', '$modal', 'openConfirmModal', function ($scope, $location, getUserInfo, userPageHttpService, $modal, openConfirmModal) {

    $scope.newCar = {
        make: '',
        model: '',
        year: '',
        vin: '',
        userId: ''
    };

    $scope.addNewCar = function () {
        userPageHttpService.addNewCar($scope.newCar).then(function () {
            userPageHttpService.getUserCars($scope.user.id).then(function (data, status, headers, config) {
                $scope.cars = data.data.cars;
                $scope.newCar.make = '';
                $scope.newCar.model = '';
                $scope.newCar.year = '';
                $scope.newCar.vin = '';
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.error(error);
        });
    };

    $scope.editCar = function (car) {
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'resources/userpaige/editCarModal.html',
            controller: 'userPageModalController',
            windowClass: 'createUserModalWindow',
            resolve: {
                currentModalData: function () {
                    return angular.copy(car)
                }
            }
        });
        modalInstance.result.then(function () {
            userPageHttpService.getUserCars($scope.user.id).then(function (data, status, headers, config) {
                $scope.cars = data.data.cars;
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.error(error);
        });
    };

    $scope.editUser = function () {
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'resources/userpaige/editUserModal.html',
            controller: 'userPageModalController',
            windowClass: 'createUserModalWindow',
            resolve: {
                currentModalData: function () {
                    return angular.copy($scope.user)
                }
            }
        });
        modalInstance.result.then(function () {
            userPageHttpService.getUser($scope.user.id).then(function (data, status, headers, config) {
                $scope.user = data.data.user;
            }, function (error) {
                console.error(error)
            });
        }, function (error) {
            console.error(error);
        });
    };

    $scope.deleteCar = function (car) {
        console.log(car);
        openConfirmModal("Delete car?").then(function () {
            userPageHttpService.deleteCar(car.vin).then(function () {
                userPageHttpService.getUserCars($scope.user.id).then(function (data, status, headers, config) {
                    $scope.cars = data.data.cars;
                }, function (error) {
                    console.error(error);
                });
            }, function (error) {
                console.error(error);
                if (error.status === 409) {
                    alert("this car has orders")
                }
            });
        }, function (error) {
            console.log(error);
        });
    };

    $scope.clearInputs = function () {
        $scope.newCar.make = '';
        $scope.newCar.model = '';
        $scope.newCar.year = '';
        $scope.newCar.vin = '';
    };

    $scope.openOrdersModal = function (car) {
        userPageHttpService.getCarOrders(car.vin).then(function (data, status, headers, config) {
            console.log(data.data.orders);
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'resources/userpaige/ordersModal.html',
                controller: 'orderModalController',
                windowClass: 'createUserModalWindow',
                size: 'md',
                resolve: {
                    currentModalData: function () {
                        return {
                            orders: data.data.orders,
                            vin: car.vin
                        }
                    }
                }
            });
            modalInstance.result.then(function () {
                userPageHttpService.getUser($scope.user.id).then(function (data, status, headers, config) {

                }, function (error) {
                    console.error(error)
                });
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.log(error)
        });
    };

    getUserInfo().then(function (data, status, headers, config) {
        $scope.user = data.data.user;
        $scope.newCar.userId = $scope.user.id;
        userPageHttpService.getUserCars($scope.user.id).then(function (data, status, headers, config) {
            $scope.cars = data.data.cars;
        }, function (error) {
            console.error(error);
        });
    }, function (error) {
        console.error(error);
    });
}]);