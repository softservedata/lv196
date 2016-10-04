angular
    .module('delivery')
    .controller('ordersInProgressController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveInProgressOrders = () => {
                $http.get('/order/in-progress').then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveInProgressOrders();
        }]);