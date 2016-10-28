angular
    .module('delivery')
    .controller('ordersInProgressController', ['$scope', '$orders','$uibModal',
        function ($scope, $orders, $uibModal) {
            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveInProgressOrders = () => {
                $orders.findInProgress().then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveInProgressOrders();

            $scope.showMap = function (orderThis) {

                        order: () => orderThis

            }
        }]);