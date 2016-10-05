angular
    .module('delivery')
    .controller('ordersInProgressController', ['$scope', '$orders',
        function ($scope, $orders) {
            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveInProgressOrders = () => {
                $orders.findInProgress().then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveInProgressOrders();
        }]);