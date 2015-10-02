angular.module('serviceApp').factory('openConfirmModal', ['$modal', function ($modal) {

    var service = function (text) {
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'resources/confirmModal/confirm.html',
            controller: 'confirmModalController',
            windowClass: 'createUserModalWindow',
            size: 'sm',
            resolve: {
                confirmText: function () {
                    return text
                }
            }
        });
        return modalInstance.result;
    };
    return service;
}]);