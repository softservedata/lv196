angular
    .module('delivery')
    .controller('allOrdersController', ['$scope', '$locations', '$orderProperty', '$http', 'Notification',
        function ($scope, $locations, $orderProperty, $http, Notification) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $http.get('/all-orders/open').then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };



            $scope.filter = () => {
                var filterByCityFrom = $scope.filterByCityFrom || '';
                var cityFromId = filterByCityFrom.cityId || '';
                var filterByCityTo = $scope.filterByCityTo || '';
                var cityToId = filterByCityTo.cityId || '';
                var filterByWeight = $scope.filterByWeight || '';
                var filterByArrivalDate = Date.parse($scope.filterByArrivalDate) || '';

                $http.get('/all-orders/filtered-orders/?cityFrom=' + cityFromId +
                    '&cityTo=' + cityToId + '&weight=' + filterByWeight +
                    '&arrivalDate=' + filterByArrivalDate).then(response => {
                    console.log(response.data),
                        $scope.orders.open = response.data
                })
            };

            $scope.addOffer = (id) => {
                $orderProperty.setId(id);
                $http.post('/all-orders/offer/' + $orderProperty.getId()).then(response => {
                    console.log("addOffer");
                    Notification.success('Thank you, your offer added successfully');
                }).catch(function () {
                    throw Notification.error('Sorry, you can not create more than one offer for this order');
                })
            }
        }]);