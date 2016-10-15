angular
    .module('delivery')
    .controller('allOrdersController', ['$scope', '$orderProperty', '$http',
    function ($scope, $orderProperty, $http) {
        $scope.orders = {
            open: []
        };

        $scope.retrieveOpenOrders = () => {
            $http.get('/all-orders/open').then(response => {
                $scope.orders.open = response.data;
            })
        };
        $scope.retrieveOpenOrders();

        $scope.filter = () => {
            var filterByCityFrom = $scope.filterByCityFrom || '';
            var filterByCityTo = $scope.filterByCityTo || '';
            var filterByWeight = $scope.filterByWeight || '';
            var filterByArrivalDate = Date.parse($scope.filterByArrivalDate) || '';

            $http.get('/all-orders/filtered-orders/?cityFrom=' + filterByCityFrom +
                '&cityTo=' + filterByCityTo + '&weight=' + filterByWeight +
                '&arrivalDate=' + filterByArrivalDate).then(response => {
                    console.log(response.data),
                    $scope.orders.open = response.data
            })
        };

        $scope.addOffer = (id) => {
            $orderProperty.setId(id);
            $http.post('/all-orders/offer/' + $orderProperty.getId()).then(response => {
                console.log("addOffer");
            })
        }
   }]);
