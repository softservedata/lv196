angular
    .module('delivery')
    .controller('ordersTrackingController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.trackOrder = function(orderId) {
                if(!orderId) {
                    return;
                }
                $http.get('/order/tracking/' + orderId).then(function(response) {
                    $scope.ordersTracking = response.data;
            });
            };
        }]);
