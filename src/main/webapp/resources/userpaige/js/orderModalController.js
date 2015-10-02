/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('userPageModule');
userPageModule.controller('orderModalController', ['$scope', '$modalInstance', 'currentModalData', 'userPageHttpService', function ($scope, $modalInstance, currentModalData, userPageHttpService) {

    $scope.orders = currentModalData.orders;
    $scope.vin=currentModalData.vin;

    $scope.newOrder={
        status: 'completed',
        date: '',
        amount: '',
        vin: ''
    };

    $scope.addNewOrder=function(){
        $scope.newOrder.vin=$scope.vin;
        userPageHttpService.addNewOrder($scope.newOrder).then(function(){
            userPageHttpService.getCarOrders($scope.vin).then(function(data, status, headers, config){
                $scope.orders=data.data.orders;
                $scope.newOrder.date= '';
                $scope.newOrder.amount= '';
                $scope.newOrder.vin= '';
            }, function( error ){
                console.log(error)
            });

        },function(error){
            console.error(error);
        });
        console.log($scope.newOrder);
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('canceled');
    };
}]);