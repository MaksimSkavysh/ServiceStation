/**
 * Created by maksim on 01.10.2015.
 */
angular.module('userPageModule').factory('userPageHttpService', ['$http', function ($http) {
    var services = {};

    services.getUser = function (id) {
        return $http.get('rest/userPage?id=' + id);
    };

    services.getUserCars = function (id) {
        return $http.get('rest/Cars?id=' + id);
    };

    services.getCarOrders = function (vin) {
        return $http.get('rest/orders?vin=' + vin);
    };

    services.addNewCar = function (newCar) {
        return $http.post('rest/Cars', newCar)
    };

    services.addNewOrder = function (order) {
        return $http.post('rest/orders',order);
    };

    services.saveEditedUser = function (user) {
        return $http.put('rest/userPage', user);
    };

    services.saveEditedCar = function (car) {
        return $http.put('rest/Cars', car);
    };

    services.saveEditedOrder = function (order) {
        return $http.put('rest/orders', order);
    };

    services.deleteCar = function (vin) {
        return $http.delete('rest/Cars?vin=' + vin);
    };

    services.deleteOrder = function (orderId) {
        return $http.delete('rest/orders?id=' + orderId);
    };

    return services;
}]);