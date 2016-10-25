angular
    .module('delivery')
    .controller('findOrdersController', ['$scope', '$locations', '$orderProperty', '$http', 'Notification',
        function ($scope, $locations, $orderProperty, $http, Notification) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $http.get('/find-order/open').then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };

            $scope.filter = () => {
                var cityFromId = '';
                if ($scope.filterByCityFrom && $scope.filterByCityFrom.cityId) {
                    cityFromId = $scope.filterByCityFrom.cityId;
                } else if ($scope.filterByCityFrom) {
                    Notification.error('Sorry, you write incorrect city from name. You can use the hint');
                }

                var cityToId = '';
                if ($scope.filterByCityTo && $scope.filterByCityTo.cityId) {
                    cityToId = $scope.filterByCityTo.cityId;
                } else if ($scope.filterByCityTo) {
                    Notification.error('Sorry, you write incorrect city to name. You can use the hint');
                }

                var filterByWeight = '';
                if ($scope.filterByWeight > 0) {
                    filterByWeight = $scope.filterByWeight;
                } else if ($scope.filterByWeight) {
                    Notification.error('Sorry, you write incorrect weight. Please, write a positive number');
                }

                var filterByArrivalDate = '';
                if (Date.parse($scope.filterByArrivalDate) > Date.now()) {
                    filterByArrivalDate = Date.parse($scope.filterByArrivalDate);
                } else if ($scope.filterByArrivalDate) {
                    Notification.error('Sorry, you write incorrect date. You can use the calendar');
                }

                $http({
                    url: '/find-order/filtered-orders/',
                    method: 'GET',
                    params: {
                        cityFromId: cityFromId,
                        cityToId: cityToId,
                        weight: filterByWeight,
                        date: filterByArrivalDate
                    }
                }).then(response => {
                    console.log(response.data),
                        $scope.orders.open = response.data
                });
            };

            $scope.addOffer = (id) => {
                $orderProperty.setId(id);
                $http.post('/find-order/offer/' + $orderProperty.getId()).then(response => {
                    console.log("addOffer");
                    Notification.success('Thank you, your offer added successfully');
                }).catch(function () {
                    Notification.error('Sorry, you can not create more than one offer for this order');
                })
            }
        }]);