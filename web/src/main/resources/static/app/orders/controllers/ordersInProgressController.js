angular
    .module('delivery')
    .controller('ordersInProgressController', ['$scope', '$chat', '$orders',
        function ($scope, $chat, $orders) {
            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveInProgressOrders = () => {
                $orders.findInProgress().then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveInProgressOrders();

            $scope.showChat = (offerId) => {
                $chat.open(offerId);
            };

            $scope.showMap = function (orderThis) {

                        order: () => orderThis

            }
        }]);