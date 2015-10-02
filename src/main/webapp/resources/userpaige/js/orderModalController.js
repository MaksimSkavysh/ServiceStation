/**
 * Created by maksim on 02.10.2015.
 */
var userPageModule = angular.module('userPageModule');
userPageModule.controller('orderModalController', ['$scope', '$modalInstance', 'currentModalData', 'userPageHttpService','openConfirmModal', function ($scope, $modalInstance, currentModalData, userPageHttpService, openConfirmModal) {

    $scope.orders = currentModalData.orders;
    $scope.vin=currentModalData.vin;
    $scope.collapse='';
    $scope.newOrder={
        status: 'completed',
        date: '',
        amount: '',
        vin: ''
    };
    $scope.editedOrder={
        status: 'completed',
        date: '',
        amount: '',
        vin: '',
        orderId:''
    };

    $scope.editOrder=function(order){
        $scope.collapse=order.orderId;
        $scope.editedOrder.status=order.status;
        $scope.editedOrder.date=order.date;
        $scope.editedOrder.amount=order.amount;
        $scope.editedOrder.vin=$scope.vin;
        $scope.editedOrder.orderId=order.orderId;
        console.log($scope.editedOrder);
    };

    $scope.saveEditedChanges=function(order){
        console.log($scope.editedOrder);
        userPageHttpService.saveEditedOrder($scope.editedOrder).then(function(){
            userPageHttpService.getCarOrders($scope.vin).then(function(data, status, headers, config){
                $scope.orders=data.data.orders;
            }, function( error ){
                console.log(error)
            });
        },function(error){
            console.error(error)
        });

        $scope.collapse='';
        //$scope.editedOrder.status='completed';
        //$scope.editedOrder.date='';
        //$scope.editedOrder.amount='';
        //$scope.editedOrder.vin='';
        //$scope.editedOrder.orderId='';
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

    $scope.deleteOrder= function(order){
        openConfirmModal("Delete order?").then(function () {
            userPageHttpService.deleteOrder(order.orderId).then(function () {
                userPageHttpService.getCarOrders($scope.vin).then(function (data, status, headers, config) {
                    $scope.orders = data.data.orders;
                }, function (error) {
                    console.error(error);
                });
            }, function (error) {
                console.error(error);
            });
        }, function (error) {
            console.log(error);
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('canceled');
    };
}]);